package com.mvc.controller;

import com.mvc.model.User;
import com.mvc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//@CrossOrigin("*")
public class SearchController {

    @Autowired
    private UserService userService;

    @RequestMapping("/search/{idPost}")
    public ResponseEntity<List<User>> searchUser(@PathVariable("idPost") Long idPost) {
        User loggedUser = userService.findOneById(idPost);
        List<User> userListSearch = userService.findAll();
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
