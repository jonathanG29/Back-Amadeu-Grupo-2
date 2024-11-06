package com.amadeus.amadeusbackend.services;

import com.amadeus.amadeusbackend.models.User;
import com.amadeus.amadeusbackend.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User saveUser(User user) {
        return userRepository.save(user);

        //Validacion de usuario existente por correo getByemail
        //Validación de exepciones
    }

    public List<User> userList (){
        return userRepository.findAll();
    }

    public User updateUser(User user) {
        return userRepository.save(user);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }


}

