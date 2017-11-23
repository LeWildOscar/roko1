package com.itesm.roko.service;

import com.itesm.roko.dao.matchday_match.MatchDay_MatchDAOImpl;
import com.itesm.roko.domain.Match;
import com.itesm.roko.domain.Matchday_match;
import com.itesm.roko.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class Matchday_matchService {

    @Autowired
    private MatchDay_MatchDAOImpl matchDay_matchDAO;

    public Optional<Matchday_match>insert(Matchday_match matchday_match){
        return matchDay_matchDAO.insert(matchday_match);
    }
}
