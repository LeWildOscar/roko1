package com.itesm.roko.service;

import com.itesm.roko.dao.TournamentDAO;
import com.itesm.roko.dao.matchday_match.MatchDay_MatchDAOImpl;
import com.itesm.roko.dao.tournament_matchday.TournamentMatchDayDAOImpl;
import com.itesm.roko.domain.Match;
import com.itesm.roko.domain.Matchday_match;
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
    @Autowired
    private MatchDay_MatchDAOImpl matchday_matchDAO;




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

    public Optional<List<Tournament_matchday>>tournamentMatchdays(String tournament_id){
        return  tournamentMatchDayDAO.tournamentMatchdays(tournament_id);
    }

    public Optional<List<Match>>matchdayMatches(String tournament_matchday_id){
        return tournamentMatchDayDAO.matchday_matches(tournament_matchday_id);
    }

    public Optional<Matchday_match>insertMatch(Matchday_match matchday_match){
        return matchday_matchDAO.insert(matchday_match);
    }











}
