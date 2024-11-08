package com.amadeus.amadeusbackend.controllers;


import com.amadeus.amadeusbackend.services.ResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/v1")
public class ResultController {

    @Autowired
    private ResultService resultService;


}
