package com.example.openaccessbackend.controller;

import com.example.openaccessbackend.model.Question;
import com.example.openaccessbackend.model.Answer;
import com.example.openaccessbackend.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;


@RestController
@RequestMapping(path = "/api")
public class QuestionController {
    private static final Logger LOGGER = Logger.getLogger(QuestionController.class.getName());
    private QuestionService questionService;

    @Autowired
    public void setQuestionService(QuestionService questionService) {
        this.questionService = questionService;
    }
    //creating the two endpoints - (Question, Answer)



//==========================================Book==============================================

    //1 -> GET all question http://localhost:9092/api/questions
    @GetMapping("/questions")
    public List<Question> getQuestion() {
        LOGGER.info(" calling getQuestion method from controller");

        return questionService.getQuestions();
    }

    //2 -> Get one question http://localhost:9092/api/questions/1
    @GetMapping("/questions/{questionId}")
    public Optional getQuestion(@PathVariable Long questionId) {
        LOGGER.info(" calling getQuestion method from controller");
        return questionService.getQuestion(questionId);
    }


    //3 -> Post/Create a question http://localhost:9092/api/questions
    @PostMapping(path = "/questions/")
    public Question createQuestion(@RequestBody Question questionObject) {
        LOGGER.info("calling createQuestion method from controller ");
        return questionService.createQuestion(questionObject);
    }


    //4 -> Put/Update a question http://localhost:9092/api/question/1
    @PutMapping(path = "/questions/{questionId}")
    public Question updateQuestion(@PathVariable(
            value = "questionId") Long questionId, @RequestBody Question questionObject) {
        LOGGER.info("calling updateQuestion method from controller");
        return questionService.updateQuestion(questionId, questionObject);
    }


    //5 -> Delete a question http://localhost:9092/api/questions/1
    @DeleteMapping(path = "/questions/{questionId}")
    public Optional<Question> deleteQuestion(@PathVariable(value = "questionId") Long questionId) {
        LOGGER.info("calling deleteQuestion method from controller");
        return questionService.deleteQuestion(questionId);
    }


//===========================================Answer================================================================


    //6 -> Get all answers http://localhost:9092/api/answers
    @GetMapping("/answers")
    public List<Answer> getAnswers() {
        LOGGER.info(" calling getAnswers method from controller");
        return questionService.getAnswers();
    }

    //7 -> Get a single answer http://localhost:9092/api/answers/{answerId}
    @GetMapping("/answers/{answerId}")
    public Optional getAnswer(@PathVariable Long answerId) {
        LOGGER.info(" calling getAnswer method from controller");
        return questionService.getAnswer(answerId);
    }


    //8 -> Post/Create question and add an answer to the question  http://localhost:9092/api/questions/1/answers
    @PostMapping(path = "/questions/{questionId}/answers")
    public Answer createAnswer(@PathVariable Long questionId, @RequestBody Answer answerObject) {
        LOGGER.info("calling createAnswer method from controller");
        return questionService.createAnswer(questionId, answerObject);
    }

    //9 -> Put/Update an answer http://localhost:9092/api/answers/1

    @PutMapping(path = "/answers/{answerId}")
    public Answer updateAnswer(@PathVariable(
            value = "answerId") Long answerId, @RequestBody Answer answerObject) {
        LOGGER.info("calling updateAnswer method from controller");
        return questionService.updateAnswer(answerId, answerObject);
    }


    //10 -> Delete a answer http://localhost:9092/api/questions/1
    @DeleteMapping(path = "/answers/{answerId}")
    public Optional<Answer> deleteAnswer(@PathVariable(value = "answerId") Long answerId) {
        LOGGER.info("calling deleteAnswer method from controller");
        return questionService.deleteAnswer(answerId);
    }
}
