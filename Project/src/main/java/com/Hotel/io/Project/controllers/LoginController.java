package com.Hotel.io.Project.controllers;


import com.Hotel.io.Project.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Collections;
import java.util.Map;


@CrossOrigin(origins = "http://192.168.0.107:4200")
@RestController
public class LoginController
{
    @Autowired
    private UserService userService;

    @CrossOrigin(origins = "http://192.168.0.107:4200")
    @RequestMapping(value = "/token")
    public Map<String,String> token(HttpSession session, HttpServletRequest request)
    {
        System.out.println(request.getRemoteHost());

        String remoteHost=request.getRemoteHost();

        int portnumber=request.getRemotePort();
        System.out.println(remoteHost+":"+portnumber);
        System.out.println(request.getRemoteAddr());

        return Collections.singletonMap("token",session.getId());
    }
    @CrossOrigin(origins = "http://192.168.0.107:4200")
    @RequestMapping("/checkSession")
    public ResponseEntity checkSession()
    {
        return new ResponseEntity("Session Active!", HttpStatus.OK);
    }

    @RequestMapping(value = "/user/logout",method = RequestMethod.POST)
    public ResponseEntity logout()
    {
        SecurityContextHolder.clearContext();
        return new ResponseEntity("Logout Successfully",HttpStatus.OK);
    }

}
