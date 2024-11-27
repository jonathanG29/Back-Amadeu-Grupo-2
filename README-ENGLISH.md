# Back-Amadeu-Grupo-2

## 1. Layered Architecture
This project was carried out following a layered architecture. The layers used are listed below:

### 1.1 Models Layer
The following models were defined for this layer, which are persisted in the database:
#### 1.1.1 City: 
* Definition: master table that stores the different cities of Europe and America.
* Attributes: id, nombreDestino, img, pais, idioma, lugarImperdible, comidaTipica, continente, nombreHotel, descripcionHotel, imgHotel and urlHotel.
* Note: The “cities” table homologous to this model in the database has the city information pre-inserted. This table is for consultation, and through the web application, no microservices are defined for its update. 
#### 1.1.2 User: 
* Definition: model that persists the information of the user who enters the website.
* Attributes: id, name and email.
#### 1.1.3 Answer: 
* Definition: model that persists the users’ answers.
* Attributes: id, destiny, weather, activity, housing, travelTime, age and user.
* Relationship: the model has a *@ManyToOne* relationship with the User model.
#### 1.1.4 Result: 
* Definition: model that persists the recommended destinations for each user every time they use the application.
* Attributes: id, cityAmerica, cityEurope and answer;
* Relationship: the model has a *@OneToOne* relationship with the Answer model.

### 1.2 Repository Layer
Interface that extends from the JPARepository class for manipulating the models in the database. 

### 1.3 Service Layer
Layer that implements the business logic.
#### 1.3.1 UserService: 
* Method SaveUser:
  * Definition: saves a new user if the email does not exist in the database. If it already exists, a new user is not created in the database.
  * Parameters: receives a User object.
  * Returns: User object saved in the DB.
#### 1.3.2 ResultService
* Method SaveResult:
  * Definition: saves recommended destinations for users, one city in America and one in Europe according to their answers.
  * Parameters: receives a Result object.
  * Returns: Result object saved in the DB.
#### 1.3.3 CalculateDestinyService
* Method CalculateDestiny:
  * Parameters: receives a DestinationRequest object.
  * Retorna: DestinationResponse object.
  * Function:
    * Verifies the user’s answers to each question and calculates a city in America and one in Europe according to the answers.
    * Sets the destinoA and destinoE attributes of the DestinationResponse object.
    * Sets the cityAmerica and cityEurope attributes of the DestinationResponse object.
    * Invokes the findById method of UserRepository to obtain a User object according to the idUser attribute of the DestinationRequest object received as a parameter.
    * Invokes the destinationRequestToAnswer method of the Answer model to set the attributes of the Answer model with the attributes of the DestinationRequest object and the User object obtained in the previous method.
    * Invokes the save method of AnswerRepository to persist the Answer object.
    * Invokes the save method of ResultRepository to persist the Result object.
### 1.4 Controllers Layer
In this layer, the endpoints for connections with front-end requests are defined.
* Base URL "api/v1"
#### 1.4.1 UserController
* Method: saveUser
    * Operation: POST
    * Function: save user in the database.
    * URL: "/user"
    * Parameters: @RequestBody: User object.
      ```json
      {
          "name": "nombreUsuario",
          "email": "nombreUsuario@correo.com"
      }
      ```
    * Return: User object.
      ```json
      {
          "id": 1,
          "name": "nombreUsuario",
          "email": "nombreUsuario@correo.com"
      }
      ```
#### 1.4.2 AnswerController
* Method: calcularDestino
    * Operation: POST
    * Function: calls the CalculateDestiny method of the calculateDestinyService service, sending it a DestinationRequest object as a parameter.
    * URL: "/enviarDestino"
    * Parameters: @RequestBody: objeto User.
      ```json
      {
          
          "destino": "Montaña",
          "climatica": "Frío",
          "actividad": "Cultura y Museos",
          "alojamiento": "Airbnb",
          "viaje": "1-2 semanas",
          "edad": "Más de 50 años",
          "userId":1

      }
      ```
    * Return: DestinationResponse object.
      ```json
      {
          "destinoA": "Ushuaia",
          "destinoE": "Reykjavik"
      }
      ```
#### 1.4.3 ResultController
* Method: searchName
    * Operation: GET
    * Function: Retrieves from the database the information of the calculated city in America and Europe for each user in the previous endpoint. 
    * URL: "/searchName/{america}/{europa}"
    * Parameters: @PathVariable 
      ```
      http://localhost:8080/api/v1/searchName/Ushuaia/Reykjavik
      ```
    * Return: ArrayList of the City object
      ```
      [
          {
        "id": 15,
        "nombreDestino": "Ushuaia",
        "img": "../../../assets/img/ushuaia.jpg",
        "pais": "Argentina",
        "idioma": "Español",
        "lugarImperdible": "Montes Martial",
        "comidaTipica": "Cazuela de Centolla",
        "continente": "América",
        "nombreHotel": "Las Hayas Ushuaia Resort",
        "descripcionHotel":"Resort de 5 estrellas con habitaciones    elegantes, vistas al Canal Beagle, spa, piscina y restaurantes.",
        "imgHotel":"../../../assets/img/hoteles/Las_Hayas_Ushuaia_Resort-Ushuaia-Argentina.jpg",
        "urlHotel":"https://bit.ly/4eOcvs0"
    },
    {
        "id": 16,
        "nombreDestino": "Reykjavik",
        "img": "../../../assets/img/reykjavik.jpg",
        "pais": "Islandia",
        "idioma": "Islandés",
        "lugarImperdible": "Hallgrimskirkja",
        "comidaTipica": "Sopa de Cordero",
        "continente": "Europa",
        "nombreHotel": "Center Hotels Laugavegur",
        "descripcionHotel":"Hotel boutique en el corazón de Reykjavik, cerca de Laugavegur, con habitaciones modernas, restaurante, piscina y sauna.",
        "imgHotel":"../../../assets/img/hoteles/Center_Hotels_Laugavegur-Reykjavik-Islandia.jpg",
        "urlHotel":"https://www.centerhotels.com/en/hotel-laugavegur-reykjavik"
    }
      ]
      ```
## 2. Contract Details
Models that are not persisted in the database, used for backend connection with the frontend.
### 2.1 Request 
#### 2.1.2 DestinationRequest
* Model used as a parameter in the calculateDestiny method of the POST endpoint of the AnswerController.
* Attributes: destiny, weather, activity, housing, travelTime, age, and userId.
### 2.2 Response
#### 2.2.1 DestinationResponse
* Model used as a return in the calcularDestino method of the POST endpoint of the AnswerController.
* Attributes: destinoA y destinoB.
  
## 3. Entity-Relationship Model
![MER_Amadeus](https://github.com/user-attachments/assets/61fea0dc-7841-484d-9929-04bcd69a0f6e)

## 4. Class Model
![src](https://github.com/user-attachments/assets/8778f03e-ba6c-4705-8adb-1e49abdfd0af)

## 5. Script tabla cities_bd
```
INSERT INTO cities 
VALUES
(1, 'Salbutes', 'América', 'Resort de lujo para adultos en Playa del Carmen, México, con atención personalizada.', 'Español', '../../../assets/img/PlayaDelCarmen.jpg', '../../../assets/img/hoteles/TRSCoralHotel.jpg', 'Chichén-Itzá', 'Playa del Carmen', 'TRS Coral Hotel', 'México', 'http://bit.ly/415jHwE'),
(2, 'Hummus de Fava', 'Europa', 'Famoso por su servicio, ambiente cálido y sentido del romanticismo.', 'Griego', '../../../assets/img/Santorini.jpg', '../../../assets/img/hoteles/Katikies_Hotel_Santorin.jpg', 'Oia', 'Santorini', 'Katikies Hotel Santorini', 'Grecia', 'https://bit.ly/3Zodydr'),
(3, 'Ceviche de Pescado', 'América', 'Resort de lujo frente al mar con desayuno a la carta, piscina, jardines y terrazas.', 'Español', '../../../assets/img/Tulum.jpg', '../../../assets/img/hoteles/Kore_Tulum_Resort-Tulum-Mexico.jpg', 'Cenote Calavera', 'Tulum', 'Kore Tulum Retreat and Spa Resort', 'México', 'https://www.koretulum.com/es'),
(4, 'Sofrit pagès', 'Europa', 'Resort de lujo frente a la playa de SArgamassa con habitaciones elegantes, piscina privada y jardín propio.', 'Castellano/Catalán', '../../../assets/img/ibiza.jpg', '../../../assets/img/hoteles/ME_Ibiza_Ibiza-España.jpg', 'Islote Es Vedrá', 'Ibiza', 'ME Ibiza – The Leading Hotels of the World', 'España', 'https://bit.ly/3B8JfOE'),
(5, 'Saimin', 'América', 'Resort tropical en Waikiki Beach con habitaciones inspiradas en la isla, vistas al océano y jacuzzis.', 'Ingles/Hawaiano', '../../../assets/img/Honolulu.jpg', '../../../assets/img/hoteles/outrigger-waikiki-beach-Honolulu-Hawai.jpg', 'Playa Hapuna', 'Honolulu', 'Outrigger Waikiki Beach Resort', 'Hawái', 'https://www.outrigger.com/hawaii/oahu/outrigger-waikiki-beach-resort'),
(6, 'Aljotta', 'Europa', 'Resort de 3 estrellas en la bahía de Ramla con vistas al Mediterráneo, habitaciones cómodas y piscina.', 'Ingles/Maltés', '../../../assets/img/Malta.jpg', '../../../assets/img/hoteles/Ramla_Bay_Resort-Malta.jpg', 'La Valeta', 'Malta', 'Ramla Bay Resort', 'Malta', 'https://www.ramlabayresort.com/'),
(7, 'Cazuela de Mariscos', 'América', 'Hotel de 5 estrellas en la ciudad amurallada con habitaciones lujosas, spa y vistas al mar Caribe.', 'Español', '../../../assets/img/Cartagena.jpg', '../../../assets/img/hoteles/Casa_San_Agustín_Cartagena.jpg', 'Castillo San Felipe', 'Cartagena', 'Casa San Agustín', 'Colombia', 'https://www.hotelcasasanagustin.com/es/home.html'),
(8, 'Pa amb tomàquet', 'Europa', 'Hotel de 5 estrellas en la Barceloneta, conocido por su ambiente exclusivo y romántico.', 'Castellano/Catalán', '../../../assets/img/Barcelona.jpg', '../../../assets/img/hoteles/Hotel_Arts_Barcelona.jpg', 'Sagrada Familia', 'Barcelona', 'Hotel Arts Barcelona', 'España', 'https://bit.ly/497dhPm'),
(9, 'Mofongo', 'América', 'Resort de lujo en Condado con habitaciones elegantes, piscinas, spa y vistas al mar Caribe.', 'Español', '../../../assets/img/SanJuan.jpg', '../../../assets/img/hoteles/The-Ritz-Carlton_San-Juan-Puerto-Rico.jpg', 'Viejo San Juan', 'San Juan', 'The Ritz-Carlton, San Juan', 'Puerto Rico', 'https://bit.ly/3B3bKxk'),
(10, 'La socca', 'Europa', 'Hotel de lujo en la Promenade des Anglais, famoso por su arquitectura, historia y exclusivo ambiente.', 'Frances', '../../../assets/img/niza.jpg', '../../../assets/img/hoteles/hotel-le-negresco-Niza-Francia.jpg', 'Vielle Ville', 'Niza', 'Hotel Negresco', 'Francia', 'https://www.hotel-negresco-nice.com/en'),
(11, 'Feijoada', 'América', 'Icónico hotel de lujo en la playa de Copacabana, inaugurado en 1923, con habitaciones elegantes.', 'Portugués', '../../../assets/img/RioDeJaneiro.jpg', '../../../assets/img/hoteles/Belmond_Copacabana_Palace_Rio_janeiro.jpg', 'Cristo Redentor', 'Río de Janeiro', 'Belmond Copacabana Palace', 'Brasil', 'https://bit.ly/415Nk0J'),
(12, 'Pasteles de Belem', 'Europa', 'Hotel de lujo en la Avenida da Liberdade, cerca del centro de la ciudad, con habitaciones elegantes y spa.', 'Portugués', '../../../assets/img/lisboa.jpg', '../../../assets/img/hoteles/Grande_Real_Villa_Italia_Hotel & Spa-lisboa.jpg', 'Tranvía 28', 'Lisboa', 'Grande Real Villa Italia Hotel & Spa', 'Portugal', 'https://www.granderealvillaitalia.realhotelsgroup.com/'),
(13, 'Bandera Dominicana', 'América', 'Resort de lujo en Cap Cana con habitaciones elegantes, vistas al océano, piscinas, spa y restaurantes gourmet.', 'Español', '../../../assets/img/puntaCana.jpg', '../../../assets/img/hoteles/Secrets_Cap_Cana_PuntaCana-RepublicaDominicana.jpg', 'Playa Bávaro', 'Punta Cana', 'Secrets Cap Cana Resort & Spa', 'Republica Dominicana', 'https://www.capcanaresortspa.com/'),
(14, 'Cataplana', 'Europa', 'Resort de 4 estrellas diseñado para familias, con habitaciones cómodas, piscinas, parque infantil y actividades.', 'Portugués', '../../../assets/img/algarve.jpg', '../../../assets/img/hoteles/Martinhal_Quinta_Family_Resort-Algarve-Portugal.jpg', 'Tavira', 'Algarve', 'Martinhal Quinta Family Resort', 'Portugal', 'https://bit.ly/3Veh8V5'),
(15, 'Cazuela de Centolla', 'América', 'Resort de 5 estrellas con habitaciones elegantes, vistas al Canal Beagle, spa, piscina y restaurantes.', 'Español', '../../../assets/img/ushuaia.jpg', '../../../assets/img/hoteles/Las_Hayas_Ushuaia_Resort-Ushuaia-Argentina.jpg', 'Montes Martial', 'Ushuaia', 'Las Hayas Ushuaia Resort', 'Argentina', 'https://bit.ly/4eOcvs0'),
(16, 'Sopa de Cordero', 'Europa', 'Hotel boutique en el corazón de Reykjavik, cerca de Laugavegur, con habitaciones modernas, restaurante, piscina y sauna.', 'Islandés', '../../../assets/img/reykjavik.jpg', '../../../assets/img/hoteles/Center_Hotels_Laugavegur-Reykjavik-Islandia.jpg', 'Hallgrimskirkja', 'Reykjavik', 'Center Hotels Laugavegur', 'Islandia', 'https://www.centerhotels.com/en/hotel-laugavegur-reykjavik'),
(17, 'Parrilla', 'América', 'Hotel de lujo en el corazón de Aspen con habitaciones elegantes, vistas a las montañas, spa y restaurantes.', 'Ingles', '../../../assets/img/Aspen.jpg', '../../../assets/img/hoteles/The_Little_Nell-Aspen-Usa.jpg', 'Aspen Mountain', 'Aspen', 'The Little Nell', 'EE.UU', 'https://bit.ly/3Ow5KjP'),
(18, 'Wiener Schnitzel', 'Europa', 'Hotel de 4 estrellas en el centro de Innsbruck, cerca de la estación de tren y el centro histórico, con habitaciones cómodas y spa.', 'Alemán', '../../../assets/img/innsbruck.jpg', '../../../assets/img/hoteles/Hotel_Sailer-Innsbruck-Austria.jpg', 'Hofkkirche', 'Innsbruck', 'Hotel Sailer', 'Austria', 'https://www.sailer-innsbruck.at/en/index.html'),
(19, 'Curanto', 'América', 'Resort con apartamentos modernos y vistas al lago Nahuel Huapi, cada uno con cocina y detalles arquitectónicos en piedra y madera.', 'Español', '../../../assets/img/Bariloche.jpg', '../../../assets/img/hoteles/Catalonia_Sur_Aparts-Bariloche.jpg', 'Nahuel Huapi', 'Bariloche', 'Catalonia Sur Aparts', 'Argentina', 'https://www.cataloniasur.com.ar/'),
(20, 'Raclette', 'Europa', 'Hotel de 5 estrellas con habitaciones elegantes, spa, restaurantes y vistas al Jungfrau y los Alpes suizos.', 'Alemán', '../../../assets/img/interlaken.jpg', '../../../assets/img/hoteles/Victoria_Jungfrau_Grand_Hotel_Suiza.jpg', 'Höhematte Park', 'Interlaken', 'Victoria Jungfrau Grand Hotel & Spa', 'Suiza', 'https://www.victoria-jungfrau.ch/'),
(21, 'Poutine', 'América', 'Icónico hotel de lujo conocido como "The Castle in the Rockies", con habitaciones elegantes, restaurantes y spa.', 'Inglés', '../../../assets/img/banff.jpg', '../../../assets/img/hoteles/fairmont-banff-springs-Banff-Canada.jpg', 'Upper Hot Springs', 'Banff', 'Fairmont Banff Springs', 'Canadá', 'https://www.fairmont.com/banff-springs/'),
(22, 'Raclette', 'Europa', 'Hotel en el centro de Zermatt, cerca de la estación de tren Gornergrat, con vistas al Monte Cervino.', 'Alemán', '../../../assets/img/zermatt.jpg', '../../../assets/img/hoteles/Hotel_Alphubel-Zermatt-Suiza.jpg', 'Ferrocarril de Gornergrat', 'Zermatt', 'Hotel Alphubel', 'Suiza', 'https://alphubel-zermatt.ch/en/'),
(23, 'Chiri Uchu', 'América', 'Hotel de lujo con habitaciones elegantes, spa, restaurantes y vistas a la ciudad y alrededores.', 'Español', '../../../assets/img/cusco.jpg', '../../../assets/img/hoteles/Palacio_del_Inka_Cusco-Peru.jpg', 'Saqsaywaman', 'Cusco', 'Palacio del Inka, a Luxury Collection Hotel', 'Perú', 'https://bit.ly/3ZoC0vr'),
(24, 'Pionono', 'Europa', 'Hotel de lujo en el barrio de Ronda, cerca del centro histórico de Granada, con habitaciones elegantes, piscina cubierta y al aire libre.', 'Español', '../../../assets/img/Granada.jpg', '../../../assets/img/hoteles/Gran_Hotel_Luna_de_Granada_Granada-España.jpg', 'Alhambra', 'Granada', 'Gran Hotel Luna de Granada', 'España', 'https://www.delunahotels.com/en/granada/hotels/gran-hotel-luna-de-granada'),
(25, 'Cuy al horno', 'América', 'Resort de lujo en Aguas Calientes, cerca de las ruinas de Machu Picchu, con habitaciones elegantes, piscinas, spa y restaurante gourmet.', 'Español', '../../../assets/img/MachuPicchu.jpg', '../../../assets/img/hoteles/HotelInkaterra-Machu-Picchu-Peru.jpg', 'Huayna Picchu', 'Machu Picchu', 'Inkaterra Machu Picchu Pueblo Hotel', 'Perú', 'https://www.inkaterra.com/'),
(26, 'La tartiflette', 'Europa', 'Hotel de lujo en Chamonix, cerca del centro comercial, con habitaciones elegantes y vistas al Mont Blanc.', 'Francés', '../../../assets/img/Chamonix.jpg', '../../../assets/img/hoteles/Hotel_Le_Morgane-Chamonix-Francia.jpg', 'Mont Blanc', 'Chamonix', 'Hotel Le Morgane', 'Francia', 'https://morgane-hotel-chamonix.com/en/'),
(27, 'Hickory Burger', 'América', 'Hotel boutique en Downtown Los Ángeles con habitaciones modernas, spa, restaurante gourmet y vistas de la ciudad.', 'Inglés', '../../../assets/img/LosAngeles.jpg', '../../../assets/img/hoteles/Hotel_Figueroa-Los-Angeles-Usa.jpg', 'Parque Griffith', 'Los Angeles', 'Hotel Figueroa - Unbound Collection by Hyatt', 'EE.UU', 'https://www.hotelfigueroa.com/'),
(28, 'Gnocchi', 'Europa', 'Hotel de lujo entre la Plaza de España y la Piazza del Popolo, conocido por su arquitectura, jardines y servicio. Habitaciones lujosas con camas king y baños de mármol.', 'Italiano', '../../../assets/img/roma.jpg', '../../../assets/img/hoteles/Hotel-de-Russie-Roma-Italia.jpg', 'Palacio Barberini', 'Roma', 'Hotel de Russie, A Rocco Forte Hotel', 'Italia', 'https://www.roccofortehotels.com/hotels-and-resorts/hotel-de-russie/'),
(29, 'Poutine', 'América', 'Icónico hotel de lujo en el corazón de Toronto, con habitaciones elegantes, spa, restaurantes y vistas del centro.', 'Francés/Inglés', '../../../assets/img/toronto.jpg', '../../../assets/img/hoteles/Fairmont_Royal_York-Toronto-Canada.jpg', 'Torre CN', 'Toronto', 'Fairmont Royal York', 'Canadá', 'https://www.fairmont.com/royal-york-toronto/'),
(30, 'Eisbein', 'Europa', 'Hotel de lujo cerca de la Puerta de Brandenburgo con habitaciones elegantes, spa, restaurantes y vistas de la ciudad.', 'Alemania', '../../../assets/img/berlin.jpg', '../../../assets/img/hoteles/Hotel_Adlon_Kempinski-Berlin-Alemania.jpg', 'Puesta de Brandeburgo', 'Berlín', 'Hotel Adlon Kempinski', 'Alemania', 'https://www.kempinski.com/en/hotel-adlon'),
(31, 'Chilaquiles', 'América', 'Hotel de lujo en Polanco, Ciudad de México, con habitaciones elegantes, spa, restaurantes y vistas de la ciudad.', 'Español', '../../../assets/img/ciudadMexico.jpg', '../../../assets/img/hoteles/Four_Seasons_Hotel-Ciudad-De-Mexico-Mexico.jpg', 'Coyoacán', 'Ciudad de México', 'Four Seasons Hotel Mexico City', 'México', 'https://www.fourseasons.com/es/mexico/'),
(32, 'Cocido Madrileño', 'Europa', 'Hotel de lujo en Paseo de la Castellana, con habitaciones lujosas, spa sublime y múltiples opciones de restaurantes.', 'Castellano', '../../../assets/img/madrid.jpg', '../../../assets/img/hoteles/Rosewood_Villa_Magna-Madrid-Espana.jpg', 'Palacio Real', 'Madrid', 'Rosewood Villa Magna', 'España', 'https://www.rosewoodhotels.com/es/villa-magna'),
(33, 'Pizza', 'América', 'Icónico hotel de lujo en la Quinta Avenida y Calle 59, famoso por su arquitectura, historia y ambiente exclusivo.', 'Inglés', '../../../assets/img/NuevaYork.jpg', '../../../assets/img/hoteles/The-Plaza-Hotel-Manhattan-New-York.jpg', 'Central Park', 'Nueva York', 'The Plaza Hotel', 'EE.UU', 'https://www.theplazany.com/'),
(34, 'Foie gra', 'Europa', 'Hotel de lujo en Campos Elísios, cerca del Arco de Triunfo y la Ópera, ofreciendo una estancia cómoda y sofisticada en París.', 'Frances', '../../../assets/img/paris.jpg', '../../../assets/img/hoteles/Le_Bristol_Paris-Francia.jpg', 'Torre Eiffel', 'París', 'Le Bristol Paris', 'Francia', 'https://bit.ly/4g7IF2B'),
(35, 'Pargo Frito Y Griot', 'América', 'Hotel de lujo en el distrito financiero de Miami, con vistas del Río Miami y la Bahía Biscayne.', 'Inglés', '../../../assets/img/miami.jpg', '../../../assets/img/hoteles/Kimpton_EPIC_Hotel-Miami.jpg', 'Miami Beach', 'Miami', 'Kimpton EPIC Hotel', 'EE.UU', 'https://www.epichotel.com/'),
(36, 'Wiener Schnitzel', 'Europa', 'Hotel boutique cerca del Barrio de los Museos, con habitaciones personalizadas, gimnasio, piscina y spa.', 'Alemán', '../../../assets/img/viena.jpg', '../../../assets/img/hoteles/Hotel_Sans_Souci_Wien-Viena-Suiza.jpg', 'Palacio de Schönbrunn', 'Viena', 'Hotel Sans Souci Wien', 'Austria', 'https://www.sanssouci-wien.com/'),
(37, 'Deep-dish Pizza', 'América', 'Hotel de lujo a orillas del río Chicago, cerca de la avenida Michigan, con habitaciones elegantes y vistas de la ciudad.', 'Inglés', '../../../assets/img/chicago.jpg', '../../../assets/img/hoteles/The_Langham_Chicago-Usa.jpg', 'Cloud Gate', 'Chicago', 'The Langham, Chicago', 'EE.UU', 'https://www.langhamhotels.com/en/the-langham/chicago/'),
(38, 'Fish & Chips', 'Europa', 'Icónico hotel de lujo en Mayfair, con habitaciones elegantes, spa, restaurantes, bares y vistas de la ciudad.', 'Inglés', '../../../assets/img/londres.jpg', '../../../assets/img/hoteles/The_Ritz_London_Hotel-London-Uk.jpg', 'Abadía Westminster', 'Londres', 'The Ritz London, A JW Marriott Hotel', 'Reino Unido', 'https://www.theritzlondon.com/'),
(39, 'Roulottes', 'América', 'Hermoso hotel a cinco minutos del Aeropuerto Internacional Faaa, con amplia piscina y vistas a la isla Moorea.', 'Francés', '../../../assets/img/BoraBora.jpg', '../../../assets/img/hoteles/Hilton_Hotel_Tahiti.jpg', 'Otemanu', 'Bora Bora', 'Hilton Hotel Tahiti', 'Polinesia Francesa', 'https://bit.ly/3CGiwtd'),
(40, 'El Mezze', 'Asia', 'Hotel de lujo conocido por su forma de vela y ubicación en una isla artificial frente a la playa de Jumeirah.', 'Árabe', '../../../assets/img/dubai.jpg', '../../../assets/img/hoteles/Jumeirah_Burj_Al_Arab.jpg', 'Burj Al Arab', 'Dubái', 'Jumeirah Burj Al Arab', 'Emiratos Árabes', 'https://www.jumeirah.com/en/stay/dubai/burj-al-arab-jumeirah')
```

