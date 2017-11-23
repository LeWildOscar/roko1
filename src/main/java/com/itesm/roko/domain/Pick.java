package com.itesm.roko.domain;

import java.util.Date;

public class Pick {

    private int id;

    private String uuid;

    private Date on_created;

    public Date getOn_updated() {
        return on_updated;
    }

    public void setOn_updated(Date on_updated) {
        this.on_updated = on_updated;
    }

    private Date on_updated;

    private int prediction;

    private int tournament_match_id;

    private int user_id;

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

    public int getPrediction() {
        return prediction;
    }

    public void setPrediction(int prediction) {
        this.prediction = prediction;
    }

    public int getTournament_match_id() {
        return tournament_match_id;
    }

    public void setTournament_match_id(int tournament_match_id) {
        this.tournament_match_id = tournament_match_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getMatch_id() {
        return match_id;
    }

    public void setMatch_id(int match_id) {
        this.match_id = match_id;
    }
}
