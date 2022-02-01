package com.example.openaccessbackend.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Set;


@Entity
@Table(name = "quiz")
public class Quiz {

    @Id
    @Column(name="quiz_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="quiz_name")
    private String name;
    @Column
    private String description;
    @Column
    private String questions;
    @JsonIgnoreProperties("quiz")
    @ManyToMany (cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "quiz_question", joinColumns = {@JoinColumn(name = "quiz_id")},
            inverseJoinColumns = @JoinColumn(name = "question_id"))

    private Set<Question> question;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getQuestions() {
        return questions;
    }

    public void setQuestions(String questions) {
        this.questions = questions;
    }

    public Set<Question> getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.getQuestion().add(question);
        question.getQuiz().add(this);
    }

    public void addQuestion(Question question) {
        this.question.add(question);
        question.getQuiz().add(this);
    }

    public void removeQuestion(Question question) {
        this.question.remove(question);
        question.getQuiz().remove(this);
    }
}
