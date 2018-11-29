package com.Hotel.io.Project.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.security.SecureRandom;
import java.util.Random;

@Component
public class SecurityUtility
{
    private static final  String SALT="salt";// SALT should be protected

    @Bean
    public static BCryptPasswordEncoder passwordEncoder()
    {
        return new BCryptPasswordEncoder(12,new SecureRandom(SALT.getBytes()));
    }

    @Bean
    public static String randomPassword()
    {
        String SALTCHARS = "ABCDEFGHJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder salt= new StringBuilder();

        Random rnd =new Random();

        while (salt.length() <18)
        {
            int index =(int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }

        String saltStr =salt.toString();
        return saltStr;
    }

}
