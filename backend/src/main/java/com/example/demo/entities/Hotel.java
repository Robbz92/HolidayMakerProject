package com.example.demo.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

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
    private long hotelImg;

    public Hotel() {
    }

    public Hotel(String name, String address, long totalScore, long distanceDowntown, long distanceBeach, long hotelImg) {
        this.name = name;
        this.address = address;
        this.totalScore = totalScore;
        this.distanceDowntown = distanceDowntown;
        this.distanceBeach = distanceBeach;
        this.hotelImg = hotelImg;
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

    public long getHotelImg() {
        return hotelImg;
    }

    public void setHotelImg(long hotelImg) {
        this.hotelImg = hotelImg;
    }

    @Override
    public String toString() {
        return "Hotel{" +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", totalScore=" + totalScore +
                ", distanceDownTown=" + distanceDowntown +
                ", distanceBeach=" + distanceBeach +
                '}';
    }
}
