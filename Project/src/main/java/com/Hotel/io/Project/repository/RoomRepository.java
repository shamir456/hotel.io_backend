package com.Hotel.io.Project.repository;

import com.Hotel.io.Project.domain.Room;
import org.springframework.data.repository.CrudRepository;

public interface RoomRepository extends CrudRepository<Room,Long> {
    @Override
    Iterable<Room> findAllById(Iterable<Long> iterable);
}
