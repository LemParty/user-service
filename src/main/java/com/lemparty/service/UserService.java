package com.lemparty.service;

import com.lemparty.data.MongoUserRepository;
import com.lemparty.entity.User;
import com.lemparty.exception.InvalidPasswordException;
import com.lemparty.exception.InvalidUserException;
import com.lemparty.exception.DuplicateUserException;
import com.lemparty.util.PasswordUtil;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class UserService {

    @Autowired
    private MongoUserRepository userRepository;

    @Autowired
    private String salt;

    public User createUser(User user) throws DuplicateUserException {

        //TODO: Validate User logic here

        // This is TEMP: Set the USER ID, system managed
        user.setUserID(UUID.randomUUID().toString());

        // Hash the provided Password
        Optional<String> hashedPassword = PasswordUtil.hashPassword(user.getPassword(), salt);
        user.setPassword(hashedPassword.get());

        // Check to see if user exists
        Optional<User> existingUser = userRepository.findUserByEmail(user.getEmail());

        if(!existingUser.isPresent()){
            User createdUser = userRepository.insert(user);
            return createdUser;
        } else{
            System.out.println("User already exists for "+user.getEmail());
            throw new DuplicateUserException(user.getEmail());
        }

    }

    public User authenticateUser(String email, String password) throws InvalidUserException, InvalidPasswordException {
        Optional<User> user = userRepository.findUserByEmailForAuthentication(email);

        if(!user.isPresent() || (user.isPresent() && user.get().getPassword() == null)){
            throw new InvalidUserException(email);
        }

        boolean validPassword = PasswordUtil.verifyPassword(password, user.get().getPassword(), salt);
        if(validPassword){
            return user.get();
        }

        throw new InvalidPasswordException(email);
    }


    private User update(User user) throws InvalidUserException {
        Optional<User> existingUser = userRepository.findById(user.getUserID());

        if(!existingUser.isPresent()){
            throw new InvalidUserException(user.getEmail());
        }

        User updated = userRepository.save(user);
        return updated;
    }

    private List<User> findAll(){
        return userRepository.findAll();
    }

    public User findUserByEmail(String email){
        return userRepository.findUserByEmail(email).get();
    }


}
