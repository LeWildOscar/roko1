package com.itesm.roko.service;

import com.itesm.roko.dao.UserDAO;
import com.itesm.roko.dao.tournament_user.Tournament_userDAOImpl;
import com.itesm.roko.domain.Tournament;
import com.itesm.roko.domain.Tournament_user;
import com.itesm.roko.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class Tournament_userService {

    @Autowired
    private Tournament_userDAOImpl tournament_userDAOImpl;
    @Autowired
    private UserDAO usuarioDAO;

    public Optional<Tournament_user> insert (Tournament_user tournament_user,String username) {
        User user = usuarioDAO.getByUsername(username).get();
        tournament_user.setUser_id(user.getId());
        return tournament_userDAOImpl.insert(tournament_user);
    }

    public Optional<Tournament_user> update (String username, Tournament_user tournament_user, String uuid) {
        User user = usuarioDAO.getByUsername(username).get();
        Tournament_user aux_tournament_user = tournament_userDAOImpl.getTournamentUserByUuid(uuid).get();
        tournament_user.setUuid(aux_tournament_user.getUuid());
        tournament_user.setUser_id(user.getId());
        return tournament_userDAOImpl.update(tournament_user);
    }

    public Optional<Tournament_user> delete (String uuid) {
        Tournament_user tournament_user = tournament_userDAOImpl.getTournamentUserByUuid(uuid).get();
        return tournament_userDAOImpl.delete(tournament_user);
    }

    public Optional<List<Tournament_user>> getUserTournaments(String username) {
        User user = usuarioDAO.getByUsername(username).get();
        return tournament_userDAOImpl.getUserTournaments (user.getId());
    }

    public Optional<List<Tournament>> getTournamentsUser (String username) {
        return tournament_userDAOImpl.getTournamentsUser(username);
    }

    public Optional<List<Tournament>> getTournamentsAdminUser (String username) {
        return tournament_userDAOImpl.getTournamentsAdminUser(username);
    }


    public Optional<Tournament_user> getTournamentUserByUuid (String uuid) {
        return tournament_userDAOImpl.getTournamentUserByUuid(uuid);
    }



}
