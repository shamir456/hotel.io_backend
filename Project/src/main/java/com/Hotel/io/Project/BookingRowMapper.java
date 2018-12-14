package com.Hotel.io.Project;


import java.sql.ResultSet;
import java.sql.SQLException;

import com.Hotel.io.Project.domain.Booking;
import com.Hotel.io.Project.domain.User;
import org.springframework.jdbc.core.RowMapper;


public class  BookingRowMapper implements RowMapper<Booking> {
    @Override
    public Booking mapRow(ResultSet row, int rowNum) throws SQLException {
        Booking booking = new Booking();
        booking.setId(row.getInt("BookingId"));
        booking.setCheckin(row.getString("checkin"));
        booking.setCheckout(row.getString("checkout"));
        return booking;
    }
}
