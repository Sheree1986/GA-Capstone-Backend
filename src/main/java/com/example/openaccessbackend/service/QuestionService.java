package com.example.openaccessbackend.service;


import com.example.openaccessbackend.controller.QuestionController;
import com.example.openaccessbackend.exception.InformationExistException;
import com.example.openaccessbackend.exception.InformationNotFoundException;
import com.example.openaccessbackend.model.Question;
import com.example.openaccessbackend.model.User;
import com.example.openaccessbackend.repository.QuestionRepository;
import com.example.openaccessbackend.repository.UserRepository;
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

    private UserRepository userRepository;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
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
            question.get().setImageTitle(questionObject.getImageTitle());
            question.get().setAnswer1(questionObject.getAnswer1());
            question.get().setAnswer2(questionObject.getAnswer2());
            question.get().setAnswer3(questionObject.getAnswer3());
            question.get().setAnswer4(questionObject.getAnswer4());
            question.get().setCorrect(questionObject.getCorrect());
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

    //6 -> Get all users http://localhost:9092/api/users/
    public List<User> getUsers() {
        LOGGER.info("service calling getQuestion==>");
        return userRepository.findAll();
    }

    //7 -> Get a single user http://localhost:9092/api/users/1

    public Optional getUser(Long userId) {
        LOGGER.info("service calling getUser==>");
        Optional user = userRepository.findById(userId);
        if (user.isPresent()) {
            return user;
        } else {
            throw new InformationNotFoundException("user with id " + userId + " not found");
        }
    }

    //8 -> Post/Create a question and add an user http://localhost:9092/api/users

    public User createUser(User userObject) {
        LOGGER.info("service calling createUser ==>");
        User user = userRepository.findByName(userObject.getName());
        if (user !=null) {
            throw new InformationExistException("user with name " + user.getName() + " already exists");
        } else {
            return userRepository.save(userObject);
        }
    }




    //9 -> Put/Update an user http://localhost:9092/api/users/1
    public User updateUser(Long userId, User userObject) {
        LOGGER.info("service calling updateUser method ==> ");
        Optional<User> user = userRepository.findById(userId);
        if (user.isPresent()) {
            if (userObject.getName().equals(user.get().getName())) {
                throw new InformationExistException("user with name " + user.get()
                        .getName() + " already exist");
            } else {
                User updateUser = userRepository.findById(userId).get();
                updateUser.setName(userObject.getName());
                updateUser.setEmail(userObject.getEmail());
                updateUser.setScore(userObject.getScore());
                updateUser.setTimer(userObject.getTimer());
                return userRepository.save(updateUser);
            }
        } else {
            throw new InformationNotFoundException("user with id " + userId + " not found");
        }

    }

    //10 -> Delete an user http://localhost:9092/api/user/1
    public Optional<User> deleteUser(Long userId) {
        LOGGER.info("calling deleteUser method ==>");
        Optional<User> user = userRepository.findById(userId);
        if (user.isPresent()) {
            userRepository.deleteById(userId);
            return user;
        } else {
            throw new InformationNotFoundException("user with id: " + userId + " not found");
        }
    }
}