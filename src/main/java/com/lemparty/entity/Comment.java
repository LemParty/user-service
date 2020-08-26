package com.lemparty.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

// Comment table for Bets and for Wagers and Games
@Entity
@Table(name = "comment")
public class Comment implements Serializable {


    @Id
    private int commentID;

    @Column(name = "betID")
    private int betID;

    @Column(name = "userID")
    private int userID;

    @Column(name = "content")
    private String content;

    @Column(name = "numLikes")
    private int numLikes;

    @Column(name = "numDislikes")
    private int numDislikes;

    @Column(name = "timestamp")
    private Date timestamp;

    @Column(name = "modifiedDate")
    private Date modifiedDate;

    public int getCommentID() {
        return commentID;
    }

    public void setCommentID(int commentID) {
        this.commentID = commentID;
    }

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

    public Date getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }
}
