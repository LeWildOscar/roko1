package com.itesm.roko.dao;

import com.itesm.roko.domain.Tournament;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.itesm.roko.domain.Tournament_user;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class Tournament_userDAO {

    @Autowired
    protected JdbcTemplate jdbcTemplate;

    private static final Logger logger = LoggerFactory.getLogger(Tournament_user.class);

    public Optional<Tournament_user> getByUuid(String uuid){
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

    /*public Optional<Tournament_user>insert(Tournament tournament){
        String newUUid = UUID.randomUUID().toString();
        try{
            jdbcTemplate.update(
                    "INSERT INTO tournament_user()"
            )
        }
    }*/
}
