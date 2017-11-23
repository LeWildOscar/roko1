package com.itesm.roko.dao.matchday_match;

import com.itesm.roko.domain.Matchday_match;
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
    public Optional<Matchday_match> getByUuid(String uuid) {
        String sql = "SELECT * FROM matchday_match where uuid = ?";

        try {
            Matchday_match matchday_match = jdbcTemplate.queryForObject(sql,new BeanPropertyRowMapper<>(Matchday_match.class),uuid);
            return Optional.of(matchday_match);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public Optional<Matchday_match> getById(int id) {
        String sql = "SELECT * FROM matchday_match where id = ?";
        try {

            Matchday_match jornada_match = jdbcTemplate.queryForObject(sql,new BeanPropertyRowMapper<>(Matchday_match.class),id);
            return Optional.of(jornada_match);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public Optional<Matchday_match> insert(Matchday_match matchday_match) {
        Date on_created = new Date();
        String newUuid = UUID.randomUUID().toString();
        try {
            jdbcTemplate.update(
                    "INSERT INTO matchday_match(uuid, on_created, on_updated, tournament_matchday_id, match_id) " +
                            "VALUES (?,?,?,?,?)",newUuid, matchday_match.getOn_created(), matchday_match.getOn_updated(),
                            matchday_match.getTournament_matchday_id(), matchday_match.getMatch_id()
            );

            return getByUuid(newUuid);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }

        return Optional.empty();
    }

    /*@Override
    public Optional<Jornada_match> delete(Jornada_match jornada_match) {
        String sql = "DELETE FROM matchday_match WHERE id = ?";
        try {
            jdbcTemplate.update(sql,new BeanPropertyRowMapper<>(Jornada_match.class),jornada_match.getId());
            return Optional.of(jornada_match);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return Optional.empty();
    }*/


}
