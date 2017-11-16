package com.itesm.roko.service;

import com.itesm.roko.dao.matchday_match.MatchDay_MatchDAOImpl;
import com.itesm.roko.domain.Jornada_match;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MatchDayMatchService {

    @Autowired
    private MatchDay_MatchDAOImpl matchDaymatchDAO;



    public Optional<Jornada_match> insert (Jornada_match jornada_match) {
        return matchDaymatchDAO.insertMatchDayMatch(jornada_match);
    }

}
