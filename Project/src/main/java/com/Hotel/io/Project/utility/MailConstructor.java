package com.Hotel.io.Project.utility;

import com.Hotel.io.Project.domain.Booking;
import com.Hotel.io.Project.domain.Hotel;
import com.Hotel.io.Project.domain.Room;
import com.Hotel.io.Project.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Component;

import javax.mail.internet.MimeMessage;
import java.util.List;
import java.util.Locale;

@Component
public class MailConstructor
{
    @Autowired
    private Environment env;

    public SimpleMailMessage contructNewUserEmail(User user, String password)
    {
        String message="\n Please use the folloing credentials to log in:"+"\nUsername: "+user.getUsername()+"\nPassword: "+password;

        SimpleMailMessage email=new SimpleMailMessage();
        email.setTo(user.getEmail());
        email.setSubject("Hotel.io");
        email.setText(message);
        email.setFrom(env.getProperty("support.email"));
        return email;

    }

    public SimpleMailMessage contructConfirmEmail(String firstname,String user_mail,String hotel_name,String checkin,String checkout,String booking_id )
    {
        String message="\n Your Booking has been made on Hotel: "+hotel_name+"\n"+"Checking Date: "+checkin+"\n"+"CheckoutDate: "+checkout+"\n"+"Booking By Name:"+firstname+"\n"+"Booking By Booking Id:"+booking_id+"\n";
        SimpleMailMessage email=new SimpleMailMessage();
        email.setTo(user_mail);
        email.setSubject("Booking of Rooms");
        email.setText(message);
        email.setFrom(env.getProperty("support.email"));
        return email;



    }
//    public MimeMessagePreparator constructOrderConfirmationEmail (User user, List<Room> roomList, Locale locale) {
//        Context context = new Context();
//        context.setVariable("order", order);
//        context.setVariable("user", user);
//        context.setVariable("cartItemList", order.getCartItemList());
//        String text = templateEngine.process("orderConfirmationEmailTemplate", context);
//
//        MimeMessagePreparator messagePreparator = new MimeMessagePreparator() {
//            @Override
//            public void prepare(MimeMessage mimeMessage) throws Exception {
//                MimeMessageHelper email = new MimeMessageHelper(mimeMessage);
//                email.setTo(user.getEmail());
//                email.setSubject("Order Confirmation - "+order.getId());
//                email.setText(text,true);
//                email.setFrom(new InternetAddress("ray.deng83@gmail.com"));
//            }
//        };
//
//        return messagePreparator;
//    }


}
