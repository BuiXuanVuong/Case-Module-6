package com.mvc.controller;

import com.mvc.model.User;
import com.mvc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin("*")
public class FriendController {

    @Autowired
    private UserService userService;

    @RequestMapping("/invite/{userName}/{id_get}")
    public User inviteUser(@PathVariable("userName") String userName, @PathVariable("id_get") long idGet) {
        User loggedUser = userService.findByUserName(userName);
        User connect_to_person = userService.findOneById(idGet);
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

    @RequestMapping("/connect/{userName}/{idPost}")
    public User connectWith(@PathVariable("userName") String userName, @PathVariable("idPost") Long idPost) {
        User loggedUser = userService.findByUserName(userName);
        User connect_to_person = userService.findOneById(idPost);
        if(loggedUser.getInvitedUserFriends().size() == 0) {
            List<User> list = new ArrayList<>();
            list.add(connect_to_person);
            loggedUser.setUserFriends(list);
            List<User> user_invitations = loggedUser.getInvitedUserFriends();
            User inviting_user = userService.findOneById(idPost);
            user_invitations.remove(inviting_user);
            loggedUser.setInvitedUserFriends(user_invitations);
        } else {
            List<User> list = loggedUser.getUserFriends();
            list.add(connect_to_person);
            loggedUser.setUserFriends(list);
            List<User> user_invitations = loggedUser.getInvitedUserFriends();
            User inviting_user = userService.findOneById(idPost);
            user_invitations.remove(inviting_user);
            loggedUser.setInvitedUserFriends(user_invitations);
        }
        userService.saveUser(loggedUser);
        return connect_to_person;
    }

    @RequestMapping("/list-friend/{userName}")
    public List<User> getListFriend(@PathVariable("userName") String userName) {
        User selected_user_object = userService.findByUserName(userName);

        List<User> list = new ArrayList<>();
        for(User u: selected_user_object.getUserFriends()) {
            list.add(u);
        }
        for(User u: selected_user_object.getFriends()) {
            list.add(u);
        }
        return list;
    }

}
