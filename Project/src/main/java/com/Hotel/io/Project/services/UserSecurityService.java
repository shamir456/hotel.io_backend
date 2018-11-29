package com.Hotel.io.Project.services;


import com.Hotel.io.Project.domain.User;
import com.Hotel.io.Project.repository.UserRepository;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;



@Service
public class UserSecurityService implements UserDetailsService
{
    private static final Logger LOG =  LoggerFactory.getLogger(UserSecurityService.class);
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    User user =userRepository.findByUsername(username);
    if (null==user)
    {
        LOG.warn("Username {} not found",username);
    }
      return user;
    }

}
