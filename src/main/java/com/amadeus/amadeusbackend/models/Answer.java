package com.amadeus.amadeusbackend.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

@Entity
@Data
@Table(name = "answers")
//@NoArgsConstructor
@AllArgsConstructor

public class Answer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String destiny;
    private String weather;
    private String activity;
    private String housing;
    private String travelTime;
    private String age;

    //TODO: Metodo para obtener la fecha actual
    @Temporal(TemporalType.DATE)
    private Date registDate;

    //TODO: Falta relación con la tabla User (uno a muchos)
    private long userId;

    //Inicializar fecha en el contructor para que tome por defecto la fecha actual
    public Answer() {
        this.registDate = new Date();
    }

}
