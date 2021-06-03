package com.example.demo.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;

@Entity
@Table(name = "reviews")
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String text;
    private int score;
    private long user_id;
    private long bookingId;

    @Column(name = "hotel_id")
    private long hotel_id;

    @ManyToOne
    @JoinColumn(name = "hotel_id", insertable = false, updatable = false) //, insertable = false, updatable = false
    private Hotel hotel;

    public Review() {}

    public Review(long bookingId, String text, int score, long user_id, long hotel_id) {
        this.text = text;
        this.score = score;
        this.user_id = user_id;
        this.hotel_id = hotel_id;
        this.bookingId = bookingId;
    }

    public long getBookingId() {
        return bookingId;
    }

    public void setBookingId(long bookingId) {
        this.bookingId = bookingId;
    }

    @JsonIgnore
    public long getId() {
        return id;
    }
    @JsonProperty
    public void setId(long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public long getUser_id() {
        return user_id;
    }

    public void setUser_id(long user_id) {
        this.user_id = user_id;
    }

    @JsonIgnore
    public long getHotel_id() {
        return hotel_id;
    }

    @JsonProperty
    public void setHotel_id(long hotel_id) {
        this.hotel_id = hotel_id;
    }

    @Override
    public String toString() {
        return "Review{" +
                "id=" + id +
                ", text='" + text + '\'' +
                ", score=" + score +
                ", user_id=" + user_id +
                ", hotel_id=" + hotel_id +
                '}';
    }
}
