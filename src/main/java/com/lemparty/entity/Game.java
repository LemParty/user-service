package com.lemparty.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "game")
public class Game implements Serializable {


    @Id
    private int gameID;

    @Column(name = "homeTeamID")
    private int homeTeamID;

    @Column(name = "awayTeamID")
    private int awayTeamID;

    @Column(name = "homeTeamScore")
    private int homeTeamScore;

    @Column(name = "awayTeamScore")
    private int awayTeamScore;

    @Column(name = "winnerTeamID")
    private int winnerTeamID;

    @Column(name = "timestamp")
    private Date timestamp;

    public int getGameID() {
        return gameID;
    }

    public void setGameID(int gameID) {
        this.gameID = gameID;
    }

    public int getHomeTeamID() {
        return homeTeamID;
    }

    public void setHomeTeamID(int homeTeamID) {
        this.homeTeamID = homeTeamID;
    }

    public int getAwayTeamID() {
        return awayTeamID;
    }

    public void setAwayTeamID(int awayTeamID) {
        this.awayTeamID = awayTeamID;
    }

    public int getHomeTeamScore() {
        return homeTeamScore;
    }

    public void setHomeTeamScore(int homeTeamScore) {
        this.homeTeamScore = homeTeamScore;
    }

    public int getAwayTeamScore() {
        return awayTeamScore;
    }

    public void setAwayTeamScore(int awayTeamScore) {
        this.awayTeamScore = awayTeamScore;
    }

    public int getWinnerTeamID() {
        return winnerTeamID;
    }

    public void setWinnerTeamID(int winnerTeamID) {
        this.winnerTeamID = winnerTeamID;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }
}
