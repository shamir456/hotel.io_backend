package com.Hotel.io.Project.services.impl;

import com.Hotel.io.Project.domain.Room;
import com.Hotel.io.Project.repository.RoomRepository;
import com.Hotel.io.Project.services.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class RoomServiceImp implements RoomService
{
    @Autowired
    RoomRepository roomRepository;

    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<Room> findAllRoom()
    {
        List<Room> roomlist=(List<Room>)roomRepository.findAll();
        List<Room> activeroomList = new ArrayList<>();

        for (Room room: roomlist)
        {
            if(room.isIsenabled())
            {
                activeroomList.add(room);

            }



        }


        return activeroomList;
    }
    public void updateRoom(Room room)
    {

        String sql = "UPDATE room SET isenabled=false WHERE Id=?";
        jdbcTemplate.update(sql, room.isIsenabled());
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
