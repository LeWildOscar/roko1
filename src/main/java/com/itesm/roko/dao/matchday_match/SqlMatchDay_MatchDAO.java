package com.itesm.roko.dao.matchday_match;

import com.itesm.roko.domain.Matchday_match;

import java.util.Optional;

public interface SqlMatchDay_MatchDAO {




    Optional<Matchday_match> getByUuid(String uuid);
    Optional<Matchday_match> getById(int id);
    Optional<Matchday_match> insert(Matchday_match matchday_match);
    //Optional<Matchday_match> delete(Matchday_match matchday_match);




}
