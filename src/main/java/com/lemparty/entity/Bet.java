package com.lemparty.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

//Winner_Lost_Bets table, but shortened down to Bet
@Entity
@Table(name = "bet")
public class Bet implements Serializable {


    @Id
    private int betID;

    @Column(name = "userID")
    private int userID;

    @Column(name = "gameID")
    private int gameID;

    @Column(name = "winnerBet")
    private String winnerBet;

    @Column(name = "content")
    private int content;

    @Column(name = "numLikes")
    private int numLikes;

    @Column(name = "numDislikes")
    private int numDislikes;

    @Column(name = "timestamp")
    private Date timestamp;

    public int getBetID() {
        return betID;
    }

    public void setBetID(int betID) {
        this.betID = betID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getGameID() {
        return gameID;
    }

    public void setGameID(int gameID) {
        this.gameID = gameID;
    }

    public String getWinnerBet() {
        return winnerBet;
    }

    public void setWinnerBet(String winnerBet) {
        this.winnerBet = winnerBet;
    }

    public int getContent() {
        return content;
    }

    public void setContent(int content) {
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
