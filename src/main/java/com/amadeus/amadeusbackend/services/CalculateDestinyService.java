package com.amadeus.amadeusbackend.services;

import com.amadeus.amadeusbackend.contracts.request.DestinationRequest;
import com.amadeus.amadeusbackend.contracts.response.DestinationResponse;
import com.amadeus.amadeusbackend.models.Answer;
import com.amadeus.amadeusbackend.models.User;
import com.amadeus.amadeusbackend.repositories.UserRepository;
import com.amadeus.amadeusbackend.models.Result;
import com.amadeus.amadeusbackend.repositories.ResultRepository;
import com.amadeus.amadeusbackend.repositories.AnswerRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@Data
public class CalculateDestinyService {

    String pDestino = "";
    String pClimatica = "";
    String dViaje = "";
    String edad = "";
    String pActividad = "";
    String pAlojamiento = "";
    String destinoA = "";
    String destinoE = "";

    @Autowired
    ResultRepository resultRepository;
    @Autowired
    AnswerRepository answerRepository;

    @Autowired
    UserRepository userRepository;

    public DestinationResponse CalculateDestiny(DestinationRequest destinationRequest) {

        //Recuperar el valor de cada variable del objeto recibido calculoDestino
        pDestino = destinationRequest.getDestino();
        pClimatica = destinationRequest.getClimatica();
        dViaje = destinationRequest.getViaje();
        edad = destinationRequest.getEdad();
        pActividad = destinationRequest.getActividad();
        pAlojamiento = destinationRequest.getAlojamiento();

        //Mapa de respuestas y destinos
        Map<String, String[]> destinos = new HashMap<>();
        destinos.put("Playa-Caluroso-1-2 semanas-Menos de 30 años-Deportes y Aventuras-Hostal o Albergue", new String[]{"Tulum", "Ibiza"});
        destinos.put("Playa-Caluroso-1-2 semanas-Menos de 30 años-Relax y Bienestar-Hotel de Lujo", new String[]{"Playa del Carmen", "Santori"});
        destinos.put("Playa-Caluroso-1-2 semanas-30-50 años-Cultura y Museos-Hotel de Lujo", new String[]{"Honolulu", "Malta"});
        destinos.put("Playa-Caluroso-Menos de una semana-Menos de 30 años-Cultura y Museos-Airbnb", new String[]{"Cartagena", "Barcelona"});
        destinos.put("Playa-Templado-1-2 semanas-Menos de 30 años-Cultura y Museos-Hostal o Albergue", new String[]{"San Juan", "Niza"});
        destinos.put("Playa-Templado-1-2 semanas-30-50 años-Cultura y Museos-Hotel de Lujo", new String[]{"Río de Janeiro", "Lisboa"});
        destinos.put("Playa-Templado-Más de dos semanas-Más de 50 años-Relax y Bienestar-Airbnb", new String[]{"Punta Cana", "Algarve"});
        destinos.put("Montaña-Frío-1-2 semanas-Más de 50 años-Cultura y Museos-Airbnb", new String[]{"Ushuaia", "Reykjavik"});
        destinos.put("Montaña-Frío-1-2 semanas-Más de 50 años-Relax y Bienestar-Airbnb", new String[]{"Aspen", "Innsbruck"});
        destinos.put("Montaña-Frío-1-2 semanas-Menos de 30 años-Deportes y Aventuras-Hostal o Albergue", new String[]{"Bariloche", "Interlaken"});
        destinos.put("Montaña-Frío-1-2 semanas-30-50 años-Deportes y Aventuras-Hotel de Lujo", new String[]{"Banff", "Zermatt"});
        destinos.put("Montaña-Templado-1-2 semanas-Más de 50 años-Cultura y Museos-Airbnb", new String[]{"Cusco", "Granada"});
        destinos.put("Montaña-Templado-Más de dos semanas-Menos de 30 años-Deportes y Aventuras-Airbnb", new String[]{"Machu Picchu", "Chamonix"});
        destinos.put("Ciudad-Caluroso-1-2 semanas-Más de 50 años-Cultura y Museos-Hotel de Lujo", new String[]{"Los Angeles", "Roma"});
        destinos.put("Ciudad-Frío-1-2 semanas-30-50 años-Cultura y Museos-Hotel de Lujo", new String[]{"Toronto", "Berlín"});
        destinos.put("Ciudad-Templado-1-2 semanas-30-50 años-Cultura y Museos-Hostal o Albergue", new String[]{"Ciudad de México", "Madrid"});
        destinos.put("Ciudad-Templado-1-2 semanas-Más de 50 años-Cultura y Museos-Hotel de Lujo", new String[]{"Nueva York", "París"});
        destinos.put("Ciudad-Templado-Menos de una semana-Menos de 30 años-Relax y Bienestar-Airbnb", new String[]{"Miami", "Viena"});
        destinos.put("Ciudad-Templado-Menos de una semana-30-50 años-Deportes y Aventuras-Hotel de Lujo", new String[]{"Chicago", "Londres"});

        String key = String.join("-", pDestino, pClimatica, dViaje, edad, pActividad, pAlojamiento);
        String[] destino = destinos.getOrDefault(key, new String[]{"Bora Bora", "Dubái"});

        //Guardar los destinos calculados de acuerdo a las respuestas
        destinoA = destino[0];
        destinoE = destino[1];

        System.out.println("Destino A: " + destinoA);
        System.out.println("Destino E: " + destinoE);

        //Instancias de los modelos Result, Answer y Destinos
        DestinationResponse destinationResponse = new DestinationResponse();
        Result result = new Result();
        Answer answer = new Answer();

        //Setear los destinos resultantes en el objeto (contracts-response)Destinos
        destinationResponse.setDestinoA(destinoA);
        destinationResponse.setDestinoE(destinoE);

        //Setear los destinos resultantes en el objeto (models)Result
        result.setCityAmerica(destinoA);
        result.setCityEurope(destinoE);

        //Buscar el usuario por el id del objeto calculoDestino
        User user = userRepository.findById(destinationRequest.getUserId()).get();

        //invocar el metodo calcularDestinoToAnswer de la clase Answer
        //Se le envían los parámetros calculoDestino y user
        answer = answer.destinationRequestToAnswer(destinationRequest, user);

        //Guardar en la base de datos el objeto Answer
        answerRepository.save(answer);

        //Setear el objeto Answer en el objeto Result-Relación OneToOne
        result.setAnswer(answer);

        //Guardar en la base de datos el objeto Result
        resultRepository.save(result);

        //Devuelve el objeto Destinos para enviar al front
        return destinationResponse;
    }

}


