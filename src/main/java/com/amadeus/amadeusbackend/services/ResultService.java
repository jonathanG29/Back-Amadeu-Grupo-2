package com.amadeus.amadeusbackend.services;


import com.amadeus.amadeusbackend.models.Result;
import com.amadeus.amadeusbackend.repositories.ResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ResultService {

    @Autowired
    private ResultRepository resultRepository;


    public Result saveResult(Result result) {
        return resultRepository.save(result);
    }


}
