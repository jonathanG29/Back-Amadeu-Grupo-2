package com.amadeus.amadeusbackend.models;

import com.amadeus.amadeusbackend.contracts.request.DestinationRequest;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import java.util.Date;

@Entity
@Data
@Table(name = "answers")
@AllArgsConstructor

public class Answer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank
    @NotNull
    private String destiny;
    @NotBlank
    @NotNull
    private String weather;
    @NotBlank
    @NotNull
    private String activity;
    @NotBlank
    @NotNull
    private String housing;
    @NotBlank
    @NotNull
    private String travelTime;
    @NotBlank
    @NotNull
    private String age;

    @ManyToOne
    private User user;


    @Temporal(TemporalType.DATE)
    private Date registDate;


    //Inicializar fecha en el contructor para que tome por defecto la fecha actual
    public Answer() {
        this.registDate = new Date();
    }

    //Metodo para convertir un objeto de tipo DestinationRequest a tipo Answer
    //Metodo recibe un objeto de tipo DestinationRequest y un objeto de tipo User
    public Answer destinationRequestToAnswer(DestinationRequest destinationRequest, User usuario)
    {
        this.destiny = destinationRequest.getDestino();
        this.weather = destinationRequest.getClimatica();
        this.activity = destinationRequest.getActividad();
        this.housing = destinationRequest.getAlojamiento();
        this.travelTime = destinationRequest.getViaje();
        this.age = destinationRequest.getEdad();
        this.user = usuario;

        return this;
    }

}
