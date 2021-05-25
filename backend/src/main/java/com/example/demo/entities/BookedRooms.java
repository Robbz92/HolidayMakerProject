package com.example.demo.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;

@Entity
@Table(name="booked_rooms")
public class BookedRooms {
    @Id
    private long id;
    private long roomsId;
    private long board;
    private long extraBedAmount;
    private String fromDate;
    private String toDate;

    @ManyToOne
    private Bookings bookings;

    public BookedRooms(Bookings bookings, long roomsId, long board, long extraBedAmount, String fromDate, String toDate) {
        this.bookings = bookings;
        this.roomsId = roomsId;
        this.board = board;
        this.extraBedAmount = extraBedAmount;
        this.fromDate = fromDate;
        this.toDate = toDate;
    }

    public BookedRooms() {
    }


    public Bookings getBookings() {
        return bookings;
    }


    public void setBookings(Bookings bookings) {
        this.bookings = bookings;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getRoomsId() {
        return roomsId;
    }

    public void setRoomsId(long roomsId) {
        this.roomsId = roomsId;
    }

    public long getBoard() {
        return board;
    }

    public void setBoard(long board) {
        this.board = board;
    }

    public long getExtraBedAmount() {
        return extraBedAmount;
    }

    public void setExtraBedAmount(long extraBedAmount) {
        this.extraBedAmount = extraBedAmount;
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

    @Override
    public String toString() {
        return "BookedRooms{" +
                "id=" + id +
                ", roomsId=" + roomsId +
                ", board=" + board +
                ", extraBedAmount=" + extraBedAmount +
                ", fromDate='" + fromDate + '\'' +
                ", toDate='" + toDate + '\'' +
                '}';
    }
}
