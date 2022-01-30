package com.example.openaccessbackend.repository;

import com.example.openaccessbackend.model.Answer;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AnswerRepository extends JpaRepository<Answer, Long> {


    List<Answer> findByQuestionId(Long questionId);


    Answer findByresponse(String response);

}