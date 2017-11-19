package com.itesm.roko.dao.tournament_user;

import com.itesm.roko.dao.UserDAO;
import com.itesm.roko.dao.tournament_user.SqlTournament_userDAO;
import com.itesm.roko.domain.Tournament;
import com.itesm.roko.domain.User;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.itesm.roko.domain.Tournament_user;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class Tournament_userDAOImpl implements SqlTournament_userDAO {

    @Autowired
    protected JdbcTemplate jdbcTemplate;
    @Autowired
    UserDAO daoUsuario;

    private static final Logger logger = LoggerFactory.getLogger(Tournament_user.class);

    public Optional<Tournament_user> getTournamentUserByUuid (String uuid) {
        String sql = "SELECT * FROM tournament_user WHERE uuid = ?";
        try {
            BeanPropertyRowMapper<Tournament_user> rowMapper = new BeanPropertyRowMapper<>(Tournament_user.class);
            Tournament_user tournament_user = jdbcTemplate.queryForObject(sql,rowMapper,uuid);
            return Optional.of(tournament_user);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
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

    public Optional<List<Tournament_user>> getUserTournaments (int user_id) {
        String sql = "SELECT * FROM tournament_user WHERE user_id = ?";
        try {
            List<Tournament_user> userTournaments = jdbcTemplate.query(sql,new BeanPropertyRowMapper<>(Tournament_user.class),user_id);
            return Optional.of(userTournaments);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public Optional<List<Tournament>> getTournamentsUser (String username) {
        String sql = "SELECT tournament.id, tournament.uuid, tournament.is_public, tournament.date_start, " +
                "tournament.date_end, tournament.pot, tournament.fee, tournament.level, tournament.prize_spread, " +
                "tournament.max_users, tournament.min_users, tournament.description, tournament.name, " +
                "tournament.password, tournament.public_identifier, tournament.on_created, tournament.on_updated, " +
                "tournament.prize_id FROM tournament WHERE tournament.id = (SELECT tournament_user.id FROM " +
                "tournament_user JOIN user ON tournament_user.user_id = user.id WHERE user.id = ? )";
        User user = daoUsuario.getByUsername(username).get();
        try {
            List<Tournament> list_user = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Tournament.class),user.getId());
            return Optional.of(list_user);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public Optional<List<Tournament>> getTournamentsAdminUser(String username) {
        String sql = "select * from tournament where id = (select tournament_user.id from tournament_user join user on tournament_user.user_id = user.id where user.id = ? and tournament_user.is_admin = 1)";
        User user = daoUsuario.getByUsername(username).get();
        try {
            List<Tournament> list_tournament = jdbcTemplate.query(sql,new BeanPropertyRowMapper<>(Tournament.class),user.getId());
            return Optional.of(list_tournament);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return Optional.empty();
    }


    public Optional<Tournament_user> insert (Tournament_user tournament_user) {
        String newUUid = UUID.randomUUID().toString();
        System.out.println("vamo a intentar");
        try{
            jdbcTemplate.update(
                    "INSERT INTO tournament_user (uuid, is_admin, position, is_winner, prize_amount, on_created, on_updated, tournament_id, user_id )" +
                            " VALUES (?,?,?,?,?,?,?,?,?)",
                    newUUid, tournament_user.getIs_admin(),tournament_user.getPosition(), tournament_user.getIs_winner(), tournament_user.getPrize_amount(),
                    tournament_user.getOn_created(), tournament_user.getOn_updated(), tournament_user.getTournament_id(),tournament_user.getUser_id()
            );
            System.out.println("si se pudo");
            return getTournamentUserByUuid(newUUid);
        }catch (Exception e){
            System.out.println("no c pudo :'v");
            e.printStackTrace();
            return Optional.empty();
        }
    }



    public Optional<Tournament_user> delete (Tournament_user tournament_user) {
        String sql = "DELETE FROM tournament_user WHERE uuid = ?";
        try {
            BeanPropertyRowMapper<Tournament_user> rowMapper = new BeanPropertyRowMapper<>(Tournament_user.class);
            jdbcTemplate.update(sql,rowMapper,tournament_user.getUuid());
            return Optional.of(tournament_user);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }



    public Optional<Tournament_user> update (Tournament_user tournament_user) {
        Date on_updated = new Date();
        int is_admin = tournament_user.getIs_admin();
        int position = tournament_user.getPosition();
        int isWinner = tournament_user.getIs_winner();
        int prizeAmount = tournament_user.getPrize_amount();
        String uuid = tournament_user.getUuid();
        try {
            jdbcTemplate.update(
                    "UPDATE tournament_user SET is_admin = ?, position = ?, is_winner = ?, prize_amount = ?, on_created = ?, on_updated = ? where uuid = ?",
                    is_admin, position, isWinner, prizeAmount, on_updated,uuid
            );

            return getTournamentUserById(tournament_user.getId());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

}
