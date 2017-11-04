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
}
