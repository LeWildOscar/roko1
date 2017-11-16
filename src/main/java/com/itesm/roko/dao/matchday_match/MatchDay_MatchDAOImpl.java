package com.itesm.roko.dao.matchday_match;

import com.itesm.roko.domain.Jornada_match;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@Repository
public class MatchDay_MatchDAOImpl implements SqlMatchDay_MatchDAO {

    @Autowired
    protected JdbcTemplate jdbcTemplate;


    @Override
    public Optional<Jornada_match> getMatchDayMatchByUuid(String uuid) {
        String sql = "SELECT * FROM matchday_match where uuid = ?";

        try {
            Jornada_match jornada_match = jdbcTemplate.queryForObject(sql,new BeanPropertyRowMapper<>(Jornada_match.class),uuid);
            return Optional.of(jornada_match);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public Optional<Jornada_match> getMatchDayMatchById(int id) {
        String sql = "SELECT * FROM matchday_match where id = ?";
        try {

            Jornada_match jornada_match = jdbcTemplate.queryForObject(sql,new BeanPropertyRowMapper<>(Jornada_match.class),id);
            return Optional.of(jornada_match);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public Optional<Jornada_match> insertMatchDayMatch(Jornada_match jornada_match) {
        Date on_created = new Date();
        String newUuid = UUID.randomUUID().toString();
        String sql = "INSERT INTO matchday_match (uuid,on_created,tournament_match_id,match_id) VALUES (?,?,?,?)";
        try {
            Jornada_match aux_jornada_match = jdbcTemplate.queryForObject(sql,
                    new BeanPropertyRowMapper<>(Jornada_match.class), newUuid, on_created,
                    jornada_match.getTournament_matchday_id(),jornada_match.getMatch_id());
            return Optional.of(aux_jornada_match);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }

        return Optional.empty();
    }

    @Override
    public Optional<Jornada_match> deleteMatchDayMatch(Jornada_match jornada_match) {
        String sql = "DELETE FROM matchday_match WHERE id = ?";
        try {
            jdbcTemplate.update(sql,new BeanPropertyRowMapper<>(Jornada_match.class),jornada_match.getId());
            return Optional.of(jornada_match);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return Optional.empty();
    }
}
