package com.itesm.roko.domain;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class Match {

    private int id;

    private String uuid;

    @JsonFormat(pattern = "yyyy-MM-dd hh:mm")
    private Date date_start;

    @JsonFormat(pattern = "yyyy-MM-dd hh:mm")
    private Date date_end;

    private int score;

    private int home_goals;

    private int away_goals;

    private String type;

    private Date on_created;

    private Date on_updated;

    private int season_matchday_id;

    private String home_team;

    private String away_team;

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

    public int getAway_goals() {
        return away_goals;
    }

    public void setAway_goals(int away_goals) {
        this.away_goals = away_goals;
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

    public String getHome_team() {
        return home_team;
    }

    public void setHome_team(String home_team) {
        this.home_team = home_team;
    }

    public String getAway_team() {
        return away_team;
    }

    public void setAway_team(String away_team) {
        this.away_team = away_team;
    }
}
