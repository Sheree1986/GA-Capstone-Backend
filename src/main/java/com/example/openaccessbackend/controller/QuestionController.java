package com.example.openaccessbackend.controller;

import com.example.openaccessbackend.model.Question;
import com.example.openaccessbackend.model.Quiz;
import com.example.openaccessbackend.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;


@RestController
@RequestMapping(path = "/api")
@ResponseStatus(value = HttpStatus.OK)
public class QuestionController {
    private static final Logger LOGGER = Logger.getLogger(QuestionController.class.getName());
    private QuestionService questionService;

    @Autowired
    public void setQuestionService(QuestionService questionService) {
        this.questionService = questionService;
    }
    //creating the two endpoints - (Question, Quiz)


//==========================================Questions==============================================

    @GetMapping("/")
//    @ResponseStatus(value = HttpStatus.OK)
    public String working(){
        LOGGER.info(" calling getQuestion method from controller is working");

        return "<p>Testing working</p>";
    }
    //1 -> GET all question http://localhost:9092/api/questions
    @GetMapping("/question")
    @ResponseStatus(value = HttpStatus.OK)
    public List<Question> getQuestion() {
        LOGGER.info(" calling getQuestion method from controller");

        return questionService.getQuestions();
    }

    //2 -> Get one question http://localhost:9092/api/questions/1
    @GetMapping("/question/{questionId}")
//    @ResponseStatus(value = HttpStatus.OK)
    public Optional getQuestion(@PathVariable Long questionId) {
        LOGGER.info(" calling getQuestion method from controller");
        return questionService.getQuestion(questionId);
    }


    //3 -> Post/Create a question http://localhost:9092/api/questions
    @PostMapping(path = "/question")
//    @ResponseStatus(value = HttpStatus.OK)
    public Question createQuestion(@RequestBody Question questionObject) {
        LOGGER.info("calling createQuestion method from controller ");
        return questionService.createQuestion(questionObject);
    }
    @PutMapping(path = "/quiz/question/{quizId}")
//    @ResponseStatus(value = HttpStatus.OK)
    public Quiz createQuestionQuiz(@PathVariable(value = "quizId") Long questionId,
                                   @RequestBody Quiz quizObject) {
        LOGGER.info("calling createQuestionQuiz method from controller");
        return questionService.createQuestionQuiz(questionId, quizObject);
    }


    //4 -> Put/Update a question http://localhost:9092/api/question/1
    @PutMapping(path = "/question/{questionId}")
//    @ResponseStatus(value = HttpStatus.OK)
    public Question updateQuestion(@PathVariable(
            value = "questionId") Long questionId, @RequestBody Question questionObject) {
        LOGGER.info("calling updateQuestion method from controller");
        return questionService.updateQuestion(questionId, questionObject);
    }
    // PUT Update quiz a question  "/quiz/question/{quizID}"
//    @PutMapping(path = "/quiz/question/{quizId}")
//    public Quiz updateQuestionQuiz(@PathVariable(value = "quizId") Long questionId,
//            @RequestBody Quiz quizObject) {
//        LOGGER.info("calling updateQuestionQuiz method from controller");
//        return questionService.updateQuestionQuiz(questionId, quizObject);
//    }


    //5 -> Delete a question http://localhost:9092/api/questions/1
    @DeleteMapping(path = "/question/{questionId}")
//    @ResponseStatus(value = HttpStatus.OK)
    public Optional<Question> deleteQuestion(@PathVariable(value = "questionId") Long questionId) {
        LOGGER.info("calling deleteQuestion method from controller");
        return questionService.deleteQuestion(questionId);
    }


////===========================================Quiz================================================================


    //6 -> Get all quizzes http://localhost:9092/api/quizzes
    @GetMapping("/quiz")
//    @ResponseStatus(value = HttpStatus.OK)
    public List<Quiz> getAnswers() {
        LOGGER.info(" calling getAnswers method from controller");
        return questionService.getQuiz();
    }

    //7 -> Get a single quiz http://localhost:9092/api/quizzes/{quizId}
    @GetMapping("/quizzes/{quizId}")
//    @ResponseStatus(value = HttpStatus.OK)
    public Optional getQuiz(@PathVariable Long quizId) {
        LOGGER.info(" calling getQuiz method from controller");
        return questionService.getQuiz(quizId);
    }


    //8 -> Post/Create quizzes and add an quiz to the quiz http://localhost:9092/api/quizzes
    @PostMapping(path = "/quiz")
//    @ResponseStatus(value = HttpStatus.OK)
    public Quiz createQuiz(@RequestBody Quiz quizObject) {
        LOGGER.info("calling createQuiz method from controller");
        return questionService.createQuiz(quizObject);
    }



    //9 -> Put/Update an quiz http://localhost:9092/api/quizzes/1

    @PutMapping(path = "/quiz/{quizId}")
//    @ResponseStatus(value = HttpStatus.OK)
    public Quiz updateQuiz(@PathVariable(
            value = "quizId") Long quizId, @RequestBody Quiz quizObject) {
        LOGGER.info("calling updateQuiz method from controller");
        return questionService.updateQuiz(quizId, quizObject);
    }


    //10 -> Delete a quiz http://localhost:9092/api/quizzes/1
    @DeleteMapping(path = "/quiz/{quizId}")
//    @ResponseStatus(value = HttpStatus.OK)
    public Optional<Quiz> deleteQuiz(@PathVariable(value = "quizId") Long quizId) {
        LOGGER.info("calling deleteQuiz method from controller");
        return questionService.deleteQuiz(quizId);
    }
}
