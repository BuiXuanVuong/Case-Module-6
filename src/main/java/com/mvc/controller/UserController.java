package com.mvc.controller;

import com.mvc.model.User;
import com.mvc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
//@CrossOrigin("*")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/users/{id}")
    public ResponseEntity<User> getUserById(@PathVariable long id) {
        User user = userService.getUser(id);
        return ResponseEntity.ok(user);
    }

    @PostMapping("users")
    public User createUser(@RequestBody User user) {
        return userService.saveUser(user);
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User userDetail) {
        User user = userService.getUser(id);
        user.setBirthday(userDetail.getBirthday());
        user.setUserName(userDetail.getUserName());
        user.setEmail(userDetail.getEmail());
        user.setPassword(userDetail.getPassword());
        user.setPhone(userDetail.getPhone());
        user.setImage(userDetail.getImage());
        user.setUserFriends(userDetail.getUserFriends());
        user.setFriends(userDetail.getFriends());
        user.setStatusList(userDetail.getStatusList());
        User updateUser = userService.saveUser(user);
        return ResponseEntity.ok(updateUser);
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteUsers(@PathVariable long id) {
        User user = userService.getUser(id);
        userService.deleteUser(id);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }



}
