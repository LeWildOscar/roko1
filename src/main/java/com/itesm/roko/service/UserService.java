package com.itesm.roko.service;

import com.itesm.roko.model.dao.UserDAO;
import com.itesm.roko.model.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserDAO userDAO;

    public Optional<List<User>> list(){
        return userDAO.list();
    }

    public Optional<User>insert(User user){
        return userDAO.insert(user);
    }

    public Optional<User> getUser(){
        //acá se mandaría a llamar el DAO
        User user = new User();
        user.setUsername("Nombre");
        user.setCountry("Apellido");
        return Optional.of(user);
    }
}
