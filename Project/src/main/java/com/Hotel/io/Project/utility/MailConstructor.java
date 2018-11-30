package com.Hotel.io.Project.utility;

import com.Hotel.io.Project.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;

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

}
