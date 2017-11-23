package com.itesm.roko.dao.tournament_matchday;

import com.itesm.roko.domain.Tournament_matchday;
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

    @Override
    public Optional<Tournament_matchday> getTournamentMatchdayByUuid (String uuid) {
        String sql = "Select * from tournament_matchday where uuid = ?";
        try {
            Tournament_matchday tournament_matchday = jdbcTemplate.queryForObject(sql,new BeanPropertyRowMapper<>(Tournament_matchday.class),uuid);
            return Optional.of(tournament_matchday);
        } catch (Exception e) {

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
}
