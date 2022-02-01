package com.example.openaccessbackend.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.List;
import java.util.Set;


@Entity
@Table(name = "question")
public class Question {
    @Id
    @Column(name = "question_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "quesiton_text")
    private String text;

    @Column
    private String a;

    @Column
    private String b;
    @Column
    private String c;
    @Column
    private String d;
    @Column
    private String correct;

    @JsonIgnoreProperties("question")
    @ManyToMany(mappedBy = "question", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Set<Quiz> quiz;
    public Question(){}
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getA() {
        return a;
    }

    public void setA(String a) {
        this.a = a;
    }

    public String getB() {
        return b;
    }

    public void setB(String b) {
        this.b = b;
    }

    public String getC() {
        return c;
    }

    public void setC(String c) {
        this.c = c;
    }

    public String getD() {
        return d;
    }

    public void setD(String d) {
        this.d = d;
    }

    public String getCorrect() {
        return correct;
    }

    public void setCorrect(String correct) {
        this.correct = correct;
    }
    public Set<Quiz> getQuiz() {
        return quiz;
    }

    public void setQuiz(Set<Quiz> quiz) {
        this.quiz = quiz;
    }

    public void addQuiz(Quiz quiz) {
        this.quiz.add(quiz);
        quiz.getQuestion().add(this);
    }

    public void removeQuiz(Quiz quiz) {
        this.quiz.remove(quiz);
        quiz.getQuestion().remove(this);
    }
}