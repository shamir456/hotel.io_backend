package com.Hotel.io.Project.domain;


import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Room implements Serializable {
    private static final long serialVersionUID=425345L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    private Hotel hotel;


    private String  room_name;
    private String  rating;
    private String price;
    private  String img;


    public Room(Hotel hotel, String room_name, String rating, String price, String img) {
        this.hotel = hotel;
        this.room_name = room_name;
        this.rating = rating;
        this.price = price;
        this.img = img;
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
