package com.itesm.roko.domain;

import java.util.Date;

public class Matchday_match {

    private int id;

    private String uuid;

    private Date on_created;

    private Date on_updated;

    private int tournament_matchday_id;

    private int match_id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public Date getOn_created() {
        return on_created;
    }

    public void setOn_created(Date on_created) {
        this.on_created = on_created;
    }

    public Date getOn_updated() {
        return on_updated;
    }

    public void setOn_updated(Date on_updated) {
        this.on_updated = on_updated;
    }

    public int getTournament_matchday_id() {
        return tournament_matchday_id;
    }

    public void setTournament_matchday_id(int tournament_matchday_id) {
        this.tournament_matchday_id = tournament_matchday_id;
    }

    public int getMatch_id() {
        return match_id;
    }

    public void setMatch_id(int match_id) {
        this.match_id = match_id;
    }
}
