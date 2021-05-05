package com.example.demo.entities;

import javax.persistence.*;

@Entity()
@Table(name = "rooms")
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private long hotelId;
    private long roomTypeId;
    private double price;

    public Room() {
    }

    public Room(long hotelId, long roomId, double price) {
        this.hotelId = hotelId;
        this.roomTypeId = roomId;
        this.price = price;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getHotelId() {
        return hotelId;
    }

    public void setHotelId(long hotelId) {
        this.hotelId = hotelId;
    }

    public long getRoomId() {
        return roomTypeId;
    }

    public void setRoomId(long roomId) {
        this.roomTypeId = roomId;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Room{" +
                "id=" + id +
                ", hotelId=" + hotelId +
                ", roomId=" + roomTypeId +
                ", price=" + price +
                '}';
    }
}
