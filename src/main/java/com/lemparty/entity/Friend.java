package com.lemparty.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "friend")
public class Friend implements Serializable {

    // Friend A (friendID?) --> Friends List, Block List
    // Should User contain the friends? or should Friends be its own DB?

    // Friend will be it's own DB
    // 1. Create Friend Table -- DONE
    // 2. Create Friend repository;  CRUD = Create, Read, Update, Delete; default is CRUD by ID
    // 3. Create/Update Friend Service; implement findFriendsById method
    // 4. Create/Update Friend Controller -- DONE; but be able to test

    @Id
    private String friendID; //Unique ID with Friend

    @Column(name = "user1ID")
    private int user1ID; //foreign key with User

    @Column(name = "friends")
    private String[] friends;

    @Column(name=" blockedFriends")
    private String[] blockedFriends;

    public String getFriendID() {
        return friendID;
    }

    public void setFriendID(String friendID) {
        this.friendID = friendID;
    }

    public String[] getFriends() {
        return friends;
    }

    public void setFriends(String[] friends) {
        this.friends = friends;
    }

    public int getUser1ID() {
        return user1ID;
    }

    public void setUser1ID(int user1ID) {
        this.user1ID = user1ID;
    }



    public String[] getBlockedFriends() {
        return blockedFriends;
    }

    public void setBlockedFriends(String[] blockedFriends) {
        this.blockedFriends = blockedFriends;
    }
}