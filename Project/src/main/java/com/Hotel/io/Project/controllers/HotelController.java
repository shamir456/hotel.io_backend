package com.Hotel.io.Project.controllers;

import com.Hotel.io.Project.domain.Hotel;
import com.Hotel.io.Project.domain.Room;
import com.Hotel.io.Project.services.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/hotel")
public class HotelController {

    @Autowired
    HotelService hotelService;


    @RequestMapping(produces ={"application/json"})
    public Iterable<Hotel> getHotelList() {
        return hotelService.findAll();
    }

    @RequestMapping(value = "/save",method = RequestMethod.POST,consumes ={"application/json"})
    public Optional<Hotel> save(@RequestBody Hotel hotel) {
        List<Room> room =new ArrayList<Room>(hotel.getRooms());

         

        return hotelService.addHotelRooms(hotel,room);
    }

    @RequestMapping(value = "/remove",method = RequestMethod.POST)
    public void remove(@RequestBody HashMap<String,String> mapper)throws Exception
    {
        String id=mapper.get("id");hotelService.remove(Long.valueOf(id));

    }
}
