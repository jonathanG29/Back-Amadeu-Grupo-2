package com.amadeus.amadeusbackend.controllers;

import com.amadeus.amadeusbackend.contracts.response.DestinationResponse;
import com.amadeus.amadeusbackend.services.CalculateDestinyService;
import com.amadeus.amadeusbackend.contracts.request.DestinationRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1")
public class AnswerController {

    @Autowired
    private CalculateDestinyService calculateDestinyService;

    @PostMapping("/enviarDestino")
    public ResponseEntity<DestinationResponse> calcularDestino(@RequestBody DestinationRequest destinationRequest)
    {
        try {
            DestinationResponse destino = calculateDestinyService.CalculateDestiny(destinationRequest);
            return new ResponseEntity<>(destino, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
