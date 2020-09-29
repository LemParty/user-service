package com.lemparty.entity;


import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "profiles")
public class Profile implements Serializable {


    @Id
    private String userID;

    @Column(name = "email")
    private String email;

    @Column(name = "firstname")
    private String firstname;

    @Column(name = "lastname")
    private String lastname;

    @Column(name = "birthmonth")
    private int birthmonth;

    @Column(name = "birthday")
    private int birthday;

    @Column(name = "birthyear")
    private int birthyear;

    //@Column(name = "friendslist")
   // private String[] friendsList;

//    public boolean hasFriend(String id){
//        if(this.friendsList == null)
//            return false;
//
//        for(String s : this.friendsList){
//            if(s.equals(id))
//                return true;
//        }
//
//        return false;
//    }

//    public String[] getFriendsList() {
//        return friendsList;
//    }

//    public void setFriendsList(String[] friendsList) {
//        this.friendsList = friendsList;
//    }


    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public int getBirthmonth() {
        return birthmonth;
    }

    public void setBirthmonth(int birthmonth) {
        this.birthmonth = birthmonth;
    }

    public int getBirthday() {
        return birthday;
    }

    public void setBirthday(int birthday) {
        this.birthday = birthday;
    }

    public int getBirthyear() {
        return birthyear;
    }

    public void setBirthyear(int birthyear) {
        this.birthyear = birthyear;
    }
}
