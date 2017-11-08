package com.itesm.roko.service;

import com.itesm.roko.dao.TournamentDAO;
import com.itesm.roko.domain.Tournament;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TournamentService {

    @Autowired
    private TournamentDAO tournamentDAO;

    public Optional<Tournament>insert(Tournament tournament){

        return tournamentDAO.insert(tournament);
    }

    public Optional<List<Tournament>> list(){
        return tournamentDAO .list();
    }


}
