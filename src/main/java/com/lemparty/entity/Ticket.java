package com.lemparty.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "ticket")
public class Ticket implements Serializable {


    @Id
    private int ticketID;

    @Column(name = "userID")
    private int gameID;

    @Column(name = "teamID")
    private int wagerID;

    public int getTicketID() {
        return ticketID;
    }

    public void setTicketID(int ticketID) {
        this.ticketID = ticketID;
    }

    public int getGameID() {
        return gameID;
    }

    public void setGameID(int gameID) {
        this.gameID = gameID;
    }

    public int getWagerID() {
        return wagerID;
    }

    public void setWagerID(int wagerID) {
        this.wagerID = wagerID;
    }
}
