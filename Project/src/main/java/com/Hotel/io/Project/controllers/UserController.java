package com.Hotel.io.Project.controllers;

import com.Hotel.io.Project.config.SecurityConfig;
import com.Hotel.io.Project.config.SecurityUtility;
import com.Hotel.io.Project.domain.User;
import com.Hotel.io.Project.domain.security.Role;
import com.Hotel.io.Project.domain.security.UserRole;
import com.Hotel.io.Project.services.UserService;
import com.Hotel.io.Project.utility.MailConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

@CrossOrigin(origins = "http://192.168.0.101:4200")
@RestController
@RequestMapping("/user")
public class UserController
{
    @Autowired
    private UserService userService;

    @Autowired
    private MailConstructor mailConstructor;

    @Autowired
    private JavaMailSender mailSender;

    @RequestMapping(value="/newUser",method = RequestMethod.POST)
    public ResponseEntity newUserPost(
            HttpServletRequest request,
            @RequestBody HashMap<String,String> mapper

            )throws Exception {
        String username=mapper.get("username");
        String userEmail=mapper.get("email");

        if(userService.findByUsername(username)!=null)
        {
            return new ResponseEntity("usernameExists", HttpStatus.BAD_REQUEST);
        }
        if(userService.findByEmail(userEmail)!=null)
        {
            return new ResponseEntity("mailExists", HttpStatus.BAD_REQUEST);
        }

        User user=new User();
        user.setUsername(username);
        user.setEmail(userEmail);

        String password= SecurityUtility.randomPassword();

        String encryptedPassword= SecurityUtility.passwordEncoder().encode(password);
        user.setPassword(encryptedPassword);

        Role role=new Role();
        role.setRoleId(1);
        role.setName("ROLE_USER");
        Set<UserRole> userRoles=new HashSet<>();
        userService.createUser(user,userRoles);

        SimpleMailMessage email=mailConstructor.contructNewUserEmail(user,password);
        mailSender.send(email);


        return new ResponseEntity("User Added Successfully",HttpStatus.OK);
    }


    @CrossOrigin(origins = "http://192.168.0.101:4200")
    @RequestMapping(value="/forgotpassword",method = RequestMethod.POST)
    public ResponseEntity forgotPasswordPost(
            HttpServletRequest request,
            @RequestBody HashMap<String,String> mapper

    )throws Exception {


         User user=userService.findByEmail(mapper.get("email"));
        if(user==null)
        {
            return new ResponseEntity("email not found", HttpStatus.BAD_REQUEST);
        }


         String password= SecurityUtility.randomPassword();

        String encryptedPassword= SecurityUtility.passwordEncoder().encode(password);
        user.setPassword(encryptedPassword);


        SimpleMailMessage newEmail=mailConstructor.contructNewUserEmail(user,password);
        mailSender.send(newEmail);


        return new ResponseEntity("Email Sent",HttpStatus.OK);
    }

    @RequestMapping(value = "/updateUserInfo",method = RequestMethod.POST)
    public ResponseEntity profileInfo(
            @RequestBody HashMap<String,Object> mapper
    )throws Exception{
        int id=(Integer)mapper.get("id");
        String email=(String)mapper.get("email");
        String username=(String)mapper.get("username");
        String firstname=(String) mapper.get("firstname");
        String lastname=(String)mapper.get("lastname");
        String newPassword=(String)mapper.get("newPassword");
        String currentPassword=(String)mapper.get("currentPassword");

        User currentUser=userService.findById(Long.valueOf(id));
        if(currentUser==null)
        {
            throw new Exception("User not found");
        }
        if (userService.findByEmail(email)!=null)
        {
            if (userService.findByEmail(email).getId()!=currentUser.getId())
            {
                return new ResponseEntity("Email not found",HttpStatus.BAD_REQUEST);

            }

        }


        if (userService.findByUsername(username)!=null)
        {
            if (userService.findByUsername(username).getId()!=currentUser.getId())
            {
                return new ResponseEntity("username not found",HttpStatus.BAD_REQUEST);

            }

        }
        SecurityConfig securityConfig=new SecurityConfig();

        if(newPassword!=null && !newPassword.isEmpty() && !newPassword.equals("")) {
            BCryptPasswordEncoder passwordEncoder = SecurityUtility.passwordEncoder();
            String dbpassword = currentUser.getPassword();
            if (currentPassword.equals(dbpassword))
            {
                currentUser.setPassword(passwordEncoder.encode(newPassword));
            }
            else {
                return new ResponseEntity("Incorrect password",HttpStatus.BAD_REQUEST);


            }

        }

        currentUser.setFirstname(firstname);
        currentUser.setLastname(lastname);
        currentUser.setUsername(username);
        currentUser.setEmail(email);

        userService.save(currentUser);

        return new ResponseEntity("Updated",HttpStatus.OK);





    }


}
