package com.Hotel.io.Project.services.impl;


import com.Hotel.io.Project.domain.User;
import com.Hotel.io.Project.domain.security.UserRole;
import com.Hotel.io.Project.repository.RoleRepository;
import com.Hotel.io.Project.repository.UserRepository;
import com.Hotel.io.Project.services.UserService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service
public class UserServiceImp implements UserService
{
    private static final Logger LOG = LoggerFactory.getLogger(UserService.class);
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;


    public User createUser(User user, Set<UserRole> userRoles)
    {
        User localUser =userRepository.findByUsername(user.getUsername());

        if (localUser!= null)
        {
            LOG.info("User with username {} already exist.",user.getUsername());
        }
        else
        {
            for (UserRole ur : userRoles)
            {
                roleRepository.save(ur.getRole());
            }
            user.getUserRole().addAll(userRoles);

            localUser =userRepository.save(user);
        }

        return localUser;

    }

}
