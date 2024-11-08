package com.amadeus.amadeusbackend.controllers;

import com.amadeus.amadeusbackend.models.Answer;
import com.amadeus.amadeusbackend.services.AnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1")
public class AnswerController {

    @Autowired
    private AnswerService answerService;

    @PostMapping("/answer")
    public ResponseEntity<Answer> saveAnswer (@RequestBody Answer answer) {

        try {
            Answer savedAnswer = answerService.saveAnswer(answer);
            return new ResponseEntity<>(savedAnswer, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }


}
