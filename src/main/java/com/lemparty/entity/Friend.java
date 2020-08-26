package com.lemparty.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "friend")
public class Friend implements Serializable {

    @Id
    private String friendID;

    @Column(name = "user1ID")
    private int user1ID;

    @Column(name = "mutualFriends")
    private String[] mutualFriends;

    @Column(name=" blockedFriends")
    private String[] blockedFriends;

    public String getFriendID() {
        return friendID;
    }

    public void setFriendID(String friendID) {
        this.friendID = friendID;
    }

    public int getUser1ID() {
        return user1ID;
    }

    public void setUser1ID(int user1ID) {
        this.user1ID = user1ID;
    }

    public String[] getMutualFriends() {
        return mutualFriends;
    }

    public void setMutualFriends(String[] mutualFriends) {
        this.mutualFriends = mutualFriends;
    }

    public String[] getBlockedFriends() {
        return blockedFriends;
    }

    public void setBlockedFriends(String[] blockedFriends) {
        this.blockedFriends = blockedFriends;
    }
}