package com.itesm.roko.dao;


import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.itesm.roko.domain.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Repository
public class UserDAO {

    @Autowired
    protected JdbcTemplate jdbcTemplate;

    private static final Logger logger = LoggerFactory.getLogger(UserDAO.class);

    public Optional<User> getByUuid(String uuid){
        String sql = "SELECT * FROM user WHERE uuid = ?";
        try {
            BeanPropertyRowMapper<User> rowMapper = new BeanPropertyRowMapper<>(User.class);
            User user = jdbcTemplate.queryForObject(sql, rowMapper, uuid);
            logger.debug("Getting usuario con uuid: " + uuid);
            return Optional.of(user);
        }catch (Exception e){
            e.printStackTrace();
            logger.debug("No se encontro usuario con uuid: "+uuid);
        }
        return Optional.empty();
    }

    public Optional<User> getByUsername(String username){
        String sql = "SELECT * FROM user WHERE username = ?";
        try {
            BeanPropertyRowMapper<User> rowMapper = new BeanPropertyRowMapper<>(User.class);
            User user = jdbcTemplate.queryForObject(sql, rowMapper, username);

            logger.debug("Getting usuario con username: " + username);
            return Optional.of(user);
        }catch (Exception e){
            e.printStackTrace();
            System.out.println(e.getMessage() +" getByUsername");
            logger.debug("No se encontro usuario con username: "+username);
        }
        return Optional.empty();
    }

    public Optional<User> update(User user){
        try {
            jdbcTemplate.update("UPDATE user SET " +
                            "password=? WHERE uuid=?",
                    user.getPassword(), user.getUuid());
            logger.debug("Updating user: " + user.getUuid());
            return getByUuid(user.getUuid());
        } catch (Exception e) {
            e.printStackTrace();
            logger.debug("Could not update user: " + user.getUuid());
            return Optional.empty();
        }
    }

    public Optional<User> insert(User user){
        String newUuid = UUID.randomUUID().toString();
        try{
            jdbcTemplate.update(
                    "INSERT INTO user (uuid, username, email, password, country) VALUES (?,?,?,?,?)",newUuid, user.getUsername(),user.getEmail(),user.getPassword(), user.getCountry()
            );
            logger.debug("Inserting User");
            return getByUuid(newUuid);
        }catch (Exception e){
            e.printStackTrace();
            logger.debug("Could not insert user");
            return Optional.empty();
        }
    }

    public Optional<User> delete(User user){
        try{
            jdbcTemplate.update(
                    "DELETE FROM user WHERE uuid=?",user.getUuid()
            );
            return Optional.of(user);
        }catch (Exception e){
            e.printStackTrace();
            return Optional.empty();
        }
    }

    public Optional<List<User>>list(){
        String sql = "SELECT * FROM user order by total_points desc";
        try{
            List<User>users= jdbcTemplate.query(sql,
                    new BeanPropertyRowMapper<>(User.class));
            logger.debug("Jalando lista de usuarios");
            return Optional.of(users);
        }catch (EmptyResultDataAccessException e){
            e.printStackTrace();
            logger.debug("No habia nada alv");

        }
        return Optional.empty();
    }
    /*
    public Optional<User> update(User user){
        try {
            jdbcTemplate.update("UPDATE user SET " +
                            "password=? WHERE uuid=?",
                    user.getName(), user.getLastname(), user.getStatus(),
                    Timestamp.from(Instant.now()), user.getUuid());
            logger.debug("Updating user: " + user.getUuid());
            return getByUuid(user.getUuid());
        } catch (Exception e) {
            e.printStackTrace();
            logger.debug("Could not update user: " + user.getUuid());
            return Optional.empty();
        }
    }*/



}