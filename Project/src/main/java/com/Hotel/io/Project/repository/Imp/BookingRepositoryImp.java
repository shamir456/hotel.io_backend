package com.Hotel.io.Project.repository.Imp;

import com.Hotel.io.Project.BookingRowMapper;
import com.Hotel.io.Project.domain.Booking;
import com.Hotel.io.Project.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BookingRepositoryImp implements BookingRepository
{
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Override
    public Booking getBookingById(int BookingId) {
        String sql = "SELECT BookingId, checkin, checkout FROM Booking WHERE BookingId = ?";
        RowMapper<Booking> rowMapper = new BeanPropertyRowMapper<Booking>(Booking.class);
        Booking Booking = jdbcTemplate.queryForObject(sql, rowMapper, BookingId);
        Booking.setId(BookingId);
        return Booking;
    }
    @Override
    public List<Booking> getAllBookings() {
        String sql = "SELECT * FROM Booking";
       // RowMapper<Booking> rowMapper = new BeanPropertyRowMapper<Booking>(Booking.class);
        return jdbcTemplate.query(sql,new BookingRowMapper());

    }
    @Override
    public void addBooking(Booking booking) {
        //Add Booking
        String sql = "INSERT INTO Booking (BookingId, checkin,checkout) values (?, ?, ?)";
        jdbcTemplate.update(sql, booking.getId(), booking.getCheckin(),booking.getCheckout());

        //Fetch Booking id
        sql = "select BookingId from Booking where checkin=?,checkout=?";
        System.out.println(sql +"\n");
        int BookingId = jdbcTemplate.queryForObject(sql, Integer.class,booking.getCheckin(),booking.getCheckout());
        System.out.println(BookingId);

        //Set Booking id
        booking.setId(BookingId);
    }
    @Override
    public void updateBooking(Booking booking) {
        String sql = "UPDATE Booking SET checkin=?, checkout=? WHERE Booking.BookingId=?";
        jdbcTemplate.update(sql, booking.getCheckin(), booking.getCheckout(), booking.getId());
    }
    @Override
    public void deleteBooking(int BookingId){
        String sql = "DELETE FROM Booking WHERE BookingId=?";
        jdbcTemplate.update(sql, BookingId);
    }


}
