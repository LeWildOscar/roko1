package com.itesm.roko.dao;

import com.itesm.roko.domain.Matchday_match;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.itesm.roko.domain.Match;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Repository
public class MatchDAO {

    @Autowired
    protected JdbcTemplate jdbcTemplate;

    private static final Logger logger = LoggerFactory.getLogger(UserDAO.class);

    public Optional<Match> getByUuid(String uuid){
        String sql = "SELECT * FROM match WHERE uuid = ?";
        try {
            BeanPropertyRowMapper<Match> rowMapper = new BeanPropertyRowMapper<>(Match.class);
            Match match = jdbcTemplate.queryForObject(sql, rowMapper, uuid);
            logger.debug("Getting match con uuid: " + uuid);
            return Optional.of(match);
        }catch (Exception e){
            e.printStackTrace();
            logger.debug("No se encontro match con uuid: "+uuid);
        }
        return Optional.empty();
    }

    public Optional<Match>insert(Match match){
        String newUuid = UUID.randomUUID().toString();
        try{
            jdbcTemplate.update(
                    "INSERT INTO match (uuid, date_start, date_end, score, home_goals, away_goals, type, on_created, on_updated, season_matchday_id) " +
                            "VALUES (?,?,?,?,?,?,?,?,?,?)", newUuid, match.getDate_start(), match.getDate_end(), match.getScore(), match.getHome_goals(),
                            match.getAway_goals(), match.getType(), match.getOn_created(), match.getOn_updated(), match.getSeason_matchday_id()
            );
            return getByUuid(newUuid);
        }catch (Exception e){
            e.printStackTrace();
        }
        return Optional.empty();
    }

    public Optional<List<Match>>getByTeam(String team){
        String sql = "SELECT * FROM match WHERE home_team=? OR away_team=?";
        try{
            List<Match>matches= jdbcTemplate.query(sql,
                    new BeanPropertyRowMapper<>(Match.class), team);
            logger.debug("Jalando lista de partidos chakas");
            return Optional.of(matches);
        }catch (EmptyResultDataAccessException e){
            e.printStackTrace();
            logger.debug("No habia nada alv");

        }
        return Optional.empty();
    }

    public Optional<List<Match>>list(){
        String sql = "SELECT * FROM mydb.match";
        try{
            List<Match>users= jdbcTemplate.query(sql,
                    new BeanPropertyRowMapper<>(Match.class));
            logger.debug("Jalando lista de partidos");
            return Optional.of(users);
        }catch (EmptyResultDataAccessException e){
            e.printStackTrace();
            logger.debug("No habia nada alv");

        }
        return Optional.empty();
    }


}
