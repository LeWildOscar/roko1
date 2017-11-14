package com.itesm.roko.dao.tournament_user;

import com.itesm.roko.domain.Tournament;
import com.itesm.roko.domain.Tournament_user;
import com.itesm.roko.domain.User;

import java.util.List;
import java.util.Optional;

public interface SqlTournament_userDAO {


    public Optional<Tournament_user> getTournamentUserByUuid (String uuid);
    public Optional<Tournament_user> getTournamentUserById (int id);
    public Optional<List<Tournament_user>> getUserTournaments (int user_id);
    public Optional<List<Tournament>> getTournamentsUser (String username);
    public Optional<List<Tournament>> getTournamentsAdminUser (String username);
    public Optional<Tournament_user> insert (Tournament_user tournament_user);
    public Optional<Tournament_user> delete (Tournament_user tournament_user);
    public Optional<Tournament_user> update (Tournament_user tournament_user);



}
