package com.amadeus.amadeusbackend.controllers;


import com.amadeus.amadeusbackend.models.Answer;
import com.amadeus.amadeusbackend.models.Result;
import com.amadeus.amadeusbackend.services.ResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1")
public class ResultController {

    @Autowired
    private ResultService resultService;

    @PostMapping("/result")
    public ResponseEntity<Result> saveResult (@RequestBody Result result) {

        try {
            Result savedResult = resultService.saveResult(result);
            return new ResponseEntity<>(savedResult, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/result/{answer}")
    public ResponseEntity<Result> findByAnswer(@PathVariable Answer answer){

        try {
            Result findResult = resultService.findByAnswer(answer);
            return new ResponseEntity<>(findResult, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

}
