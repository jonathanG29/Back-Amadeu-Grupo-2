package com.amadeus.amadeusbackend.services;

import com.amadeus.amadeusbackend.models.User;
import com.amadeus.amadeusbackend.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User saveUser(User usuario) {

        //Verificar si el usuario existe
        Optional<User> existingUsuario = userRepository.findAll().stream()
                .filter(u -> u.getEmail().equals(usuario.getEmail()))
                .findFirst();

        //Si existe devolver la información del usuario
        if (existingUsuario.isPresent()) {
            return existingUsuario.get();
        }

        //Si no existe, guardar el usuario
        return userRepository.save(usuario);
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

