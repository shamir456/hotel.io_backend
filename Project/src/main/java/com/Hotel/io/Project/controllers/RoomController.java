package com.Hotel.io.Project.controllers;


import com.Hotel.io.Project.domain.Hotel;
import com.Hotel.io.Project.domain.Room;
import com.Hotel.io.Project.services.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping(value = "/room")
public class RoomController {

    @Autowired
    RoomService roomService;


    @RequestMapping(method = RequestMethod.GET,produces = {"application/json"})
    public List<Room> getHotelList() {
        return roomService.findAllRoom();
    }

    @RequestMapping(value = "/save",method = RequestMethod.POST)
    public Room saveRoom(@RequestBody Room room) {
        return roomService.saveRoom(room);
    }

    @RequestMapping(value = "/remove",method = RequestMethod.POST)
    public void removeRoom(@RequestBody HashMap<String,String> mapper)throws Exception
    {
        String id=mapper.get("id");roomService.removeRoom(Long.valueOf(id));

    }
}
