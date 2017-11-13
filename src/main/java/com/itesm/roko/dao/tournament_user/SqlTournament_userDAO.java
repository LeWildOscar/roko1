package com.itesm.roko.dao.tournament_user;

import com.itesm.roko.domain.Tournament_user;

import java.util.List;
import java.util.Optional;

public interface SqlTournament_userDAO {


    public Optional<Tournament_user> getTournamentUserByUuid (String uuid);
    public Optional<Tournament_user> getTournamentUserById (int id);
    public Optional<List<Tournament_user>> getUserTournaments ();
    public Optional<Tournament_user> insert (Tournament_user tournament_user);
    public Optional<Boolean> delete (String uuid);
    public Optional<Tournament_user> update (Tournament_user tournament_user);



}
