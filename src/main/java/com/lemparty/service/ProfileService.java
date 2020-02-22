package com.lemparty.service;

import com.lemparty.data.MongoUserDAO;
import com.lemparty.entity.Profile;
import com.lemparty.entity.User;
import com.lemparty.entity.User;
import com.lemparty.exception.UserExistsException;
import org.springframework.beans.factory.annotation.Autowired;

public class ProfileService {

    @Autowired
    private MongoUserDAO userDAO;

//    public User save(User user) throws UserExistsException {
//
//        User profileCreated = userDAO.create(user);
//
//        if(profileCreated != null)
//            return profileCreated;
//        else
//            return null; //TODO do proper error handling
//    }
//
//    public User getUserByEmail(User user) throws UserExistsException {
//
//        User userGotten = userDAO.getUserByEmail((user).getEmail());
//
//        return userGotten;
//    }

}
