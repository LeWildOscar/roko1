package com.itesm.roko.model.dao.user;

import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;


@Entity
public class User {

    @Id
    private int id;
    @Column(name = "uuid")
    private String uuid;
    @Column(name = "username")
    private String username;
    @Column(name = "email")
    private String email;
    @Column(name = "password")
    private String password;
    @Column(name = "on_create")
    private Date onCreate;
    @Column(name = "on_update")
    private Date onUpdate;
    @Column(name = "total_points")
    private int total_points;
    @Column(name = "losts")
    private int losts;
    @Column(name = "played")
    private int played;
    @Column(name = "country")
    private String country;
    @Column(name = "total_money")
    private int total_money;
    @Column(name = "money_in")
    private int money_in;
    @Column(name = "money_out")
    private int money_out;
    @Column(name = "wins")
    private int wins;


    public User () {}


}
