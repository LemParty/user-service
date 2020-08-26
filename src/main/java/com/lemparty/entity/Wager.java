package com.lemparty.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "wager")
public class Wager implements Serializable {


    @Id
    private int wagerID;

    @Column(name = "userID")
    private int userID;

    @Column(name = "teamID")
    private int teamID;

    @Column(name = "line")
    private String line;

    @Column(name = "odds") //-110
    private String odds;

    @Column(name = "content")
    private String content;

    @Column(name = "numLikes")
    private int numLikes;

    @Column(name = "numDislikes")
    private int numDislikes;

    @Column(name = "timestamp")
    private Date timestamp;

    public int getWagerID() {
        return wagerID;
    }

    public void setWagerID(int wagerID) {
        this.wagerID = wagerID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getTeamID() {
        return teamID;
    }

    public void setTeamID(int teamID) {
        this.teamID = teamID;
    }

    public String getLine() {
        return line;
    }

    public void setLine(String line) {
        this.line = line;
    }

    public String getOdds() {
        return odds;
    }

    public void setOdds(String odds) {
        this.odds = odds;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getNumLikes() {
        return numLikes;
    }

    public void setNumLikes(int numLikes) {
        this.numLikes = numLikes;
    }

    public int getNumDislikes() {
        return numDislikes;
    }

    public void setNumDislikes(int numDislikes) {
        this.numDislikes = numDislikes;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }
}
