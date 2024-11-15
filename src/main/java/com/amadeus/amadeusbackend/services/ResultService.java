package com.amadeus.amadeusbackend.services;


import com.amadeus.amadeusbackend.models.Answer;
import com.amadeus.amadeusbackend.models.Result;
import com.amadeus.amadeusbackend.repositories.ResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class ResultService {

    @Autowired
    private ResultRepository resultRepository;

    @Autowired
    private CalculateDestinyService calculateDestinyService;


    public Result saveResult(Result result) {
        return resultRepository.save(result);
    }

    public Result findByAnswer(Answer answer) {
        return resultRepository.findByAnswer(answer);
    }



}
