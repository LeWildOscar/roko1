package com.itesm.roko.dao;


import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.itesm.roko.domain.Prize;

import javax.ws.rs.Produces;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Repository
public class PrizeDAO {

    @Autowired
    protected  JdbcTemplate jdbcTemplate;

    public Optional<Prize> getByUuid(String uuid){
        String sql = "SELECT * FROM prize WHERE uuid = ?";
        try {
            BeanPropertyRowMapper<Prize> rowMapper = new BeanPropertyRowMapper<>(Prize.class);
            Prize prize = jdbcTemplate.queryForObject(sql, rowMapper, uuid);
            return Optional.of(prize);
        }catch (Exception e){
            e.printStackTrace();
        }
        return Optional.empty();
    }

    public Optional<Prize> getById(String id){
        String sql = "SELECT * FROM prize WHERE id = ?";
        try {
            BeanPropertyRowMapper<Prize> rowMapper = new BeanPropertyRowMapper<>(Prize.class);
            Prize prize = jdbcTemplate. queryForObject(sql, rowMapper, id);
            return Optional.of(prize);
        }catch (Exception e){
            e.printStackTrace();
        }
        return Optional.empty();
    }

    public Optional<Prize> insert(Prize prize){
        String newUuid = UUID.randomUUID().toString();
        try{
            jdbcTemplate.update(
                    "INSERT INTO prize(uuid, name, points, type, on_created, on_updated) VALUES (?,?,?,?,?,?)", newUuid, prize.getName(), prize.getPoints(), prize.getType(),
                    prize.getOn_created(), prize.getOn_updated()
            );
            return getByUuid(newUuid);
        }catch(Exception e){
            e.printStackTrace();
            return Optional.empty();
        }
    }

}
