package com.lemparty.data;

import com.lemparty.entity.User;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@EnableScan
@Transactional
public interface DynamoUserRepository extends CrudRepository<User, String> {

    Optional<User> findUserByEmail(String email);

}
