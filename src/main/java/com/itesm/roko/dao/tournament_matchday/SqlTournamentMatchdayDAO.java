package com.itesm.roko.dao.tournament_matchday;

import com.itesm.roko.domain.Tournament_matchday;

import java.util.List;
import java.util.Optional;

public interface SqlTournamentMatchdayDAO {


    Optional<Tournament_matchday> getTournamentMatchdayByUuid(String uuid);
    Optional<Tournament_matchday> getTournamentMatchdayById(int id);
    Optional<List<Tournament_matchday>> getTournamentsMatchday();
    Optional<Tournament_matchday> insert (Tournament_matchday tournament_matchday);
    Optional<Tournament_matchday> update (Tournament_matchday tournament_matchday);
    Optional<Tournament_matchday> delete (Tournament_matchday tournament_matchday);
    Optional<Tournament_matchday> getByTournament_id (String tournament_id);



}
