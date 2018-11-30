package com.Hotel.io.Project.repository;

import com.Hotel.io.Project.domain.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<User,Long> {
    User findByUsername(String username);
    User findByEmail(String email);
    List<User> findAll();
    User findById(long id);
}
