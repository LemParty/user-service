package com.lemparty.service;

import com.lemparty.data.ProfileRepository;
import com.lemparty.data.UserRepository;
import com.lemparty.entity.Friend;
import com.lemparty.entity.Profile;
import com.lemparty.exception.InvalidUserException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class FriendService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private String salt;


    @Autowired
    private UserService userService;

    public List<Friend> findFriendsById(String userID, String lookupID) throws InvalidUserException {
//        List<Friend> friendList = new ArrayList<Friend>();
//
//        Profile userProfile = profileRepository.findUserByUserID(userID).get();
//        Profile lookupProfile = profileRepository.findUserByUserID(lookupID).get();
//
//        if(lookupProfile != null
//                && lookupProfile.getFriendsList() != null
//                && lookupProfile.getFriendsList().length > 0) {
//            List<Profile> profiles = userService.findProfileById(lookupProfile.getFriendsList());
//            for(Profile p : profiles){
//                Friend newFriend = new Friend(/*p.getUserID(), p.getFirstname(), p.getLastname()*/);
//                newFriend.setUser1ID();
//                if(userProfile.hasFriend(p.getUserID()) || p.getUserID().equals(userProfile.getUserID())){
//                    String[] friendArray
//                    newFriend.setMutualFriends();
//                }
//
//                friendList.add(newFriend);
//            }
//        }
//
//        return friendList;
        return null;
    }

}
