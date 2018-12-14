package com.Hotel.io.Project.repository;

import com.Hotel.io.Project.domain.Hotel;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface HotelRepository extends CrudRepository<Hotel,Long> {
    //List<Hotel> findByTitleContaining(String keyword);
   // Hotel findOne(Long id);
  // Hotel findHotelById(Long id);


}
