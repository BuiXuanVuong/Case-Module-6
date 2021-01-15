package com.mvc.controller;

import com.mvc.model.User;
import com.mvc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class FriendController {

    @Autowired
    private UserService userService;

    @RequestMapping("/invite/{person_to_connect_id}")
    public User inviteUser(@PathVariable("person_to_connect_id") long id) {
        User loggedUser = userService.findByName("user1");
        User connect_to_person = userService.findOneById(id);
        if (loggedUser.getInvitedUserFriends().size() == 0) {
            List<User> list = new ArrayList<>();
            list.add(connect_to_person);
            loggedUser.setInvitedFriends(list);
        } else {
            List<User> list = loggedUser.getInvitedFriends();
            list.add(connect_to_person);
            loggedUser.setInvitedFriends(list);
        }
        userService.saveUser(loggedUser);
        return loggedUser;
    }
}
