package com.itesm.roko.dao.tournament_matchday;

import com.itesm.roko.dao.UserDAO;
import com.itesm.roko.domain.Match;
import com.itesm.roko.domain.Matchday_match;
import com.itesm.roko.domain.Tournament_matchday;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class TournamentMatchDayDAOImpl implements SqlTournamentMatchdayDAO {

    @Autowired
    protected JdbcTemplate jdbcTemplate;

    private static final Logger logger = LoggerFactory.getLogger(TournamentMatchDayDAOImpl.class);


    @Override
    public Optional<Tournament_matchday> getTournamentMatchdayByUuid (String uuid) {
        String sql = "Select * from tournament_matchday where uuid = ?";
        try {
            Tournament_matchday tournament_matchday = jdbcTemplate.queryForObject(sql,new BeanPropertyRowMapper<>(Tournament_matchday.class),uuid);
            return Optional.of(tournament_matchday);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return Optional.empty();
    }

    @Override
    public Optional<Tournament_matchday> getByTournament_id (String tournament_id) {
        String sql = "Select * from tournament_matchday where tournament_id = ?";
        try {
            Tournament_matchday tournament_matchday = jdbcTemplate.queryForObject(sql,new BeanPropertyRowMapper<>(Tournament_matchday.class),tournament_id);
            return Optional.of(tournament_matchday);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return Optional.empty();
    }

    @Override
    public Optional<Tournament_matchday> getTournamentMatchdayById (int id) {
        String sql = "Select * from tournament_matchday where id = ?";
        try {
            Tournament_matchday tournament_matchday = jdbcTemplate.queryForObject(sql,new BeanPropertyRowMapper<>(Tournament_matchday.class),id);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public Optional<List<Tournament_matchday>> getTournamentsMatchday() {
        String sql = "select * from tournament_matchday";
        try {
            List<Tournament_matchday> list_tournament_matchday = jdbcTemplate.query(sql,new BeanPropertyRowMapper<>(Tournament_matchday.class));
            return Optional.of(list_tournament_matchday);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public Optional<Tournament_matchday> insert(Tournament_matchday tournament_matchday){
        String newUuid = UUID.randomUUID().toString();
        try{

            jdbcTemplate.update(
                    "INSERT INTO tournament_matchday(uuid, start_date, end_date, number, on_created, on_updated, tournament_id) " +
                            "VALUES (?,?,?,?,?,?,?)", newUuid, tournament_matchday.getStart_date(), tournament_matchday.getEnd_date(),
                    tournament_matchday.getNumber(), tournament_matchday.getOn_created(), tournament_matchday.getOn_updated(),
                    tournament_matchday.getTournament_id()
            );
            return getTournamentMatchdayByUuid(newUuid);
        }catch (Exception e){
            e.printStackTrace();
            return Optional.empty();
        }
    }

    @Override
    public Optional<Tournament_matchday> update(Tournament_matchday tournament_matchday) {

        try {
            jdbcTemplate.update("UPDATE tournament_matchday SET start_date = ?, end_date = ?, number = ?, on_updated = ? WHERE uuid = ?",
                    tournament_matchday.getStart_date(),
                    tournament_matchday.getEnd_date(),tournament_matchday.getNumber(), new Date(), tournament_matchday.getUuid());
            return getTournamentMatchdayByUuid(tournament_matchday.getUuid());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public Optional<Tournament_matchday> delete(Tournament_matchday tournament_matchday) {
        String sql = "delete from tournament_matchday where id = ?";
        try {
            jdbcTemplate.update(sql,new BeanPropertyRowMapper<>(Tournament_matchday.class),tournament_matchday.getId());
            return Optional.of(tournament_matchday);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public Optional<List<Tournament_matchday>>tournamentMatchdays(String tournament_id){
        String sql = "SELECT * FROM tournament_matchday where tournament_id=?";
        try{
            List<Tournament_matchday>tournament_matchdays= jdbcTemplate.query(sql,
                    new BeanPropertyRowMapper<>(Tournament_matchday.class), tournament_id);
            logger.debug("Jalando lista de jornadas del torneo");
            return Optional.of(tournament_matchdays);
        }catch (Exception e){
            e.printStackTrace();
            logger.debug("No habia nada alv");

        }
        return Optional.empty();
    }

    @Override
    public Optional<List<Match>>matchday_matches(String tournament_matchday_id){
        String sql = "select * \n" +
                "from mydb.match \n" +
                "where id IN ( \n" +
                "\tselect matchday_match.match_id \n" +
                "    from tournament_matchday  join matchday_match \n" +
                "    on tournament_matchday.id = matchday_match.tournament_matchday_id \n" +
                "    where tournament_matchday.id = ?);";
        try{
            List<Match>matches= jdbcTemplate.query(sql,
                    new BeanPropertyRowMapper<>(Match.class), tournament_matchday_id);
            logger.debug("Jalando lista de partidos de la jornada");
            return Optional.of(matches);
        }catch (Exception e){
            e.printStackTrace();
            logger.debug("No habia nada alv");

        }
        return Optional.empty();
    }








}
