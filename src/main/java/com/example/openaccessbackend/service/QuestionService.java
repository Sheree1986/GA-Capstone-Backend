package com.example.openaccessbackend.service;


import com.example.openaccessbackend.controller.QuestionController;
import com.example.openaccessbackend.exception.InformationExistException;
import com.example.openaccessbackend.exception.InformationNotFoundException;
import com.example.openaccessbackend.model.Question;
import com.example.openaccessbackend.model.Quiz;
import com.example.openaccessbackend.repository.QuestionRepository;
import com.example.openaccessbackend.repository.QuizRepository;
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

    private QuizRepository quizRepository;

    @Autowired
    public void setQuizRepository(QuizRepository quizRepository) {
        this.quizRepository = quizRepository;
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
            question.get().setA(questionObject.getA());
            question.get().setB(questionObject.getB());
            question.get().setC(questionObject.getC());
            question.get().setD(questionObject.getD());
            question.get().setCorrect(questionObject.getCorrect());
            question.get().setQuiz(questionObject.getQuiz());
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


// ===============================================Quiz=========================================================

    //6 -> Get all quizzes http://localhost:9092/api/quiz/
    public List<Quiz> getQuiz() {
        LOGGER.info("service calling getQuestion==>");
        return quizRepository.findAll();
    }

    //7 -> Get a single quiz http://localhost:9092/api/quiz/1

    public Optional getQuiz(Long quizId) {
        LOGGER.info("service calling getQuiz==>");
        Optional quiz = quizRepository.findById(quizId);
        if (quiz.isPresent()) {
            return quiz;
        } else {
            throw new InformationNotFoundException("quiz with id " + quizId + " not found");
        }
    }

    //8 -> Post/Create a question and add an quiz http://localhost:9092/api/quiz

    public Quiz createQuiz(Quiz quizObject) {
        LOGGER.info("service calling createQuiz ==>");
        Quiz quiz = quizRepository.findByName(quizObject.getName());
        if (quiz !=null) {
            throw new InformationExistException("quiz with name " + quiz.getName() + " already exists");
        } else {
            return quizRepository.save(quizObject);
        }
    }

    public Quiz createQuestionQuiz(Long questionId, Quiz quizObject) {
        LOGGER.info("service calling updateQuestionQuiz method ==> ");
        try {
            // here we're trying to find the book
            Optional question = questionRepository.findById(questionId);
            // if the book is found, then attach it to the authorObject
            quizObject.setQuestion((Question) question.get());
            // we save the author with the book information
            return quizRepository.save(quizObject);
        } catch (NoSuchElementException e) {
            throw new InformationNotFoundException("Question with id " + questionId + "not found");
        }
    }





    //10 -> Delete an quiz http://localhost:9092/api/quiz/1




    //9 -> Put/Update an quiz http://localhost:9092/api/quiz/1
    public Quiz updateQuiz(Long quizId, Quiz quizObject) {
        LOGGER.info("service calling updateQuiz method ==> ");
        Optional<Quiz> quiz = quizRepository.findById(quizId);
        if (quiz.isPresent()) {
            if (quizObject.getName().equals(quiz.get().getName())) {
                throw new InformationExistException("quiz with name " + quiz.get()
                        .getName() + " already exist");
            } else {
                Quiz updateQuiz = quizRepository.findById(quizId).get();
                updateQuiz.setName(quizObject.getName());
                updateQuiz.setDescription(quizObject.getDescription());
                updateQuiz.setQuestions(quizObject.getQuestions());
                return quizRepository.save(updateQuiz);
            }
        } else {
            throw new InformationNotFoundException("quiz with id " + quizId + " not found");
        }
    }
        public Quiz updateQuestionQuiz(Long questionId, Quiz quizObject) {
            LOGGER.info("service calling updateQuestionQuiz method ==> ");
            try {
                // here we're trying to find the book
                Optional question = questionRepository.findById(questionId);
                // if the book is found, then attach it to the authorObject
                quizObject.setQuestion((Question) question.get());
                // we save the author with the book information
                return quizRepository.save(quizObject);
            } catch (NoSuchElementException e) {
                throw new InformationNotFoundException("Question with id " + questionId + "not found");
            }
        }





    //10 -> Delete an quiz http://localhost:9092/api/quiz/1
    public Optional<Quiz> deleteQuiz(Long quizId) {
        LOGGER.info("calling deleteQuiz method ==>");
        Optional<Quiz> quiz = quizRepository.findById(quizId);
        if (quiz.isPresent()) {
            quizRepository.deleteById(quizId);
            return quiz;
        } else {
            throw new InformationNotFoundException("quiz with id: " + quizId + " not found");
        }
    }
}