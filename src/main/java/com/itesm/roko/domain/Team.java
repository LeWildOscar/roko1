package com.itesm.roko.domain;

import java.util.Date;

public class Team {

    private int id;

    private String uuid;

    private String name;

    private String color;

    private String country;

    private String logo;

    private Date on_created;

    private Date on_updated;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
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
