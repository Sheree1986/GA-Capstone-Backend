package com.example.openaccessbackend.repository;


import com.example.openaccessbackend.model.User;

import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, Long> {



    User findByName(String name);
}