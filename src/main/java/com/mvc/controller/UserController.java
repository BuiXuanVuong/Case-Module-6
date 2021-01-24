package com.mvc.controller;

import com.mvc.model.User;
import com.mvc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin("*")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/users/{userName}")
    public ResponseEntity<User> getUser(@PathVariable String userName) {
        User user = userService.getUser(userName);
        return ResponseEntity.ok(user);
    }
    @GetMapping("/users")
    public List<User> getAllUser(){
        return userService.findAll();

    }
    @GetMapping("/users/findUserByName")
    public ResponseEntity<User> findByNameContains(@RequestParam("name") String name){
      return new ResponseEntity<>(userService.findByUserName(name), HttpStatus.OK) ;
    }

    @PostMapping("/users")
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

    @GetMapping("/search/{userName}")
    public ResponseEntity<User> getUserByUserName(@PathVariable("userName") String userName) {
        User user = userService.findByUserName(userName);
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }



}
