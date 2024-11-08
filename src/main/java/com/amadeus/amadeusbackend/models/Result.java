package com.amadeus.amadeusbackend.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@Table(name = "results")
@NoArgsConstructor
@AllArgsConstructor
public class Result {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String cityAmerica;
    private String cityEurope;

    @OneToOne
    private Answer answer;

    //TODO: Falta relación con la tabla User (uno a muchos)
//    @OneToMany
//    @JoinColumn(name = "id")
//    private List <User> users;

}
