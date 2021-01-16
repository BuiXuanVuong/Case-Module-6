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
        return connect_to_person;
    }

    @RequestMapping("/connect/{person_to_connect_id}")
    public User connectWith(@PathVariable("person_to_connect_id") Long id) {
        User loggedUser = userService.findByName("user8");
        User connect_to_person = userService.findOneById(id);
        if(loggedUser.getInvitedUserFriends().size() == 0) {
            List<User> list = new ArrayList<>();
            list.add(connect_to_person);
            loggedUser.setUserFriends(list);
            List<User> user_invitations = loggedUser.getInvitedUserFriends();
            User inviting_user = userService.findOneById(id);
            user_invitations.remove(inviting_user);
            loggedUser.setInvitedUserFriends(user_invitations);
        } else {
            List<User> list = loggedUser.getUserFriends();
            list.add(connect_to_person);
            loggedUser.setUserFriends(list);
            List<User> user_invitations = loggedUser.getInvitedUserFriends();
            User inviting_user = userService.findOneById(id);
            user_invitations.remove(inviting_user);
            loggedUser.setInvitedUserFriends(user_invitations);
        }
        userService.saveUser(loggedUser);
        return connect_to_person;
    }
}
