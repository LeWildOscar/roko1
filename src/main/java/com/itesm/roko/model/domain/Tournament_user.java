package com.itesm.roko.model.domain;

import java.util.Date;

public class Tournament_user {

    private int id;

    private String uuid;

    private int is_admin;

    private int position;

    private int is_winner;

    private int prize_amount;

    private Date on_created;

    private Date on_updated;

    private int tournament_id;

    private int user_id;

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

    public int getIs_admin() {
        return is_admin;
    }

    public void setIs_admin(int is_admin) {
        this.is_admin = is_admin;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public int getIs_winner() {
        return is_winner;
    }

    public void setIs_winner(int is_winner) {
        this.is_winner = is_winner;
    }

    public int getPrize_amount() {
        return prize_amount;
    }

    public void setPrize_amount(int prize_amount) {
        this.prize_amount = prize_amount;
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

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }
}
