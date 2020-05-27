package com.lemparty.service;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.lemparty.data.DynamoProfileRepository;
import com.lemparty.data.DynamoUserRepository;
import com.lemparty.entity.Friend;
import com.lemparty.entity.Profile;
import com.lemparty.entity.Registration;
import com.lemparty.entity.User;
import com.lemparty.exception.DuplicateUserException;
import com.lemparty.exception.InvalidPasswordException;
import com.lemparty.exception.InvalidUserException;
import com.lemparty.util.PasswordUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class FriendService {

    @Autowired
    private DynamoUserRepository userRepository;

    @Autowired
    private DynamoProfileRepository profileRepository;

    @Autowired
    private String salt;

    @Autowired
    private AmazonDynamoDB amazonDynamoDB;

    @Autowired
    private UserService userService;

    public List<Friend> findFriendsById(String userID, String lookupID) throws InvalidUserException {
        List<Friend> friendList = new ArrayList<Friend>();

        Profile userProfile = profileRepository.findUserByUserID(userID).get();
        Profile lookupProfile = profileRepository.findUserByUserID(lookupID).get();

        if(lookupProfile != null && !lookupProfile.getFriendsList().isEmpty()) {
            List<Profile> profiles = userService.findProfileById(lookupProfile.getFriendsList().toArray(new String[lookupProfile.getFriendsList().size()]));
            for(Profile p : profiles){
                Friend newFriend = new Friend(p.getUserID(), p.getFirstname(), p.getLastname());
                if(userProfile.getFriendsList().contains(p.getUserID()) || p.getUserID().equals(userProfile.getUserID())){
                    newFriend.setMutual(true);
                }

                friendList.add(newFriend);
            }
        }

        return friendList;
    }

}
