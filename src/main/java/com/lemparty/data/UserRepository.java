package com.lemparty.data;

import com.lemparty.entity.User;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional
public interface UserRepository extends CrudRepository<User, String> {

    Optional<User> findUserByEmail(String email);

}
