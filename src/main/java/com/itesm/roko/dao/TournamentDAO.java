package com.itesm.roko.dao;

import com.itesm.roko.domain.Match;
import com.itesm.roko.domain.User;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.itesm.roko.domain.Tournament;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.UUID;

@Repository
public class TournamentDAO {

    @Autowired
    protected JdbcTemplate jdbcTemplate;

    private static final Logger logger = LoggerFactory.getLogger(TournamentDAO.class);

    public Optional<Tournament> getByUuid(String uuid){
        String sql = "SELECT * FROM tournament WHERE uuid = ?";
        try {
            BeanPropertyRowMapper<Tournament> rowMapper = new BeanPropertyRowMapper<>(Tournament.class);
            Tournament tournament = jdbcTemplate.queryForObject(sql, rowMapper, uuid);
            logger.debug("Getting tournament con uuid: " + uuid);
            return Optional.of(tournament);
        }catch (Exception e){
            e.printStackTrace();
            logger.debug("No se encontro torneo con uuid: "+uuid);
        }
        return Optional.empty();
    }

    public Optional<Tournament> getByPublicIdentifier(String public_identifier){
        String sql = "SELECT * FROM tournament WHERE public_identifier = ?";
        try {
            BeanPropertyRowMapper<Tournament> rowMapper = new BeanPropertyRowMapper<>(Tournament.class);
            Tournament tournament = jdbcTemplate.queryForObject(sql, rowMapper, public_identifier);
            logger.debug("Getting tournament con public_identifier: " + public_identifier);
            return Optional.of(tournament);
        }catch (Exception e){
            e.printStackTrace();
            logger.debug("No se encontro torneo con public_identifier: "+public_identifier);
        }
        return Optional.empty();
    }

    public Optional<Tournament> insert(Tournament tournament){
        String newUuid = UUID.randomUUID().toString();
        Random rnd = new Random();
        int public_identifier = 1000 + rnd.nextInt(9000);
        System.out.println("A punto de insertar torneo");
        try{
            jdbcTemplate.update(
                    "INSERT INTO tournament(uuid, is_public, date_start, date_end, pot, fee, level, prize_spread, max_users, min_users, description, name, " +
                            "password, public_identifier, on_created, on_updated, prize_id) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)",
                    newUuid, tournament.getIs_public(), tournament.getDate_start(), tournament.getDate_end(),
                    tournament.getPot(), tournament.getFee(), tournament.getLevel(), tournament.getPrize_spread(), tournament.getMax_users(),
                    tournament.getMin_users(), tournament.getDescription(), tournament.getName(), tournament.getPassword(), public_identifier,
                    tournament.getOn_created(), tournament.getOn_updates(), 1
            );
            System.out.println("Acabó de insertar toneo jot");
            return getByUuid(newUuid);
        }catch (Exception e){
            e.printStackTrace();
            return Optional.empty();
        }
    }

    public Optional<List<Tournament>>list(){
        String sql = "SELECT * FROM tournament ORDER BY pot DESC ";
        try{
            List<Tournament>tournaments= jdbcTemplate.query(sql,
                    new BeanPropertyRowMapper<>(Tournament.class));
            logger.debug("Jalando lista de tournaments");
            return Optional.of(tournaments);
        }catch (EmptyResultDataAccessException e){
            e.printStackTrace();
            logger.debug("No habia nada alv");

        }
        return Optional.empty();
    }
    /*public Optional<List<User>>tournamentUsers(String tournament_id){
        String sql = ""
    }*/

    public Optional<List<User>>getTournamentUsers(String tournament_id){
        String sql = "select * \n" +
                "from mydb.user \n" +
                "where id IN ( \n" +
                "\tselect tournament_user.user_id \n" +
                "    from tournament  join tournament_user \n" +
                "    on tournament.id = tournament_user.tournament_id \n" +
                "    where tournament.id = ?);";
        try{
            List<User>users= jdbcTemplate.query(sql,
                    new BeanPropertyRowMapper<>(User.class),tournament_id);
            logger.debug("Jalando lista de usuarios del torneo");
            return Optional.of(users);
        }catch (EmptyResultDataAccessException e){
            e.printStackTrace();
            logger.debug("No habia nada alv");

        }
        return Optional.empty();
    }



}
