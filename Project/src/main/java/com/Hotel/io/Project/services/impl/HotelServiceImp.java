package com.Hotel.io.Project.services.impl;

import com.Hotel.io.Project.domain.Hotel;
import com.Hotel.io.Project.domain.Room;
import com.Hotel.io.Project.repository.HotelRepository;
import com.Hotel.io.Project.repository.RoomRepository;
import com.Hotel.io.Project.services.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class HotelServiceImp implements HotelService
{
    @Autowired
    private HotelRepository hotelRepository;

    @Autowired
    private RoomRepository roomRepository;

    public List<Hotel> findAll()
    {
        List<Hotel> hotellist=(List<Hotel>)hotelRepository.findAll();
        List<Hotel> activeHotelList = new ArrayList<>();

        for (Hotel hotel: hotellist)
        {
            activeHotelList.add(hotel);

        }


        return activeHotelList;
    }
    public Optional<Hotel> addHotelRooms(Hotel hotel, List<Room> rooms)
    {

       // Long id=hotel.getId();

        //Hotel hotel1=(Hotel) hotel;
        for (Room room :rooms)
        {
           // hotel.addRoom(room);
            room.setHotel(hotel);

        }
        hotelRepository.save(hotel);
        roomRepository.saveAll(rooms);




        return null ;
    }

    public Optional<Hotel> find(Long id)
    {
        return hotelRepository.findById(id);
    }
    public Hotel save(Hotel hotel)
    {
         roomRepository.saveAll(hotel.getRooms());


        return hotelRepository.save(hotel);
    }

//    public  List<Hotel> blurrySearch(String keyword)
//    {
//        List<Hotel> hotelList=hotelRepository.findByTitleContaining(keyword);
//
//        return hotelList;
//    }
    public void remove(Long id)
    {
        hotelRepository.deleteById(id);
    }




}
