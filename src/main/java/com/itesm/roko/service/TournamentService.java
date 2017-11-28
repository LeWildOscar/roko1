package com.itesm.roko.service;

import com.itesm.roko.dao.TournamentDAO;
import com.itesm.roko.dao.UserDAO;
import com.itesm.roko.dao.tournament_matchday.TournamentMatchDayDAOImpl;
import com.itesm.roko.dao.tournament_user.Tournament_userDAOImpl;
import com.itesm.roko.domain.Tournament;
import com.itesm.roko.domain.Tournament_matchday;
import com.itesm.roko.domain.Tournament_user;
import com.itesm.roko.domain.User;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TournamentService {

    @Autowired
    private TournamentDAO tournamentDAO;
    @Autowired
    private Tournament_userDAOImpl tournament_userDAOImpl;
    @Autowired
    private TournamentMatchDayDAOImpl tournamentMatchDayDAO;
    @Autowired
    private UserDAO userDAO;


    public Optional<Tournament>insert(Tournament tournament){
        //sólo recibe nombre, precio entrada, descripcion
        tournament.setIs_public(0);
        tournament.setLevel(1);
        tournament.setMax_users(10);
        tournament.setMin_users(1);
        tournament.setPot(0);
        tournament.setPrize_spread(1);

        Optional<Tournament>tournament1 = tournamentDAO.insert(tournament);
        System.out.println("torneos jot");
        Tournament_user tournament_user = new Tournament_user();
        User user = userDAO.getByUsername(tournament.getUsername()).get();
        //nombre, precui, desc
        tournament_user.setUser_id(user.getId());
        tournament_user.setIs_winner(0);
        tournament_user.setIs_admin(1);
        tournament_user.setPosition(1);
        tournament_user.setPrize_amount(0);
        tournament_user.setTournament_id(tournament1.get().getId());
        tournament_user.setOn_updated(null);
        tournament_user.setOn_created(null);
        System.out.println("antes de insertar");
        Tournament_matchday tournament_matchday = new Tournament_matchday();
        tournament_matchday.setStart_date(tournament.getDate_start());
        tournament_matchday.setEnd_date(tournament.getDate_end());
        tournament_matchday.setNumber(1);
        tournament_matchday.setTournament_id(tournament1.get().getId());
        Optional<Tournament_user> aux = tournament_userDAOImpl.insert(tournament_user);
        Optional<Tournament_matchday> tournament_matchday1 = tournamentMatchDayDAO.insert(tournament_matchday);
        System.out.println("TOURNAMENT_MATCHDAY ID:"+tournament_matchday1.get().getId());
        return tournament1;
    }

    public Optional<List<Tournament>> list(){
        return tournamentDAO.list();
    }

    public Optional<Tournament>getByPublicIdentifier(String public_identifier){
        Optional<Tournament> tournament = tournamentDAO.getByPublicIdentifier(public_identifier);
        Optional<Tournament_matchday> tournament_matchday= tournamentMatchDayDAO.getByTournament_id(""+tournament.get().getId());
        tournament.get().setTournament_matchday_id(""+tournament_matchday.get().getId());
        return tournament;
    }

    public Optional<List<User>> getTournamentUsers(String tournament){
        return tournamentDAO.getTournamentUsers(tournament);
    }


}
