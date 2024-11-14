package com.amadeus.amadeusbackend.contracts.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class DestinationResponse {
    private String DestinoA;
    private String DestinoE;
}
