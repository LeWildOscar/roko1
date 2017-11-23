package com.itesm.roko.service;

import com.itesm.roko.dao.MatchDAO;
import com.itesm.roko.domain.Match;
import com.itesm.roko.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MatchService {

    @Autowired
    private MatchDAO matchDAO;

    public Optional<List<Match>> list(){

        return matchDAO.list();
    }

}
