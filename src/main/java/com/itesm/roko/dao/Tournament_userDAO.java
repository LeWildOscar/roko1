package com.itesm.roko.dao;

import com.sun.org.apache.xpath.internal.operations.Bool;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.itesm.roko.domain.Tournament_user;
import com.itesm.roko.dao.UserDAO;
import com.itesm.roko.domain.User;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class Tournament_userDAO implements SqlTournament_userDAO {

    @Autowired
    protected JdbcTemplate jdbcTemplate;
    @Autowired
    UserDAO daoUsuario;

    private static final Logger logger = LoggerFactory.getLogger(Tournament_user.class);

    public Optional<Tournament_user> getTournamentUserByUuid (String uuid) {
        String sql = "SELECT * FROM tournament_user WHERE uuid = ?";
        try {
            BeanPropertyRowMapper<Tournament_user> rowMapper = new BeanPropertyRowMapper<>(Tournament_user.class);
            Tournament_user tournament_user = jdbcTemplate.queryForObject(sql, rowMapper, uuid);
            logger.debug("Getting tournament_user con uuid: " + uuid);
            return Optional.of(tournament_user);
        }catch (Exception e){
            e.printStackTrace();
            logger.debug("No se encontro torneo_user con uuid: "+uuid);
        }
        return Optional.empty();
    }

    public Optional<Tournament_user> getTournamentUserById (int id) {
        String sql = "Select * from tournament_user where id = ?";

        try {
            BeanPropertyRowMapper<Tournament_user> rowMapper = new BeanPropertyRowMapper<>(Tournament_user.class);
            Tournament_user tournament_user = jdbcTemplate.queryForObject(sql,rowMapper,id);
            return Optional.of(tournament_user);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.empty();

    }

    public Optional<List<Tournament_user>> getUserTournaments () {
        String sql = "SELECT * FROM tournament_user";
        try {
            List<Tournament_user> userTournaments = jdbcTemplate.query(sql,new BeanPropertyRowMapper<Tournament_user>());
            return Optional.of(userTournaments);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }


    public Optional<Tournament_user> insert (Tournament_user tournament_user, String username) {
        String newUUid = UUID.randomUUID().toString();

        try{
            User usuario = daoUsuario.getByUsername(username).get();
            int userId = usuario.getId();
            jdbcTemplate.update(
                    "INSERT INTO tournament_user (uuid, is_admin, position, is_winner, prize_amount, on_created, on_updated, tournament_id, user_id )" +
                            " VALUES (?,?,?,?,?,?,?,?,?)",
                    newUUid, tournament_user.getIs_admin(),tournament_user.getPosition(), tournament_user.getIs_winner(), tournament_user.getPrize_amount(),
                    tournament_user.getOn_created(), tournament_user.getOn_updated(), tournament_user.getTournament_id(), userId
            );
            return getTournamentUserByUuid(newUUid);
        }catch (Exception e){
            e.printStackTrace();
            return Optional.empty();
        }
    }



    public Optional<Boolean> delete (String uuid) {
        String sql = "DELETE FROM tourname_user WHERE uuid = ?";
        try {
            BeanPropertyRowMapper<Tournament_user> rowMapper = new BeanPropertyRowMapper<>(Tournament_user.class);
            jdbcTemplate.update(sql,rowMapper,uuid);
            return Optional.of(new Boolean(true));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }



    public Optional<Tournament_user> update (Tournament_user tournament_user, String uuid) {
        Date on_updated = new Date();
        int is_admin = tournament_user.getIs_admin();
        int position = tournament_user.getPosition();
        int isWinner = tournament_user.getIs_winner();
        int prizeAmount = tournament_user.getPrize_amount();
        try {
            jdbcTemplate.update(
                    "UPDATE SET is_admin = ?, position = ?, is_winner = ?, prize_amount = ?, on_created = ?, on_updated = ? where uuid = ?",
                    is_admin, position, isWinner, prizeAmount, on_updated,uuid
            );
            Tournament_user aux_tournament_user = getTournamentUserById(tournament_user.getId()).get();
            return Optional.of(aux_tournament_user);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

}
