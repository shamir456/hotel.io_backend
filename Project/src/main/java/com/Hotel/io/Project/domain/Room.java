package com.Hotel.io.Project.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "room")
public class Room implements Serializable {
    private static final long serialVersionUID=425345L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

   // @JsonManagedReference
   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "hotel_id", nullable = false)
    private Hotel hotel;






    private String  room_name;
    private String  rating;
    private String price;
    private  String img;


    private boolean isenabled=true;
    public boolean isIsenabled() {
        return isenabled;
    }

    public void setIsenabled(boolean isenabled) {
        this.isenabled = isenabled;
    }

    public Room() {
    }
    public Room(Hotel hotel, String room_name, String rating, String price, String img,boolean isenabled) {
        this.hotel = hotel;
        this.room_name = room_name;
        this.rating = rating;
        this.price = price;
        this.img = img;
        this.isenabled=isenabled;
    }

    public Room(String room_name, String rating, String price, String img) {
        this.room_name = room_name;
        this.rating = rating;
        this.price = price;
        this.img = img;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRoom_name() {
        return room_name;
    }

    public void setRoom_name(String room_name) {
        this.room_name = room_name;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
