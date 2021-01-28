package com.mvc.repository;

import com.mvc.model.StatusReply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StatusReplyRepository extends JpaRepository<StatusReply, Long> {

    StatusReply findOneById(long id);

    List<StatusReply> findAllById(long id);
}
