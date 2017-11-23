package com.itesm.roko.service;

import com.itesm.roko.dao.TournamentDAO;
import com.itesm.roko.dao.tournament_matchday.TournamentMatchDayDAOImpl;
import com.itesm.roko.domain.Tournament;
import com.itesm.roko.domain.Tournament_matchday;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
public class TournamentMatchDayService {

    @Autowired
    private TournamentMatchDayDAOImpl tournamentMatchDayDAO;
    @Autowired
    private TournamentDAO tournamentDAO;




    public Optional<Tournament_matchday> insert (Tournament_matchday tournament_matchday) {
        return tournamentMatchDayDAO.insert(tournament_matchday);

    }

    public Optional<Tournament_matchday> getTournamentMatchDayByUuid (String uuid) {
        return  tournamentMatchDayDAO.getTournamentMatchdayByUuid(uuid);
    }

    public Optional<List<Tournament_matchday>> getTournaments() {
        return tournamentMatchDayDAO.getTournamentsMatchday();

    }

    public Optional<Tournament_matchday>getByTournament_id(String tournament_id){
        return  tournamentMatchDayDAO.getByTournament_id(tournament_id);
    }











}
