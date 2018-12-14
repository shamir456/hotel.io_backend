package com.Hotel.io.Project.repository;

import com.Hotel.io.Project.domain.Booking;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BookingRepository  {

    List<Booking> getAllBookings();
    Booking getBookingById(int BookingId);
    void addBooking(Booking booking);
    void updateBooking(Booking booking);
    void deleteBooking(int BookingId);

}
