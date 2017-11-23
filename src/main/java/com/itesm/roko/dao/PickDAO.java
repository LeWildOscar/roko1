package com.itesm.roko.dao;


import com.itesm.roko.domain.Pick;
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
import java.util.UUID;

@Repository
public class PickDAO {

    @Autowired
    protected JdbcTemplate jdbcTemplate;

    private static final Logger logger = LoggerFactory.getLogger(PickDAO.class);

    public Optional<Pick> getByUuid(String uuid){
        String sql = "SELECT * FROM pick WHERE uuid = ?";
        try {
            BeanPropertyRowMapper<Pick> rowMapper = new BeanPropertyRowMapper<>(Pick.class);
            Pick pick = jdbcTemplate.queryForObject(sql, rowMapper, uuid);
            logger.debug("Getting pick con uuid: " + uuid);
            return Optional.of(pick);
        }catch (Exception e){
            e.printStackTrace();
            logger.debug("No se encontro pick con uuid: "+uuid);

        }
        return Optional.empty();
    }

    public Optional<Pick> insert(Pick pick){
        String newUuid = UUID.randomUUID().toString();
        try{
            jdbcTemplate.update(
                    "INSERT INTO pick(uuid, on_created, on_updated, prediction, tournament_match_id, user_id, match_id) " +
                            "VALUES (?,?,?,?,?,?,?)", newUuid, pick.getOn_created(), pick.getOn_updated(), pick.getPrediction(),
                            pick.getTournament_match_id(), pick.getUser_id(), pick.getMatch_id()
            );
            return getByUuid(newUuid);
        }catch(Exception e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }



}
