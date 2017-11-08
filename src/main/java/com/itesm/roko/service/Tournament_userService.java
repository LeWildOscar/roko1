package com.itesm.roko.service;

import com.itesm.roko.dao.Tournament_userDAO;
import com.itesm.roko.domain.Tournament;
import com.itesm.roko.domain.Tournament_user;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class Tournament_userService {

    @Autowired
    private Tournament_userDAO tournament_userDAO;


}
