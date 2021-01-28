package com.mvc.controller;
import com.mvc.model.User;
import com.mvc.service.impl.JwtService;
import com.mvc.service.impl.RoleService;
import com.mvc.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
@RestController
@CrossOrigin("*")
@RequestMapping("/api/admin")
public class AdminController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;
    @GetMapping("/users")
    public ResponseEntity<Iterable<User>> getAll(@RequestParam("search")Optional<String> search) {
        if(search.isPresent()){
            Iterable<User> users = userService.searchByName(search);
            return new ResponseEntity<>(users, HttpStatus.OK);
        } else {
            Iterable<User> users = userService.findAll();
            return new ResponseEntity<>(users, HttpStatus.OK);
        }
    }
    @GetMapping("/users/")
    @PutMapping("/users/{id}/block")
    public ResponseEntity<User> blockUser(@PathVariable Long id, @RequestBody User user) {
        Optional<User> userOptional = this.userService.findById(id);
        if (!userOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
//        userOptional.get().setNonBanned(false);
        userService.save(userOptional.get());
        return new ResponseEntity<>(userOptional.get(), HttpStatus.OK);
    }
    @PutMapping("/users/{id}/unblock")
    public ResponseEntity<User> unblockUser(@PathVariable Long id, @RequestBody User user) {
        Optional<User> userOptional = this.userService.findById(id);
        if (!userOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
//        userOptional.get().setNonBanned(true);
        userService.save(userOptional.get());
        return new ResponseEntity<>(userOptional.get(), HttpStatus.OK);
    }
}
