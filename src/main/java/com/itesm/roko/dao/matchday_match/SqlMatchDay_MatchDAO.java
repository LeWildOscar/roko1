package com.itesm.roko.dao.matchday_match;

import com.itesm.roko.domain.Jornada_match;

import java.util.Optional;

public interface SqlMatchDay_MatchDAO {




    Optional<Jornada_match> getMatchDayMatchByUuid(String uuid);
    Optional<Jornada_match> getMatchDayMatchById(int id);
    Optional<Jornada_match> insertMatchDayMatch(Jornada_match jornada_match);
    Optional<Jornada_match> deleteMatchDayMatch(Jornada_match jornada_match);


}
