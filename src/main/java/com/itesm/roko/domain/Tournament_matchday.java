package com.itesm.roko.domain;

import java.util.Date;

public class Tournament_matchday {

    private int id;

    private String uuid;

    private Date start_date;

    private Date end_date;

    public Date getStart_date() {
        return start_date;
    }

    public void setStart_date(Date start_date) {
        this.start_date = start_date;
    }

    public Date getEnd_date() {
        return end_date;
    }

    public void setEnd_date(Date end_date) {
        this.end_date = end_date;
    }

    private int number;

    private Date on_created;

    private Date on_updated;

    private int tournament_id;

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



    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
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

    public int getTournament_id() {
        return tournament_id;
    }

    public void setTournament_id(int tournament_id) {
        this.tournament_id = tournament_id;
    }
}
