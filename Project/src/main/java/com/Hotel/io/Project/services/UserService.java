package com.Hotel.io.Project.services;

import com.Hotel.io.Project.domain.User;
import com.Hotel.io.Project.domain.security.UserRole;

import java.util.Set;

public interface UserService
{
    User createUser(User user, Set<UserRole> userRoles);

}
