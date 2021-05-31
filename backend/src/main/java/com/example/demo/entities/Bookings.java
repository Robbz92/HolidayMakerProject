package com.example.demo.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="bookings")
public class Bookings {
    @Id
    private long id;
    private long userId;
    private long hotelId;
    private String fromDate;
    private String toDate;
    private double totalCost;
    private String paymentState;

    @OneToMany(mappedBy = "bookings", cascade=CascadeType.ALL)
    private List<BookedRooms> bookedRooms = new ArrayList<>();

    public Bookings(long userId, long hotelId, String fromDate, String toDate, long totalCost, String paymentState) {
        this.userId = userId;
        this.hotelId = hotelId;
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.totalCost = totalCost;
        this.paymentState = paymentState;
    }

    public Bookings() {
    }

    public List<BookedRooms> getBookedRooms() {
        return bookedRooms;
    }

    public void setBookedRooms(List<BookedRooms> bookedRooms) {
        this.bookedRooms = bookedRooms;
    }

    public String getPaymentState() {
        return paymentState;
    }

    public void setPaymentState(String paymentState) {
        this.paymentState = paymentState;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getHotelId() {
        return hotelId;
    }

    public void setHotelId(long hotelId) {
        this.hotelId = hotelId;
    }

    public String getFromDate() {
        return fromDate;
    }

    public void setFromDate(String fromDate) {
        this.fromDate = fromDate;
    }

    public String getToDate() {
        return toDate;
    }

    public void setToDate(String toDate) {
        this.toDate = toDate;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }

    @Override
    public String toString() {
        return "Bookings{" +
                "id=" + id +
                ", userId=" + userId +
                ", hotelId=" + hotelId +
                ", fromDate='" + fromDate + '\'' +
                ", toDate='" + toDate + '\'' +
                ", totalCost=" + totalCost +
                '}';
    }
}
