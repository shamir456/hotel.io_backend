package com.Hotel.io.Project.services;

import com.Hotel.io.Project.domain.Room;

import java.util.List;
import java.util.Optional;

public interface RoomService
{
    List<Room> findAllRoom();
    Optional<Room> findRoom(Long id);
    Room saveRoom(Room room);
    //  List<Hotel> blurrySearch(String title);
    void removeRoom(Long id);
}
