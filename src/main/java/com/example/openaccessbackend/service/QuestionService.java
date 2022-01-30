package com.example.openaccessbackend.service;


import com.example.openaccessbackend.controller.QuestionController;
import com.example.openaccessbackend.exception.InformationExistException;
import com.example.openaccessbackend.exception.InformationNotFoundException;
import com.example.openaccessbackend.model.Question;
import com.example.openaccessbackend.model.Answer;
import com.example.openaccessbackend.repository.QuestionRepository;
import com.example.openaccessbackend.repository.AnswerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.logging.Logger;


@Service

public class QuestionService {
    private static final Logger LOGGER = Logger.getLogger(QuestionController.class.getName());

    private QuestionRepository questionRepository;


    @Autowired
    public void setQuestionRepository(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    private AnswerRepository answerRepository;

    @Autowired
    public void setAnswerRepository(AnswerRepository answerRepository) {
        this.answerRepository = answerRepository;
    }





    // ===============================================QUESTION=======================================================


    //1 -> GET all questions  http://localhost:9092/api/questions/
    public List<Question> getQuestions() {
        LOGGER.info("service calling getQuestion==>");
        return questionRepository.findAll();
    }

    //2 -> Get one question http://localhost:9092/api/questions/1
    public Optional getQuestion(Long questionId) {
        LOGGER.info("service calling getQuestion==>");
        Optional question = questionRepository.findById(questionId);
        if (question.isPresent()) {
            return question;
        } else {
            throw new InformationNotFoundException("question with id " + questionId + " not found");
        }
    }

    //3 -> Post/Create a question http://localhost:9092/api/questions/
    public Question createQuestion(Question questionObject) {
        LOGGER.info("service calling createQuestion ==>");
        Question question = questionRepository.findByText(questionObject.getText());
        if (question != null) {
            throw new InformationExistException("question with name " + question.getText() + " already exists");
        } else {
            return questionRepository.save(questionObject);
        }
    }

    //4 -> Put/Update a question http://localhost:9092/api/questions/1

    public Question updateQuestion(Long questionId, Question questionObject) {
        LOGGER.info("service calling updateQuestion method ==> ");
        Optional<Question> question = questionRepository.findById(questionId);
        if (question == null) {
            throw new InformationNotFoundException("question with id " + questionId + " not found");


        } else {
            question.get().setText(questionObject.getText());
            return questionRepository.save(question.get());
        }
    }

    //5 -> Delete a question http://localhost:9092/api/questions/1

    public Optional<Question> deleteQuestion(Long questionId) {
        LOGGER.info("calling deleteQuestion method ==>");
        Optional<Question> question = questionRepository.findById(questionId);
        if (question.isPresent()) {
            questionRepository.deleteById(questionId);
            return question;
        } else {
            throw new InformationNotFoundException("question with id: " + questionId + " not found");
        }
    }


// ===============================================ANSWER=========================================================

    //6 -> Get all answers http://localhost:9092/api/answers/
    public List<Answer> getAnswers() {
        LOGGER.info("service calling getQuestion==>");
        return answerRepository.findAll();
    }

    //7 -> Get a single answer http://localhost:9092/api/answers/1

    public Optional getAnswer(Long answerId) {
        LOGGER.info("service calling getAnswer==>");
        Optional answer = answerRepository.findById(answerId);
        if (answer.isPresent()) {
            return answer;
        } else {
            throw new InformationNotFoundException("answer with id " + answerId + " not found");
        }
    }

    //8 -> Post/Create a question and add an answer http://localhost:9092/api/questions/1/answers

    public Answer createAnswer(Long questionId, Answer answerObject) {
        LOGGER.info("service calling createAnswer ==>");
        try {
            // here we're trying to find the question
            Optional question = questionRepository.findById(questionId);
            // if the question is found, then attach it to the answerObject
            answerObject.setQuestion((Question) question.get());
            // we save the answer with the question information
            return answerRepository.save(answerObject);
        } catch (NoSuchElementException e) {
            throw new InformationNotFoundException("Question with id " + questionId + "not found");
        }
    }

    //9 -> Put/Update an answer http://localhost:9092/api/answers/1
    public Answer updateAnswer(Long answerId, Answer answerObject) {
        LOGGER.info("service calling updateAnswer method ==> ");
        Optional<Answer> answer = answerRepository.findById(answerId);
        if (answer.isPresent()) {
            if (answerObject.getResponse().equals(answer.get().getResponse())) {
                throw new InformationExistException("answer with name " + answer.get()
                        .getResponse() + " already exist");
            } else {
                Answer updateAnswer = answerRepository.findById(answerId).get();
                updateAnswer.setResponse(answerObject.getResponse());
                return answerRepository.save(updateAnswer);
            }
        } else {
            throw new InformationNotFoundException("answer with id " + answerId + " not found");
        }

    }

    //10 -> Delete an answer http://localhost:9092/api/answer/1
    public Optional<Answer> deleteAnswer(Long answerId) {
        LOGGER.info("calling deleteAnswer method ==>");
        Optional<Answer> answer = answerRepository.findById(answerId);
        if (answer.isPresent()) {
            answerRepository.deleteById(answerId);
            return answer;
        } else {
            throw new InformationNotFoundException("answer with id: " + answerId + " not found");
        }
    }
}