package com.amadeus.amadeusbackend.controllers;

import com.amadeus.amadeusbackend.repositories.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import com.amadeus.amadeusbackend.models.City;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1")
public class CityController {

    @Autowired
    CityRepository cityRepository;

    //metodo para buscar por nombre los destinos y devolver los atributos de cada uno
    @GetMapping("/searchName/{america}/{europa}")
    public ResponseEntity<List<City>> searchName(@PathVariable String america, @PathVariable String europa) {

        List<City> ciudades = new ArrayList<>();

        ciudades.add(cityRepository.findBynombreDestino(america));
        ciudades.add(cityRepository.findBynombreDestino(europa));

        return ResponseEntity.ok(ciudades);
    }
}
