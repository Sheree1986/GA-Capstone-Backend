package com.example.openaccessbackend.repository;


import com.example.openaccessbackend.model.Quiz;

import org.springframework.data.jpa.repository.JpaRepository;


public interface QuizRepository extends JpaRepository<Quiz, Long> {



    Quiz findByName(String name);
}