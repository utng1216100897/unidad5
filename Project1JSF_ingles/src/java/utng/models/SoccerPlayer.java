/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utng.models;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author gerardo
 */
@Entity
@Table(name="soccerPlayer")
public class SoccerPlayer implements Serializable{
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id_soccerPlayer")
    private Long idSoccerPlayer;
    
    @Column(name="name_soccerPlayer", length=10)
    private String nameSoccerPlayer;
    
    @Column(name="lastName_soccerPlayer", length=30)
    private String lastNameSoccerPlayer;
    
    @Column(name="nacionality_soccerPlayer", length=10)
    private String nacionalitySoccerPlayer;
    
    @ManyToOne()
    @JoinColumn(name="id_team")
    private Team team;

    public SoccerPlayer(Long idSoccerPlayer, String nameSoccerPlayer, Team team,String lastNameSoccerPlayer, String nacionalitySoccerPlayer) {
        super();
        this.idSoccerPlayer = idSoccerPlayer;
        this.nameSoccerPlayer = nameSoccerPlayer;
        this.nacionalitySoccerPlayer = nacionalitySoccerPlayer;
        this.lastNameSoccerPlayer = lastNameSoccerPlayer;
        this.team = team;
    }

    public SoccerPlayer() {
        this.idSoccerPlayer=0L;
    }
    public Long getIdSoccerPlayer() {
        return idSoccerPlayer;
    }
    public void setIdSoccerPlayer(Long idSoccerPlayer) {
        this.idSoccerPlayer = idSoccerPlayer;
    }
    public String getNameSoccerPlayer() {
        return nameSoccerPlayer;
    }
    public void setNameSoccerPlayer(String nameSoccerPlayer) {
        this.nameSoccerPlayer = nameSoccerPlayer;
    }
   
    public Team getTeam() {
        return team;
    }
    public void setTeam(Team team) {
        this.team = team;
    }

    public void setLastNameSoccerPlayer(String lastNameSoccerPlayer) {
        this.lastNameSoccerPlayer = lastNameSoccerPlayer;
    }

    public String getLastNameSoccerPlayer() {
        return lastNameSoccerPlayer;
    }

    public void setNacionalitySoccerPlayer(String nacionalitySoccerPlayer) {
        this.nacionalitySoccerPlayer = nacionalitySoccerPlayer;
    }

    public String getNacionalitySoccerPlayer() {
        return nacionalitySoccerPlayer;
    }
   
}