package com.Hotel.io.Project.services;

import com.Hotel.io.Project.domain.Booking;
import com.Hotel.io.Project.domain.Room;

import java.util.List;

public interface BookingService {

    List<Booking> getAllBookings();
    Booking getBookingById(int BookingId);
    Booking addBooking(Booking booking);
    void updateBooking(Booking booking);
    void deleteBooking(int BookingId);

}
