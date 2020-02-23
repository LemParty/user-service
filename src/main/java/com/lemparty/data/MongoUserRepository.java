package com.lemparty.data;

import com.lemparty.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MongoUserRepository extends MongoRepository<User, String> {
    @Query(value="{ 'email' : ?0 }", fields="{ 'password' : 0}")
    Optional<User> findUserByEmail(String email);

    @Query(value="{ 'email' : ?0 }")
    Optional<User> findUserByEmailForAuthentication(String email);

    @Query(value="{ 'id' : ?0 }", fields="{'password' : 0}")
    Optional<User> findById(String id);


}
