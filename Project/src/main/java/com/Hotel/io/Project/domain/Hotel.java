package com.Hotel.io.Project.domain;






import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Hotel implements Serializable {
    private static final long serialVersionUID=425345L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String    hotelname;
    private String    rating;
    private String  location;
    private  String img;

    @OneToMany(mappedBy = "hotel",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private Set<Room> rooms =new HashSet<>();

    public Hotel(String hotelname, String rating, String location, String img, Set<Room> rooms) {
        this.hotelname = hotelname;
        this.rating = rating;
        this.location = location;
        this.img = img;
        this.rooms = rooms;
    }

    public void addRoom(Room room)
    {
        this.rooms.add(room);
    }

    public void removeRoom(Room room)
    {
        this.rooms.remove(room);
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public Set<Room> getRooms() {
        return rooms;
    }

    public void setRooms(Set<Room> rooms) {
        this.rooms = rooms;
    }
}
