package com.example.openaccessbackend.controller;

import com.example.openaccessbackend.model.Question;
import com.example.openaccessbackend.model.User;
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
    //creating the two endpoints - (Questions, Users)



//==========================================Questions==============================================

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


    //6 -> Get all users http://localhost:9092/api/users
    @GetMapping("/users/")
    public List<User> getAnswers() {
        LOGGER.info(" calling getAnswers method from controller");
        return questionService.getUsers();
    }

    //7 -> Get a single user http://localhost:9092/api/users/{userId}
    @GetMapping("/users/{userId}")
    public Optional getUser(@PathVariable Long userId) {
        LOGGER.info(" calling getUser method from controller");
        return questionService.getUser(userId);
    }


    //8 -> Post/Create users and add an user to the user http://localhost:9092/api/users
    @PostMapping(path = "/users")
    public User createUser(@RequestBody User userObject) {
        LOGGER.info("calling createUser method from controller");
        return questionService.createUser(userObject);
    }


    //9 -> Put/Update an user http://localhost:9092/api/users/1

    @PutMapping(path = "/users/{userId}")
    public User updateUser(@PathVariable(
            value = "userId") Long userId, @RequestBody User userObject) {
        LOGGER.info("calling updateUser method from controller");
        return questionService.updateUser(userId, userObject);
    }


    //10 -> Delete a user http://localhost:9092/api/questions/1
    @DeleteMapping(path = "/users/{userId}")
    public Optional<User> deleteUser(@PathVariable(value = "userId") Long userId) {
        LOGGER.info("calling deleteUser method from controller");
        return questionService.deleteUser(userId);
    }
}
