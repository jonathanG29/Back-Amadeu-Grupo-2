package com.amadeus.amadeusbackend.services;


import com.amadeus.amadeusbackend.models.Answer;
import com.amadeus.amadeusbackend.models.Result;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class CalculateDestinyService {

    Long idUsuario;

    String pDestino = "";
    String pClimatica = "";
    String dViaje = "";
    String edad = "";
    String pActividad = "";
    String pAlojamiento = "";
    String destinoA = "";
    String destinoE = "";

    public void CalculateDestiny(Answer answer) {

        idUsuario = answer.getUserId();

        pDestino = answer.getDestiny();
        pClimatica = answer.getWeather();
        dViaje = answer.getTravelTime();
        edad = answer.getAge();
        pActividad = answer.getActivity();
        pAlojamiento = answer.getHousing();

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

        destinoA = destino[0];
        destinoE = destino[1];

        System.out.println("Destino A: " + destinoA);
        System.out.println("Destino E: " + destinoE);

        Result result = new Result();

        result.setCityAmerica(destinoA);
        result.setCityEurope(destinoE);
        result.setIdUser(idUsuario);
        result.setAnswer(answer);


    }



}
