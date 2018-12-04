package com.Hotel.io.Project.services.impl;

import com.Hotel.io.Project.domain.Room;
import com.Hotel.io.Project.repository.RoomRepository;
import com.Hotel.io.Project.services.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class RoomServiceImp implements RoomService
{
    @Autowired
    RoomRepository roomRepository;


    public List<Room> findAllRoom()
    {
        return (List<Room>) roomRepository.findAll();

    }

   public Optional<Room> findRoom(Long id)
    {
        return roomRepository.findById(id);

    }


   public Room saveRoom(Room room)
    {

        return roomRepository.save(room);

    }
    //  List<Hotel> blurrySearch(String title);

   public void removeRoom(Long id)
    {
       roomRepository.deleteById(id);

    }

}
