package com.itesm.roko.service;

import com.itesm.roko.dao.UserDAO;
import com.itesm.roko.domain.User;
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

    public Optional<User> update(User user){
        Optional<User> userDB = userDAO.getByUuid(user.getUuid());
        if(userDB.isPresent()) {
            return userDAO.update(user);
        } else {
            return Optional.empty();
        }
    }
    public Optional<User> delete(String uuid){
        Optional<User> user = userDAO.getByUuid(uuid);
        if(user.isPresent()) {
            return userDAO.delete(user.get());
        } else {
            return Optional.empty();
        }
    }

    public Optional<User> getUser(String username){
        return userDAO.getByUsername(username);
    }
}
