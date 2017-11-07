package com.itesm.roko.model.domain;

import java.util.Date;

public class Match {

    private int id;

    private String uuid;

    private Date date_start;

    private Date date_end;

    private int score;

    private int home_goals;

    private int away_score;

    private String type;

    private Date on_created;

    private Date on_updated;

    private int season_matchday_id;

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

    public Date getDate_start() {
        return date_start;
    }

    public void setDate_start(Date date_start) {
        this.date_start = date_start;
    }

    public Date getDate_end() {
        return date_end;
    }

    public void setDate_end(Date date_end) {
        this.date_end = date_end;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getHome_goals() {
        return home_goals;
    }

    public void setHome_goals(int home_goals) {
        this.home_goals = home_goals;
    }

    public int getAway_score() {
        return away_score;
    }

    public void setAway_score(int away_score) {
        this.away_score = away_score;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public int getSeason_matchday_id() {
        return season_matchday_id;
    }

    public void setSeason_matchday_id(int season_matchday_id) {
        this.season_matchday_id = season_matchday_id;
    }
}
