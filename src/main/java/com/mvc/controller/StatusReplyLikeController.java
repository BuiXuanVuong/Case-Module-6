package com.mvc.controller;

import com.mvc.model.ResultResponse;
import com.mvc.model.StatusReply;
import com.mvc.model.StatusReplyLike;
import com.mvc.model.User;
import com.mvc.service.StatusReplyLikeService;
import com.mvc.service.StatusReplyService;
import com.mvc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
public class StatusReplyLikeController {
    @Autowired
    StatusReplyLikeService statusReplyLikeService;
    @Autowired
    UserService userService;
    @Autowired
    StatusReplyService statusReplyService;

    @GetMapping("/api/like-status-reply/{status_reply_id}")
    public Iterable<StatusReplyLike> getStatusReplyLike(@PathVariable Long status_reply_id){
        Iterable<StatusReplyLike> statusReplyLike = statusReplyLikeService.findAllByStatusReplyId(status_reply_id);
        return statusReplyLike;

    }

    @PostMapping("/api/{user_id}/like-status-reply/{status_reply_id}")
    private ResultResponse likeStatusReply(@PathVariable Long user_id, @PathVariable Long status_reply_id){
        ResultResponse resultResponse = new ResultResponse();
        try {
            StatusReplyLike statusReplyLike = new StatusReplyLike();
            StatusReply statusReply = statusReplyService.findOne(status_reply_id);
            User user = userService.findOneById(user_id);
            statusReply.setTotalLikes(statusReply.getTotalLikes() + 1);
            statusReplyService.saveStatusReply(statusReply);
            statusReplyLike.setUser(user);
            statusReplyLike.setStatusReply(statusReply);
            statusReplyLikeService.save(statusReplyLike);
            resultResponse.setMessage("Ok");

        }catch (Exception e){
            resultResponse.setMessage("Lỗi");
        }
        return resultResponse;

    }
    @DeleteMapping("/api/{user_id}/unlike-status-reply/{status-reply-id}")
    private ResultResponse unlikeStatusReply(@PathVariable Long user_id, @PathVariable Long status_reply_id){
        ResultResponse resultResponse = new ResultResponse();
        try {
            StatusReplyLike statusReplyLike = new StatusReplyLike();
            statusReplyLikeService.deleteByUserIdAndStatusReplyId(user_id, status_reply_id);
            resultResponse.setMessage("Ok");
            StatusReply statusReply = statusReplyService.findOne(status_reply_id);
            statusReply.setTotalLikes(statusReply.getTotalLikes() - 1);
            statusReplyService.saveStatusReply(statusReply);
        }catch (Exception e){
            resultResponse.setMessage("Lỗi");
        }
        return resultResponse;

    }
}
