package com.itesm.roko.dao;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.itesm.roko.domain.Tournament_matchday;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Repository
public class Tournament_matchdayDAO {

    @Autowired
    protected JdbcTemplate jdbcTemplate;

    private static final Logger logger = LoggerFactory.getLogger(UserDAO.class);

    public Optional<Tournament_matchday> getByUuid(String uuid){
        String sql = "SELECT * FROM tournament_matchday WHERE uuid = ?";
        try {
            BeanPropertyRowMapper<Tournament_matchday> rowMapper = new BeanPropertyRowMapper<>(Tournament_matchday.class);
            Tournament_matchday tournament_matchday = jdbcTemplate.queryForObject(sql, rowMapper, uuid);
            logger.debug("Getting tournament_matchday con uuid: " + uuid);
            return Optional.of(tournament_matchday);
        }catch (Exception e){
            e.printStackTrace();
            logger.debug("No se encontro tournament_matchday con uuid: "+uuid);
        }
        return Optional.empty();
    }

    public Optional<Tournament_matchday> insert(Tournament_matchday tournament_matchday){
        String newUuid = UUID.randomUUID().toString();
        try{
            jdbcTemplate.update(
                    "INSERT INTO tournament_matchday(id, uuid, start_date, end_date, number, on_created, on_updated, tournament_id) " +
                            "VALUES (?,?,?,?,?,?,?,?)", newUuid, tournament_matchday.getStart_date(), tournament_matchday.getEnd_date(),
                            tournament_matchday.getNumber(), tournament_matchday.getOn_created(), tournament_matchday.getOn_updated(),
                            tournament_matchday.getTournament_id()
            );
            logger.debug("Inserting tournament_matchday");
            return getByUuid(newUuid);
        }catch (Exception e){
            e.printStackTrace();
            logger.debug("Could not insert tournament_matchday");
            return Optional.empty();
        }
    }
}
