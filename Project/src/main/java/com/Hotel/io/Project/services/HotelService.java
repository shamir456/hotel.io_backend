package com.Hotel.io.Project.services;

import com.Hotel.io.Project.domain.Hotel;
import com.Hotel.io.Project.domain.Room;

import java.util.List;
import java.util.Optional;

public interface HotelService
{
    List<Hotel> findAll();
    Optional<Hotel> find(Long id);
    Hotel save(Hotel hotel);
  //  List<Hotel> blurrySearch(String title);
    void remove(Long id);
    public Optional<Hotel> addHotelRooms(Hotel hotel, List<Room> rooms);

}
