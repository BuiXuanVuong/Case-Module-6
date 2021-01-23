package com.mvc.repository;

import com.mvc.model.StatusReplyLike;
import org.springframework.data.repository.CrudRepository;

public interface StatusReplyLikeRepository extends CrudRepository<StatusReplyLike, Long> {
    StatusReplyLike findByUserIdAndStatusReplyId(Long user_id, Long statusReply_id);
    Iterable<StatusReplyLike> findAllByStatusReplyId(Long id);
}
