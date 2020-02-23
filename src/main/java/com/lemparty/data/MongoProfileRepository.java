package com.lemparty.data;

import com.lemparty.entity.Profile;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MongoProfileRepository extends MongoRepository<Profile, String> {
    @Query(value="{ 'email' : ?0 }")
    Optional<Profile> findUserByEmail(String email);

}
