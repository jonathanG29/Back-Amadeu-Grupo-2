package com.amadeus.amadeusbackend.services;

import com.amadeus.amadeusbackend.repositories.AnswerRepository;
import com.amadeus.amadeusbackend.models.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AnswerService {

    @Autowired
    private AnswerRepository answerRepository;

    public Answer saveAnswer(Answer answer) {
        answerRepository.save(answer);
        return answer;
    }
}
