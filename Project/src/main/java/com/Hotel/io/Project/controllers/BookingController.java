package com.Hotel.io.Project.controllers;

import com.Hotel.io.Project.domain.Booking;
import com.Hotel.io.Project.domain.Registry;
import com.Hotel.io.Project.domain.Room;
import com.Hotel.io.Project.domain.User;
import com.Hotel.io.Project.services.BookingService;
import com.Hotel.io.Project.services.RoomService;
import com.Hotel.io.Project.utility.MailConstructor;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.support.ManagedMap;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@CrossOrigin(origins = "http://192.168.43.204:4200")
@RequestMapping(value = "/booking")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @Autowired
    private MailConstructor mailConstructor;

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private RoomService roomService;

    @Autowired
    private  JdbcTemplate  jdbcTemplate;



    @GetMapping("/booking/{id}")
    public ResponseEntity<Booking> getArticleById(@PathVariable("id") Integer id) {
        Booking article = bookingService.getBookingById(id);
        return new ResponseEntity<Booking>(article, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Booking>> getAllArticles() {
        List<Booking> list = bookingService.getAllBookings();
        return new ResponseEntity<List<Booking>>(list, HttpStatus.OK);
    }
//    @RequestMapping(value = "/book",method =RequestMethod.POST)
//    public ResponseEntity addArticle(@RequestBody Booking booking) {
//        //boolean flag = bookingService.addBooking(booking);
////        if (flag == false) {
////            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
////        }
////        //HttpHeaders headers = new HttpHeaders();
//        //return new ResponseEntity(headers, HttpStatus.OK);
//        return  bookingService.addBooking(booking);
//
//    }

    @PostMapping(value = "/save",consumes ={"application/json"},produces = {"application/json"})
    public Booking savebooking(@RequestBody Booking booking) {
        //List<Room> room =new ArrayList<Room>(hotel.getRooms());
        System.out.println(booking);


     //   SimpleMailMessage newEmail=mailConstructor.contructNewUserEmail(user,password);
       // mailSender.send(newEmail);


        return bookingService.addBooking(booking);
    }

    @PostMapping(value = "/rooms",consumes ={"application/json"})
    public ResponseEntity<Map<String,String>> booking(@RequestBody Map<String,Object> mapper)throws Exception
    {


//        System.out.println(mapper.get("checkin").toString());
//        String sDate1="31/12/1998";
//        //Date checkin=new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss").parse(mapper.get("checkin").toString());
        //Date checkout=new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss").parse(mapper.get("checkout").toString());
//        Date checkin=new Date(Date.parse(mapper.get("checkin").toString()));
//        Date checkout=new Date(Date.parse(mapper.get("checkout").toString()));
        int bill= (int) mapper.get("bill");
        //Object bill=mapper.get("bill");
      //  Date  checkin=(Date)
       String checkout=mapper.get("checkout").toString();
        String checkin=mapper.get("checkin").toString();
        String hotel_id=mapper.get("hotel_id").toString();
        int user_id= (int) mapper.get("user_id");
      //  String.valueOf(checkin);

        String sql = "INSERT INTO booking ( checkin,checkout,user_id,bill) values (?,?,?, ?)";
        jdbcTemplate.update(sql, checkin,checkout,user_id,bill);


   setcheck(checkin,checkout,hotel_id,String.valueOf(user_id));



         sql = "select id from HotelDB.booking where checkin = ? AND user_id=? AND checkout=?;";
        String booking_id = (String) jdbcTemplate.queryForObject(sql,String.class,checkin,user_id,checkout);
      Map<String,String> book=new ManagedMap<>();
      book.put("booking_id",booking_id);
        return new ResponseEntity(book,HttpStatus.OK);

    }



    void setcheck(String checkin,String checkout,String hotel_id,String user_id)
    {

        String sql = "SELECT hotelname FROM hotel WHERE id=?";
        String hotelname = (String) jdbcTemplate.queryForObject(sql,String.class,hotel_id);
        System.out.println(hotelname);

         sql = "SELECT email FROM user WHERE id=?";
        String email = (String) jdbcTemplate.queryForObject(sql,String.class,user_id);
        sql = "SELECT firstname FROM user WHERE id=?";
        String firstname = (String) jdbcTemplate.queryForObject(sql,String.class,user_id);
        System.out.println(email);
        sql = "select id from HotelDB.booking where checkin = ? AND user_id=? AND checkout=?;";
        String booking_id = (String) jdbcTemplate.queryForObject(sql,String.class,checkin,user_id,checkout);

        SimpleMailMessage newEmail=mailConstructor.contructConfirmEmail( firstname,email, hotelname,checkin, checkout,booking_id);
        mailSender.send(newEmail);

    }


}
