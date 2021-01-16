package com.mvc.repository;

import com.mvc.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findAll();

    User findOneById(long id);

    User findOneByUserName(String name);

    @Query(value = "SELECT * FROM friendships WHERE friend_id != ?1", nativeQuery = true)
    List<User> selectNotFriends(long id);

    @Query(value = "SELECT * FROM friendships", nativeQuery = true)
    List<User> selectAllFriendship();

    @Query(value = "SELECT email FROM users", nativeQuery = true)
    List<String> findAllEmails();

    List<User> findByUserNameContaining(String name);
}
