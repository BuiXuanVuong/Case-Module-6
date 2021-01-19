package com.mvc.service;

import com.mvc.model.StatusReplyLike;
import com.mvc.repository.StatusReplyLikeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StatusReplyLikeServiceImpl implements StatusReplyLikeService {
    @Autowired
    private StatusReplyLikeRepository statusReplyLikeRepository;
    @Override
    public boolean isLike(Long user_id, Long status_reply_id) {
        StatusReplyLike statusReplyLike = statusReplyLikeRepository.findByUserIdAndStatusReplyId(user_id, status_reply_id);
        if(statusReplyLike != null){
            return true;
        }else {
            return false;
        }
    }

    @Override
    public StatusReplyLike save(StatusReplyLike statusReplyLike) {
        return statusReplyLikeRepository.save(statusReplyLike);
    }

    @Override
    public StatusReplyLike findByUserIdAndStatusReplyId(Long user_id, Long status_reply_id) {

        return statusReplyLikeRepository.findByUserIdAndStatusReplyId(user_id, status_reply_id);

    }

    @Override
    public void deleteByUserIdAndStatusReplyId(Long user_id, Long status_reply_id) {
        StatusReplyLike statusReplyLike = statusReplyLikeRepository.findByUserIdAndStatusReplyId(user_id, status_reply_id);
        if(statusReplyLike != null){
            statusReplyLikeRepository.delete(statusReplyLike);

        }

    }

    @Override
    public Iterable<StatusReplyLike> findAllByStatusReplyId(Long id) {
        return statusReplyLikeRepository.findAllByStatusReplyId(id);
    }

    @Override
    public void deleteAllByStatusReplyId(Iterable<StatusReplyLike> statusReplyLikes) {
        statusReplyLikeRepository.deleteAll(statusReplyLikes);

    }
}
