package com.lemparty.service;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.model.CreateTableRequest;
import com.amazonaws.services.dynamodbv2.model.ProvisionedThroughput;
import com.amazonaws.services.dynamodbv2.util.TableUtils;
import com.lemparty.data.DynamoProfileRepository;
import com.lemparty.data.DynamoUserRepository;
import com.lemparty.entity.Profile;
import com.lemparty.entity.Registration;
import com.lemparty.entity.User;
import com.lemparty.exception.InvalidPasswordException;
import com.lemparty.exception.InvalidUserException;
import com.lemparty.exception.DuplicateUserException;
import com.lemparty.util.PasswordUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Component
public class UserService {

    @Autowired
    private DynamoUserRepository userRepository;

    @Autowired
    private DynamoProfileRepository profileRepository;

    @Autowired
    private String salt;

    @Autowired
    private AmazonDynamoDB amazonDynamoDB;

    @Transactional
    public Profile createUserAndProfile(Registration registration) throws DuplicateUserException {
        User user = registration.getUser();
        Profile profile = registration.getProfile();

        // This is TEMP: Set the USER ID, system managed
        user.setUserID(UUID.randomUUID().toString());
        profile.setUserID(user.getUserID());

        // Hash the provided Password
        Optional<String> hashedPassword = PasswordUtil.hashPassword(user.getPassword(), salt);
        user.setPassword(hashedPassword.get());

        // Check to see if user exists
        Optional<User> existingUser = userRepository.findById(user.getUserID());//.findUserByEmail(user.getEmail());

        if(!existingUser.isPresent()){
            User createdUser = userRepository.save(user);
            Profile createdProfile = profileRepository.save(profile);

            return createdProfile;
        } else{
            System.out.println("User already exists for "+user.getEmail());
            throw new DuplicateUserException(user.getEmail());
        }

    }

    public Profile authenticateUser(String email, String password) throws InvalidUserException, InvalidPasswordException {
        Optional<User> user = userRepository.findUserByEmail(email);

        if(!user.isPresent() || (user.isPresent() && user.get().getPassword() == null)){
            throw new InvalidUserException(email);
        }

        boolean validPassword = PasswordUtil.verifyPassword(password, user.get().getPassword(), salt);
        if(validPassword){
            return findProfileById(user.get().getUserID());
        }

        throw new InvalidPasswordException(email);
    }



    private User updateUser(User user) throws InvalidUserException {
        Optional<User> existingUser = userRepository.findById(user.getUserID());

        if(!existingUser.isPresent()){
            throw new InvalidUserException(user.getEmail());
        }

        User updated = userRepository.save(user);
        return updated;
    }

    private List<User> findAll(){
        Iterable<User> userIter = userRepository.findAll();

        List<User> userList = new ArrayList<User>();

        userIter.forEach(userList::add);


        return userList;
    }

    public User findUserByEmail(String email){
        return userRepository.findUserByEmail(email).get();
    }

    public Profile updateProfile(String id, Profile profile) throws InvalidUserException {

        Optional<Profile> existingUser = profileRepository.findById(id);

        if(!existingUser.isPresent()){
            throw new InvalidUserException(profile.getEmail());
        }

        profile.setUserID(id);
        Profile updated = profileRepository.save(profile);
        return updated;
    }

    public Profile findProfileById(String id) throws InvalidUserException {
        Optional<Profile> profileGotten = profileRepository.findUserByUserID(id);

        if(!profileGotten.isPresent())
            throw new InvalidUserException(id);

        return profileGotten.get();
    }

//    public Profile findProfileByEmail(String email) throws InvalidUserException {
//
//        Optional<Profile> userGotten = profileRepository.findUserByEmail(email);
//
//        if(!userGotten.isPresent()){
//            throw new InvalidUserException(email);
//        }
//
//        return userGotten.get();
//    }



}
