package com.mvc.controller;

import com.mvc.model.ResultResponse;
import com.mvc.model.User;
import com.mvc.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
public class SearchController {

    @Autowired
    private UserService userService;

    @RequestMapping("/searchFriend/{userName}")
    public ResponseEntity<List<User>> searchUser(@PathVariable("userName") String userName, Optional<String> search) {
        User loggedUser = userService.findByUserName(userName);
        List<User> userList;
        if (search.isPresent()) {
            userList = userService.searchByName(search);
            if (userList.contains(loggedUser)) {
                userList.remove(loggedUser);
            }
        } else {
            userList = userService.findAll();
            userList.remove(loggedUser);
            if (userList.isEmpty()) {
                System.out.println("No match result");
            }
        }
        return ResponseEntity.ok(userList);
    }


}
