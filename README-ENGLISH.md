# Back-Amadeu-Grupo-2

## 1. Layered Architecture
This project was carried out following a layered architecture. The layers used are listed below:

### 1.1 Models Layer
The following models were defined for this layer, which are persisted in the database:
#### 1.1.1 City: 
* Definition: master table that stores the different cities of Europe and America.
* Attributes: id, nombreDestino, img, pais, idioma, lugarImperdible, comidaTipica and continente.
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
![MER_Amadeus](https://github.com/user-attachments/assets/9879ce6a-7003-47f5-afde-c0fef59c161a)

## 4. Class Model
![diagramaClases](https://github.com/user-attachments/assets/0b12714e-1802-4e27-bd1f-bd12cecde2cf)
