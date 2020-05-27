package com.lemparty.controller;

import com.lemparty.entity.Friend;
import com.lemparty.entity.Profile;
import com.lemparty.entity.Registration;
import com.lemparty.entity.User;
import com.lemparty.exception.InvalidPasswordException;
import com.lemparty.exception.InvalidUserException;
import com.lemparty.service.FriendService;
import com.lemparty.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping(value = "users")
public class FriendController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @Autowired
    private FriendService friendService;



    @GetMapping("/{userID}/friendslist/{lookupID}")
    public ResponseEntity get(@PathVariable(name = "userID") String userID, @PathVariable(name = "lookupID") String lookupID) {
        List<Friend> friendList = null;
        try {
            friendList = friendService.findFriendsById(userID, lookupID);
        } catch(Exception e){
            e.printStackTrace();
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity(friendList, HttpStatus.OK);
    }
}