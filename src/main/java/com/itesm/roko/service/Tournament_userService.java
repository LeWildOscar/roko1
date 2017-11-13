package com.itesm.roko.service;

import com.itesm.roko.dao.UserDAO;
import com.itesm.roko.dao.tournament_user.Tournament_userDAOImpl;
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

    public Optional<Tournament_user> update (Tournament_user tournament_user, String uuid) {
        return tournament_userDAOImpl.update(tournament_user);
    }

    public Optional<Boolean> delete (String uuid) {
        return tournament_userDAOImpl.delete(uuid);
    }

    public Optional<List<Tournament_user>> getUserTournaments() {
        return tournament_userDAOImpl.getUserTournaments ();
    }

    public Optional<Tournament_user> getTournamentUserById (int id) {
        return tournament_userDAOImpl.getTournamentUserById(id);
    }

    public Optional<Tournament_user> getTournamentUserByUuid (String uuid) {
        return tournament_userDAOImpl.getTournamentUserByUuid(uuid);
    }



}
