package com.amadeus.amadeusbackend.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "cities")
@NoArgsConstructor
@AllArgsConstructor

public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String nombreDestino;
    private String img;
    private String pais;
    private String idioma;
    private String lugarImperdible;
    private String comidaTipica;
    private String continente;
    private String nombreHotel;
    private String descripcionHotel;
    private String imgHotel;
}
