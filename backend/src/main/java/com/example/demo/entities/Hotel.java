package com.example.demo.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="hotels")
public class Hotel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    private long cityId;
    private String name;
    private String address;
    private long totalScore;
    private long distanceDowntown;
    private long distanceBeach;
    private String hotelImg;
    @Transient
    private List<String> comfortList;
    @Transient
    private int price;

    public Hotel() {
    }

    public Hotel(String name, String address, long totalScore, long distanceDowntown,
                 long distanceBeach, String hotelImg) {
        this.name = name;
        this.address = address;
        this.totalScore = totalScore;
        this.distanceDowntown = distanceDowntown;
        this.distanceBeach = distanceBeach;
        this.hotelImg = hotelImg;
    }

    public Hotel(long id, long cityId, String name, String address, long totalScore,
                 long distanceDowntown, long distanceBeach, String hotelImg,
                 List<String> comfortList, int price) {
        this.id = id;
        this.cityId = cityId;
        this.name = name;
        this.address = address;
        this.totalScore = totalScore;
        this.distanceDowntown = distanceDowntown;
        this.distanceBeach = distanceBeach;
        this.hotelImg = hotelImg;
        this.comfortList = comfortList;
        this.price = price;
    }

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    @JsonIgnore
    public long getCityId() {
        return cityId;
    }
    @JsonIgnore
    public void setCityId(long cityId) {
        this.cityId = cityId;
    }

    public long getDistanceDowntown() {
        return distanceDowntown;
    }

    public void setDistanceDowntown(long distanceDowntown) {
        this.distanceDowntown = distanceDowntown;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public long getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(long totalScore) {
        this.totalScore = totalScore;
    }

    public long getDistanceBeach() {
        return distanceBeach;
    }

    public void setDistanceBeach(long distanceBeach) {
        this.distanceBeach = distanceBeach;
    }

    public String getHotelImg() {
        return hotelImg;
    }

    public void setHotelImg(String hotelImg) {
        this.hotelImg = hotelImg;
    }

    public List<String> getComfortList() {
        return comfortList;
    }

    public void setComfortList(List<String> comfortList) {
        this.comfortList = comfortList;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Hotel{" +
                "id=" + id +
                ", cityId=" + cityId +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", totalScore=" + totalScore +
                ", distanceDowntown=" + distanceDowntown +
                ", distanceBeach=" + distanceBeach +
                ", hotelImg='" + hotelImg + '\'' +
                ", comfortList=" + comfortList +
                ", price=" + price +
                '}';
    }
}