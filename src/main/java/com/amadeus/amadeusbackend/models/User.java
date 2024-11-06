package com.amadeus.amadeusbackend.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Entity
@Table (name = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor

public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    //Buscar anotar especifico para que el campo no sea vacio
    @NonNull
    private String name;

    //Buscar anotar especifico para email
    @NonNull
    private String email;

}
