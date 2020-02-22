package com.lemparty.service;

import com.lemparty.data.MongoUserDAO;
import com.lemparty.entity.PasswordUser;
import com.lemparty.entity.User;
import com.lemparty.entity.User;
import com.lemparty.exception.InvalidPasswordException;
import com.lemparty.exception.InvalidUserException;
import com.lemparty.exception.UserExistsException;
import com.lemparty.util.PasswordUtil;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;
import java.util.UUID;

public class UserService {

    @Autowired
    private String salt;

    @Autowired
    private MongoUserDAO userDAO;

    public User registerUser(PasswordUser user) throws UserExistsException {

        //TODO: Validate User logic here

        // This is TEMP: Set the USER ID, system managed
        user.setUserID(UUID.randomUUID().toString());

        // Hash the provided Password
        Optional<String> hashedPassword = PasswordUtil.hashPassword(user.getPassword(), salt);
        user.setPassword(hashedPassword.get());

        User userCreated = userDAO.create(user);

        user.setPassword(null);
        return user;
    }

    public User authenticateUser(String email, String password) throws InvalidUserException, InvalidPasswordException {
         User user = userDAO.getUserByEmail(email);

        if(user == null || user.getPassword() == null){
            throw new InvalidUserException(email);
        }

        boolean validPassword = PasswordUtil.verifyPassword(password, user.getPassword(), salt);
        if(validPassword){
            return user;
        }

        throw new InvalidPasswordException(email);
    }


    public boolean update(User user) throws InvalidUserException {

        boolean updated = userDAO.update(user);
        return updated;
    }

}
