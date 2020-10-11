package com.lemparty.data;

import com.lemparty.entity.Friend;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional
public interface FriendRepository extends CrudRepository<Friend, String> {

    Optional<Friend> findFriendById(String friendID);
}
