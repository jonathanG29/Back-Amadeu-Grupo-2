package com.amadeus.amadeusbackend.contracts.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DestinationRequest {

    private String destino;
    private String climatica;
    private String actividad;
    private String alojamiento;
    private String viaje;
    private String edad;
    private long userId;
}
