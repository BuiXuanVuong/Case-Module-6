package com.mvc.repository;

import com.mvc.model.StatusReply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StatusReplyRepository extends JpaRepository<StatusReply, Long> {
//    @Query(value = "DELETE FROM statuses_replies WHERE status_id = ?1", nativeQuery=true)
//    void removeStatusReplies(Long status_id);

    StatusReply findOneById(long id);

    List<StatusReply> findAllById(long id);
}
