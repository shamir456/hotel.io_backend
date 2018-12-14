package com.Hotel.io.Project.services.impl;

import com.Hotel.io.Project.domain.Booking;
import com.Hotel.io.Project.repository.BookingRepository;
import com.Hotel.io.Project.services.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;


@Service
public class BookingServiceImp implements BookingService {

    @Autowired
    BookingRepository bookingRepository;





    public List<Booking> getAllBookings()
    {
        return bookingRepository.getAllBookings();

    }

    public Booking getBookingById(int BookingId)
    {
        Booking booking=new Booking();
       booking= bookingRepository.getBookingById(BookingId);
        return booking;

    }
    public Booking addBooking(Booking booking)
    {
//        if (bookingRepository.bookingExists(booking.isState())) {
//            return false;
//        } else {
//            bookingRepository.addBooking(booking);
//            return true;
//        }
        bookingRepository.addBooking(booking);
       return booking;

    }
    public void updateBooking(Booking booking)
    {
         bookingRepository.updateBooking(booking);

    }
   public void deleteBooking(int BookingId)
    {
        bookingRepository.deleteBooking(BookingId);

    }

}
