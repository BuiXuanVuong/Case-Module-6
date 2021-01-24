package com.mvc.controller;

import com.mvc.model.ResultResponse;
import com.mvc.model.User;
import com.mvc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
public class SearchController {

    @Autowired
    private UserService userService;

    @RequestMapping("/searchFriend/{userName}")
    public ResponseEntity<List<User>> searchUser(@PathVariable("userName") String userName) {
        User loggedUser = userService.findByUserName(userName);
        List<User> userListSearch = userService.findAll();

        List<User> list = new ArrayList<>();
        for(User u: loggedUser.getUserFriends()) {
            list.add(u);
        }
        for(User u: loggedUser.getFriends()) {
            list.add(u);
        }

        for(User user: list) {
            userListSearch.remove(user);
        }

        userListSearch.remove(loggedUser);
//        if(name == null) {
//            System.out.println("Empty input");
//        }
        if(userListSearch.isEmpty()) {
            System.out.println("No match result");
        }
        return ResponseEntity.ok(userListSearch);
    }


}
