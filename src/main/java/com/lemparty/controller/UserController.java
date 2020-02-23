package com.lemparty.controller;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import com.lemparty.entity.Profile;
import com.lemparty.entity.Registration;
import com.lemparty.entity.User;
import com.lemparty.entity.User;
import com.lemparty.exception.InvalidPasswordException;
import com.lemparty.exception.InvalidUserException;
import com.lemparty.service.ProfileService;
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
    private UserService userService;

    @Autowired
    private ProfileService profileService;

    @PostMapping("")
    public ResponseEntity createUser(@RequestBody Registration registrationObject) {

        User user = registrationObject.getUser();
        Profile profile = registrationObject.getProfile();

        try {
            User createdUser = userService.createUser(user);
            Profile createdProfile = profileService.createProfile(createdUser.getUserID(),  profile);

            return new ResponseEntity<Registration>(new Registration(createdUser, createdProfile), HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.CONFLICT);
        }

    }

    @RequestMapping("")
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

    @PutMapping("/{id}/profile")
    public ResponseEntity updateProfile(@PathVariable String id, @RequestBody Profile profile) {

        Profile userProfile;

        try {
            userProfile = profileService.update(id, profile);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity(e.getMessage(), HttpStatus.CONFLICT);
        }

        return new ResponseEntity(userProfile, HttpStatus.OK);
    }

    @GetMapping("/{id}/profile")
    public ResponseEntity get(@PathVariable(name = "id") String id) {
        Profile userProfile = null;
        try {
            userProfile = profileService.findById(id);
        } catch(Exception e){
            e.printStackTrace();
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity(userProfile, HttpStatus.OK);

    }
}