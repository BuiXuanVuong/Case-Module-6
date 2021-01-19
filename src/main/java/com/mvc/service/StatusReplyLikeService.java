package com.mvc.service;

import com.mvc.model.StatusReplyLike;

public interface StatusReplyLikeService {
    boolean isLike(Long user_id, Long status_reply_id);
    StatusReplyLike save(StatusReplyLike statusReplyLike);
    StatusReplyLike findByUserIdAndStatusReplyId(Long user_id, Long status_reply_id);
    void deleteByUserIdAndStatusReplyId(Long user_id, Long status_reply_id);
    Iterable<StatusReplyLike> findAllByStatusReplyId(Long id);
    void deleteAllByStatusReplyId(Iterable<StatusReplyLike> statusReplyLikes);


}
