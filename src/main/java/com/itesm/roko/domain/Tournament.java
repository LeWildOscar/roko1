package com.itesm.roko.domain;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class Tournament {

    private int id;

    private String uuid;

    private int is_public;

    @JsonFormat(pattern = "yyyy-MM-dd hh:mm")
    private Date date_start;
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm")
    private Date date_end;

    private int pot;

    private int fee;

    private int level;

    private int prize_spread;

    private int max_users;

    private int min_users;

    private String description;

    private String name;

    private String password;

    private String public_identifier;

    @JsonFormat(pattern = "yyyy-MM-dd hh:mm")
    private Date on_created;

    @JsonFormat(pattern = "yyyy-MM-dd hh:mm")
    private Date on_updates;

    private int prize_id;



    private String prize_uuid; //---------------------- :)

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

    public int getIs_public() {
        return is_public;
    }

    public void setIs_public(int is_public) {
        this.is_public = is_public;
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

    public int getPot() {
        return pot;
    }

    public void setPot(int pot) {
        this.pot = pot;
    }

    public int getFee() {
        return fee;
    }

    public void setFee(int fee) {
        this.fee = fee;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getPrize_spread() {
        return prize_spread;
    }

    public void setPrize_spread(int prize_spread) {
        this.prize_spread = prize_spread;
    }

    public int getMax_users() {
        return max_users;
    }

    public void setMax_users(int max_users) {
        this.max_users = max_users;
    }

    public int getMin_users() {
        return min_users;
    }

    public void setMin_users(int min_users) {
        this.min_users = min_users;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPublic_identifier() {
        return public_identifier;
    }

    public void setPublic_identifier(String public_identifier) {
        this.public_identifier = public_identifier;
    }

    public Date getOn_created() {
        return on_created;
    }

    public void setOn_created(Date on_created) {
        this.on_created = on_created;
    }

    public Date getOn_updates() {
        return on_updates;
    }

    public void setOn_updates(Date on_updates) {
        this.on_updates = on_updates;
    }

    public int getPrize_id() {
        return prize_id;
    }

    public void setPrize_id(int prize_id) {
        this.prize_id = prize_id;
    }

    public String getPrize_uuid() {
        return prize_uuid;
    }

    public void setPrize_uuid(String prize_uuid) {
        this.prize_uuid = prize_uuid;
    }
}
