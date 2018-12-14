package com.Hotel.io.Project.domain;






import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.*;

@Entity
@Table(name = "hotel")
public class Hotel implements Serializable {
    private static final long serialVersionUID=425345L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank
    @Column(unique = true)
    @Size(min = 1, max = 100)
    private String    hotelname;

    private String    rating;
    private String  location;
    private  String img;

    //@JsonBackReference
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "hotel")
    private List<Room> rooms = new ArrayList<>();

    public Hotel(String hotelname, String rating, String location, String img, List<Room> rooms) {
        this.hotelname = hotelname;
        this.rating = rating;
        this.location = location;
        this.img = img;
        this.rooms = rooms;
    }
    public Hotel() {
    }
    public Hotel(String hotelname, String rating, String location, String img) {
        this.hotelname = hotelname;
        this.rating = rating;
        this.location = location;
        this.img = img;
    }

    public Room addRoom(Room room)
    {
        this.rooms.add(room);

        return room;
    }

    public void removeRoom(Room room)
    {
        this.rooms.remove(room);
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public String getHotelname() {
        return hotelname;
    }

    public void setHotelname(String hotelname) {
        this.hotelname = hotelname;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public List<Room> getRooms() {
        return (List<Room>) rooms;
    }

    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
    }
}
