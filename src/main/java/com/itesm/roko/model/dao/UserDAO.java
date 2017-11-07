package com.itesm.roko.model.dao;


import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.itesm.roko.model.domain.User;

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

    public Optional<List<User>>list(){
        String sql = "SELECT * FROM user";
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


}
