package com.itesm.roko.dao;

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

    public Optional<Tournament>insert(Tournament tournament){
        String newUuid = UUID.randomUUID().toString();
        try{
            jdbcTemplate.update(
                    "INSERT INTO tournament(uuid, is_public, date_start, date_end, pot, fee, level, prize_spread, max_users, min_users, description, name, " +
                            "password, public_identifier, on_created, on_updated, prize_id) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)",
                    newUuid, tournament.getIs_public(), tournament.getDate_start(), tournament.getDate_end(),
                    tournament.getPot(), tournament.getFee(), tournament.getLevel(), tournament.getPrize_spread(), tournament.getMax_users(),
                    tournament.getMin_users(), tournament.getDescription(), tournament.getName(), tournament.getPassword(), tournament.getPublic_indentifier(),
                    tournament.getOn_created(), tournament.getOn_updates(), tournament.getPrize_id()
            );
            return getByUuid(newUuid);
        }catch (Exception e){
            e.printStackTrace();
            return Optional.empty();
        }
    }
}
