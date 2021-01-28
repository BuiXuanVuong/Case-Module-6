package com.mvc.controller;

import com.mvc.model.Status;
import com.mvc.model.StatusReply;
import com.mvc.model.User;
import com.mvc.service.impl.StatusReplyService;
import com.mvc.service.impl.StatusSevice;
import com.mvc.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin("*")
public class StatusReplyController {

    @Autowired
    UserService userService;

    @Autowired
    StatusSevice statusSevice;

    @Autowired
    StatusReplyService statusReplyService;


    @GetMapping("/status/reply/{status_id}")
    private List<StatusReply> getStatusReplies(@RequestBody StatusReply statusReply, BindingResult result, @PathVariable("status_id") Long id) {
        Status status = statusSevice.findOne(id);
        return status.getRepliedStatusMessages();
    }

    @PostMapping("/status/reply/{status_id}/{user_that_replied}")
    private StatusReply replyToStatus(@RequestBody StatusReply statusReply, BindingResult result, @PathVariable("status_id") Long id, @PathVariable("user_that_replied") String user_that_replied) {
        User loggedUser = userService.findByUserName(user_that_replied);
        Status status_to_reply_to = statusSevice.findOne(id);
        statusReply.setStatusReplyingTo(status_to_reply_to);
        statusReply.setUserWhoRepliedToStatus(loggedUser);
        statusReply.setUserReply(user_that_replied);
        statusReply.setImageReply(loggedUser.getImage());
        statusReplyService.saveStatusReply(statusReply);
        return statusReply;
    }

    @PutMapping("/status/reply/{status_replied_id}/{user_that_replied}/{user_login}")
    private StatusReply updateReplyToStatus(@RequestBody StatusReply updateStatusReply, BindingResult result, @PathVariable("status_replied_id") Long id, @PathVariable("user_that_replied") String user_that_replied, @PathVariable("user_login") String user_login) {
        User userReply = userService.findByUserName(user_that_replied);
        User userLogin = userService.findByUserName(user_login);
        if (userReply == userLogin ) {
            StatusReply statusReply = statusReplyService.findOne(id);
            statusReply.setStatusReplyBody(updateStatusReply.getStatusReplyBody());
            statusReplyService.saveStatusReply(statusReply);
            return statusReply;
        }
        return null;
    }

    @DeleteMapping("/status/reply/{status_replied_id}")
    public ResponseEntity<Map<String, Boolean>> deleteStatusReply(@PathVariable("status_replied_id") Long status_replied_id) {
        statusReplyService.deleteStatusReply(status_replied_id);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}
