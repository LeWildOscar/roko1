package com.itesm.roko.service;



import com.itesm.roko.dao.tournament_matchday.TournamentMatchDayDAOImpl;
import com.itesm.roko.domain.Tournament_matchday;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TournamentMatchDayService {

    @Autowired
    private TournamentMatchDayDAOImpl tour;




    public Optional<Tournament_matchday> insert (Tournament_matchday tournament_matchday, String uuid) {
        return null;
    }











}
