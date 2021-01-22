package com.mvc.repository;

import com.mvc.model.MessageReply;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageReplyRepository extends CrudRepository<MessageReply, Long> {

}
