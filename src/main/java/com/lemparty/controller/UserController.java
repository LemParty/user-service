package com.lemparty.controller;

import java.util.concurrent.atomic.AtomicLong;

import com.lemparty.entity.PasswordUser;
import com.lemparty.entity.User;
import com.lemparty.exception.InvalidPasswordException;
import com.lemparty.exception.InvalidUserException;
import com.lemparty.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "users")
public class UserController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @Autowired
    UserService userService;

    @PutMapping("/user")
    public ResponseEntity register(@RequestBody PasswordUser user) {

        User createdUser = null;
        try {
            createdUser = userService.registerUser(user);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity(e.getMessage(), HttpStatus.CONFLICT);
        }


        return new ResponseEntity(user, HttpStatus.OK);
    }

    @PostMapping("/user")
    public ResponseEntity update(@RequestBody User newuser) {

        boolean user = false;
        try {
            user = userService.update(newuser);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity(e.getMessage(), HttpStatus.CONFLICT);
        }

        return new ResponseEntity(user, HttpStatus.OK);
    }

    @RequestMapping("/user")
    public ResponseEntity login(@RequestBody User user) {
        User loggedInUser = null;
        try {
            loggedInUser = userService.authenticateUser(user.getEmail(), user.getPassword());
        } catch (InvalidUserException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_GATEWAY);
        } catch (InvalidPasswordException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.UNAUTHORIZED);
        } catch(Exception e){
            e.printStackTrace();
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity(loggedInUser, HttpStatus.OK);

    }
}