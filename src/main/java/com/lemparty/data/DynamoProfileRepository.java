package com.lemparty.data;

import com.lemparty.entity.Profile;
import com.lemparty.entity.User;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@EnableScan
@Transactional
public interface DynamoProfileRepository extends CrudRepository<Profile, String> {
    Optional<Profile> findUserByUserID(String userID);
    List<Profile> findUserByUserIDIn(String... userID);
    Optional<Profile> findUserByEmail(String email);

}
