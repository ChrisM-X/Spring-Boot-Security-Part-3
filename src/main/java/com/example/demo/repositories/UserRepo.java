package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.example.demo.models.User;

@Service
public interface UserRepo extends JpaRepository<User, Integer>{

    User findByUsername(String username);

} // end interface
