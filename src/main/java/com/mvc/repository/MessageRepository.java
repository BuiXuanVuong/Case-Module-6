package com.mvc.repository;

import com.mvc.model.Message;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends CrudRepository<Message, Long> {
    @Query(value = "SELECT * FROM MESSAGES WHERE USER_WALL_ID = ?1", nativeQuery = true)
    List<Message> findWallMessages(Long user_wall_id);

    Message findOneById(Long id);
}
