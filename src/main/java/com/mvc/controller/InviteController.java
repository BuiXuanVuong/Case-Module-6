package com.mvc.controller;

import com.mvc.model.User;
import com.mvc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class InviteController {

    @Autowired
    private UserService userService;

    @RequestMapping("/user/invite/{user_id}")
    public List<User> inviteFriend(@PathVariable("user_id") Long id) {
        User loggedUser = userService.findByName("user1");
        User selected_user_object = userService.findOneById(id);
        List<User> inviting_users = selected_user_object.getInvitedUserFriends();
        return inviting_users;
    }

}