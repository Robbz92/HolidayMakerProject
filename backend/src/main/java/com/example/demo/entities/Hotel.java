package com.example.demo.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

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
    private List<String> attractionList;
    @Transient
    private String placeName;
    @Transient
    private int price;

    @OneToMany(mappedBy = "hotel_id")
    private List<Review> review;

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
                 List<String> comfortList, List<String> attractionList,int price, String placeName) {
        this.id = id;
        this.cityId = cityId;
        this.name = name;
        this.address = address;
        this.totalScore = totalScore;
        this.distanceDowntown = distanceDowntown;
        this.distanceBeach = distanceBeach;
        this.hotelImg = hotelImg;
        this.comfortList = comfortList;
        this.attractionList = attractionList;
        this.price = price;
        this.placeName = placeName;
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

    public List<String> getAttractionList() {
        return attractionList;
    }

    public void setAttractionList(List<String> attractionList) {
        this.attractionList = attractionList;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getPlaceName() {
        return placeName;
    }

    public void setPlaceName(String placeName) {
        this.placeName = placeName;
    }

    @JsonIgnore
    public List<Review> getReview() {return review;}

    @JsonProperty
    public void setReview(List<Review> review) {this.review = review;}

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
                ", attractionList=" + attractionList +
                ", placeName='" + placeName + '\'' +
                ", price=" + price +
                '}';
    }
}