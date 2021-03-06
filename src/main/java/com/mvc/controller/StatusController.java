package com.mvc.controller;

import com.mvc.model.Status;
import com.mvc.model.User;
import com.mvc.service.impl.StatusReplyService;
import com.mvc.service.impl.StatusSevice;
import com.mvc.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin("*")
public class StatusController {

    @Autowired
    private StatusSevice statusSevice;

    @Autowired
    private UserService userService;

    @Autowired
    private StatusReplyService statusReplyService;


    @GetMapping(value = {"/{userName}", "/home/{id}"})
    public List<Status> getAllStatus(@PathVariable("userName") String userName) {
        User user = userService.findByUserName(userName);
        System.out.println(user.getId());
        return statusSevice.findWallStatuses(user.getId());
    }

    @PostMapping("/status/{userName}")
    public Status statusPostRoute(@PathVariable("userName") String userName, @RequestBody Status status, BindingResult result, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            System.out.println("DID NOT PASS STATUS VALIDATIONS: Status must be more than 2 characters");
            return null;
        } else {
            User loggedUser = userService.findByUserName(userName);
            List<Status> user_statuses = loggedUser.getStatusList();
            status.setUserPost(loggedUser);
            status.setWallId(loggedUser.getId());
            status.setImageWhoPostStatus(loggedUser.getImage());
            status.setNameWhoPostStatus(loggedUser.getUserName());
            loggedUser.setStatusList(user_statuses);
            String string_id = loggedUser.getId().toString();
            statusSevice.saveStatus(status);
            return status;
        }
    }

    @PutMapping("/status/{id}")
    public Status statusPutRoute(@PathVariable("id") Long id, @RequestBody Status statusUpdate, BindingResult result, RedirectAttributes redirectAttributes) {
        Status status = statusSevice.findOne(id);
        status.setContent(statusUpdate.getContent());
        status.setImageURL(statusUpdate.getImageURL());
        statusSevice.saveTheStatus(status);
        return status;
    }

    @DeleteMapping("status/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteStatus(@PathVariable("id") Long id) {
        Status status = statusSevice.findOne(id);
        statusSevice.deleteStatus(id);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }

    @GetMapping("status/friend/{userNameFriend}")
    public List<Status> getStatusFriend(@PathVariable("userNameFriend") String userNameFriend) {
        User user_friend = userService.findByUserName(userNameFriend);
        return statusSevice.findWallStatuses(user_friend.getId());
    }


    @RequestMapping("status/friend/{userNameLogin}/{userNameFriend}")
    public Status statusFriend(@PathVariable("userNameLogin") String userNameLogin, @PathVariable("userNameFriend") String userNameFriend, @RequestBody Status status) {
        User user_login = userService.findByUserName(userNameLogin);
        User user_friend = userService.findByUserName(userNameFriend);
        status.setUserPost(user_login);
        status.setWallId(user_friend.getId());
        status.setNameWhoPostStatus(userNameLogin);
        status.setImageWhoPostStatus(user_login.getImage());
        statusSevice.saveStatus(status);
        return status;
    }


}
