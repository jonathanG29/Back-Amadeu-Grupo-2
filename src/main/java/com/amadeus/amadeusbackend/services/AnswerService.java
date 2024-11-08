package com.amadeus.amadeusbackend.services;

import com.amadeus.amadeusbackend.repositories.AnswerRepository;
import com.amadeus.amadeusbackend.models.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AnswerService {

    @Autowired
    private AnswerRepository answerRepository;
    @Autowired
    private CalculateDestinyService calculateDestinyService;

    public Answer saveAnswer(Answer answer) {

        calculateDestinyService.CalculateDestiny(answer);

        return answerRepository.save(answer);

    }

}
