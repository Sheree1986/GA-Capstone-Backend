
package com.example.openaccessbackend.repository;

import com.example.openaccessbackend.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;


public interface QuestionRepository extends JpaRepository<Question, Long> {
    Question findByText(String text);


}