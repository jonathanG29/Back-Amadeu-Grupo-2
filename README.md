# Back-Amadeu-Grupo-2

## 1. Arquitectura de capas
Este proyecto se realizó siguiendo una arquitectura de capas, a continuación se indican las capas utilizadas:

### 1.1 Capa Models
Para esta capa se definieron los siguienets modelos, los cuales se persistenten en base de datos:
#### 1.1.1 City: 
* Definición: tabla maestra que almacena las diferentes ciudades de Europa y América
* Atributos: id, nombreDestino, img, pais, idioma, lugarImperdible, comidaTipica y continente.
* Nota: La tabla "cities" homologa de este modelo en base de datos, tiene previamente insertada la información de las ciudades, esta tabla es de consulta y a tráves del aplicativo web, no se tienen definidos microservicios para su actualización.
#### 1.1.2 User: 
* Definición: modelo que persiste la información del usuario que ingresa a la página web
* Atributos: id, name y email.
#### 1.1.3 Answer: 
* Definición: modelo que persiste las respuestas de los usuarios.
* Atributos: id, destiny, weather, activity, housing, travelTime, age y user.
* Relacion: el modelo tiene una relación *@ManyToOne* con el modelo User.
#### 1.1.4 Result: 
* Definición: modelo que persiste los destino recomendados para cada usuario cada vez que utilicen la aplicación.
* Atributos: Id, cityAmerica, cityEurope, answer;
* Relación: el modelo tiene una relación *@OneToOne* con el modelo Answer.

### 1.2 Capa Repository
Interface que extiende de la clase JPARepository para la manipulación de los modelos en la base de datos. 

### 1.3 Capa Service
Capa que implementa la lógica del negocio.
#### 1.3.1 UserService: 
* Método SaveUser:
  * Definición: guarda nuevo usuario si el correo no existe en base de datos. Si ya existe no se crea un nuevo usuario en base de datos.
  * Parámetros: recibe un objeto User.
  * Retorna: objeto User guardado en BBDD.
#### 1.3.2 ResultService
* Método SaveResult:
  * Definición: guarda destinos recomendados a los usuarios, una ciudad de America y una de Europa de acuerdo a sus respuestas.
  * Parámetros: recibe un objeto Result.
  * Retorna: objeto Result guardado en BBDD.
#### 1.3.3 CalculateDestinyService
* Método CalculateDestiny:
  * Parámetros: recibe un objeto DestinationRequest.
  * Retorna: objeto DestinationResponse.
  * Función:
    * Verifica las respuestas del usuario a cada pregunta y calcula una ciudad de America y uno de Europa de acuerdo a las respuestas.
    * Setea los atributos destinoA y destinoE del objeto DestinationResponse.
    * Setea los atributos cityAmerica y cityEurope del objeto DestinationResponse.
    * Invoca al método findById de UserRepository para obtener un objeto User de acuerdo al atributo idUser del objeto recibido por parámetros DestinationRequest.
    * Invoca al método destinationRequestToAnswer del modelo Answer para setear los atributos del modelo Answer con los atributos del objeto DestinationRequest y el objeto User obtenido en el método anterior.
    * Invoca al método save del AnswerRepository para persistir el objeto Answer.
    * Invoca al método save del ResultRepository para persistir el objeto Result.
### 1.4 Capa Controllers
En esta capa se definen los endpoints para conexiones con las solicitudes del front.
* URL base "api/v1"
#### 1.4.1 UserController
* Método: saveUser
    * Operación: POST
    * Función: guardar usuario en base de datos.
    * URL: "/user"
    * Parámetros: @RequestBody: objeto User.
      ```json
      {
          "name": "nombreUsuario",
          "email": "nombreUsuario@correo.com"
      }
      ```
    * Return: objeto User.
      ```json
      {
          "id": 1,
          "name": "nombreUsuario",
          "email": "nombreUsuario@correo.com"
      }
      ```
#### 1.4.2 AnswerController
* Método: calcularDestino
    * Operación: POST
    * Función: llama el método CalculateDestiny del servicio calculateDestinyService enviandole como parámetro un objeto DestinationRequest.
    * URL: "/enviarDestino"
    * Parámetros: @RequestBody: objeto User.
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
    * Return: objeto DestinationResponse.
      ```json
      {
          "destinoA": "Ushuaia",
          "destinoE": "Reykjavik"
      }
      ```
#### 1.4.3 ResultController
* Método: searchName
    * Operación: GET
    * Función: Recupera de base de datos la información de la ciudad de America y Europa calculada para cada usuario en el enpoind anterior. 
    * URL: "/searchName/{america}/{europa}"
    * Parámetros: @PathVariable 
      ```
      http://localhost:8080/api/v1/searchName/Ushuaia/Reykjavik
      ```
    * Return: Arraylista de del objeto City
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
              "continente": "América"
          },
          {
              "id": 16,
              "nombreDestino": "Reykjavik",
              "img": "../../../assets/img/reykjavik.jpg",
              "pais": "Islandia",
              "idioma": "Islandés",
              "lugarImperdible": "Hallgrimskirkja",
              "comidaTipica": "Sopa de Cordero",
              "continente": "Europa"
          }
      ]
      ```
## 2. Detalle de contrato
Modelos que no se persisten en la base de datos, utilizados para conexión del backend con el frontend
### 2.1 Request 
#### 2.1.2 DestinationRequest
* Modelo utilizado como parámetro en método calcularDestino el endpoint tipo POST del AnswerController.
* Atributos: destino, climatica, actividad, alojamiento, viaje, edad y userId.
### 2.2 Response
#### 2.2.1 DestinationResponse
* Modelo utilizado como return en método calcularDestino el endpoint tipo POST del AnswerController.
* Atributos: destinoA y destinoB.
  
## 3. Modelo entidad-relación
![MER_Amadeus](https://github.com/user-attachments/assets/9879ce6a-7003-47f5-afde-c0fef59c161a)

## 4. Modelos de clases
![diagramaClases](https://github.com/user-attachments/assets/0b12714e-1802-4e27-bd1f-bd12cecde2cf)


## 5. Script tabla cities_bd
```
INSERT INTO cities 
VALUES
(1,'Salbutes', 'América','Español', '../../../assets/img/PlayaDelCarmen.jpg', 'Chichén-Itzá', 'Playa del Carmen', 'México'),
(2,'Hummus de Fava', 'Europa','Griego', '../../../assets/img/Santorini.jpg', 'Oia', 'Santorini', 'Grecia'),	  
(3,'Ceviche de Pescado', 'América','Español', '../../../assets/img/Tulum.jpg', 'Cenote Calavera', 'Tulum', 'México'),
(4,'Sofrit pagès', 'Europa','Castellano/Catalán', '../../../assets/img/ibiza.jpg', 'Islote Es Vedrá', 'Ibiza', 'España'),
(5,'Saimin', 'América','Ingles/Hawaiano', '../../../assets/img/Honolulu.jpg', 'Playa Hapuna', 'Honolulu', 'Hawái'),
(6,'Aljotta', 'Europa','Ingles/Maltés', '../../../assets/img/Malta.jpg', 'La Valeta', 'Malta', 'Malta'),
(7,'Cazuela de Mariscos', 'América','Español', '../../../assets/img/Cartagena.jpg', 'Castillo San Felipe', 'Cartagena', 'Colombia'),
(8,'Pa amb tomàquet', 'Europa','Castellano/Catalán', '../../../assets/img/Barcelona.jpg', 'Sagrada Familia', 'Barcelona', 'España'),
(9,'Mofongo', 'América','Español', '../../../assets/img/SanJuan.jpg', 'Viejo San Juan', 'San Juan', 'Puerto Rico'),
(10,'La socca', 'Europa','Frances', '../../../assets/img/niza.jpg', 'Vielle Ville', 'Niza', 'Francia'),
(11,'Feijoada', 'América','Portugués', '../../../assets/img/RioDeJaneiro.jpg', 'Cristo Redentor', 'Río de Janeiro', 'Brasil'),
(12,'Pasteles de Belem', 'Europa','Portugués', '../../../assets/img/lisboa.jpg', 'Tranvía 28', 'Lisboa', 'Portugal'),
(13,'Bandera Dominicana', 'América','Español', '../../../assets/img/puntaCana.jpg', 'Playa Bávaro', 'Punta Cana', 'Republica Dominicana'),
(14,'Cataplana', 'Europa','Portugués', '../../../assets/img/algarve.jpg', 'Tavira', 'Algarve', 'Portugal'),
(15,'Cazuela de Centolla', 'América','Español', '../../../assets/img/ushuaia.jpg', 'Montes Martial', 'Ushuaia', 'Argentina'),
(16,'Sopa de Cordero', 'Europa','Islandés', '../../../assets/img/reykjavik.jpg', 'Hallgrimskirkja', 'Reykjavik', 'Islandia'),
(17,'Parrilla', 'América','Ingles', '../../../assets/img/Aspen.jpg', 'Aspen Mountain', 'Aspen', 'EE.UU'),
(18,'Wiener Schnitzel', 'Europa','Alemán', '../../../assets/img/innsbruck.jpg', 'Hofkkirche', 'Innsbruck', 'Austria'),
(19,'Curanto', 'América','Español', '../../../assets/img/Bariloche.jpg', 'Nahuel Huapi', 'Bariloche', 'Argentina'),
(20,'Raclette', 'Europa','Alemán', '../../../assets/img/interlaken.jpg', 'Höhematte Park', 'Interlaken', 'Suiza'),
(21,'Poutine', 'América','Inglés', '../../../assets/img/banff.jpg', 'Upper Hot Springs', 'Banff', 'Canadá'),
(22,'Raclette', 'Europa','Alemán', '../../../assets/img/zermatt.jpg', 'Ferrocarril de Gornergrat', 'Zermatt', 'Suiza'),
(23,'Chiri Uchu', 'América','Español', '../../../assets/img/cusco.jpg', 'Saqsaywaman', 'Cusco', 'Perú'),
(24,'Pionono', 'Europa','Español', '../../../assets/img/Granada.jpg', 'Alhambra', 'Granada', 'España'),
(25,'Cuy al horno', 'América','Español', '../../../assets/img/MachuPicchu.jpg', 'Huayna Picchu', 'Machu Picchu', 'Perú'),
(26,'La tartiflette', 'Europa','Francés', '../../../assets/img/Chamonix.jpg', 'Mont Blanc', 'Chamonix', 'Francia'),
(27,'Hickory Burger', 'América','Inglés', '../../../assets/img/LosAngeles.jpg', 'Parque Griffith', 'Los Angeles', 'EE.UU'),
(28,'Gnocchi','Europa','Italiano','../../../assets/img/roma.jpg', 'Palacio Barberini', 'Roma', 'Italia'),
(29,'Poutine','América','Francés/Inglés', '../../../assets/img/toronto.jpg', 'Torre CN', 'Toronto', 'Canadá'),
(30,'Eisbein','Europa','Alemania', '../../../assets/img/berlin.jpg', 'Puesta de Brandeburgo', 'Berlín', 'Alemania'),
(31,'Chilaquiles','América','Español', '../../../assets/img/ciudadMexico.jpg', 'Coyoacán', 'Ciudad de México', 'México'),
(32,'Cocido Madrileño','Europa','Castellano', '../../../assets/img/madrid.jpg', 'Palacio Real', 'Madrid', 'España'),
(33,'Pizza', 'América','Inglés', '../../../assets/img/NuevaYork.jpg', 'Central Park', 'Nueva York', 'EE.UU'),
(34,'Foie gra', 'Europa','Frances', '../../../assets/img/paris.jpg', 'Torre Eiffel', 'París', 'Francia'),
(35,'Pargo Frito Y Griot','América','Inglés', '../../../assets/img/miami.jpg', 'Miami Beach', 'Miami', 'EE.UU'),
(36,'Wiener Schnitzel','Europa','Alemán', '../../../assets/img/viena.jpg', 'Palacio de Schönbrunn', 'Viena', 'Austria'),
(37,'Deep-dish Pizza','América','Inglés', '../../../assets/img/chicago.jpg', 'Cloud Gate', 'Chicago', 'EE.UU'),
(38,'Fish & Chips','Europa','Inglés', '../../../assets/img/londres.jpg', 'Abadía Westminster', 'Londres', 'Reino Unido'),
(39,'Roulottes','América','Francés', '../../../assets/img/BoraBora.jpg', 'Otemanu', 'Bora Bora', 'Polinesia Francesa'),
(40,'El Mezze','Asia','Árabe', '../../../assets/img/dubai.jpg', 'Burj Al Arab', 'Dubái', 'Emiratos Árabes')
```
