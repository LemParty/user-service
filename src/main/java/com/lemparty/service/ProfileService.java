package com.lemparty.service;

import com.lemparty.data.MongoProfileRepository;
import com.lemparty.entity.Profile;
import com.lemparty.exception.InvalidUserException;
import com.lemparty.exception.DuplicateUserException;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class ProfileService {

    @Autowired
    private MongoProfileRepository profileRepository;

    public Profile createProfile(String id, Profile profile){
        Optional<Profile> existingUser = profileRepository.findById(id);


        if(existingUser.isPresent()){
            return null;
        }

        // Means Profile doesn't exist, persist provided or create shell
        if(profile == null){
            profile = new Profile();
        }

        profile.setUserID(id);
        Profile created = profileRepository.insert(profile);

        return created;


    }
    public Profile update(String id, Profile profile) throws InvalidUserException {

        Optional<Profile> existingUser = profileRepository.findById(id);

        if(!existingUser.isPresent()){
            throw new InvalidUserException(profile.getEmail());
        }

        profile.setUserID(id);
        Profile updated = profileRepository.save(profile);
        return updated;
    }

    public Profile findById(String id) throws InvalidUserException {

        Optional<Profile> userGotten = profileRepository.findById(id);

        if(!userGotten.isPresent())
            throw new InvalidUserException(id);


        return userGotten.get();
    }

    public Profile findByEmail(String email) throws InvalidUserException {

        Optional<Profile> userGotten = profileRepository.findUserByEmail(email);

        if(!userGotten.isPresent()){
            throw new InvalidUserException(email);
        }

        return userGotten.get();
    }

}
