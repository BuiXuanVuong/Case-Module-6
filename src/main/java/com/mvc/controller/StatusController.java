package com.mvc.controller;

import com.mvc.model.Post;
import com.mvc.model.Status;
import com.mvc.model.User;
import com.mvc.repository.StatusRepository;
import com.mvc.repository.UserRepository;
import com.mvc.service.StatusSevice;
import com.mvc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@RestController
public class StatusController {

    @Autowired
    private StatusSevice statusSevice;

    @Autowired
    private UserService userService;


    @GetMapping(value = {"/", "/home"})
    public List<Status> getAllStatus() {
        return statusSevice.findAll();
    }


    @PostMapping("/status/{id}")
    public Status statusPostRoute(@PathVariable("id") Long id, @RequestBody Status status, BindingResult result, RedirectAttributes redirectAttributes ) {
        if(result.hasErrors()) {
            System.out.println("DID NOT PASS STATUS VALIDATIONS: Status must be more than 2 characters");
//            return "redirect:/";
            return null;
        } else {
            User loggedUser = userService.getUser(id);
            List<Status> user_statuses = loggedUser.getStatusList();
            status.setUserPost(loggedUser);
            status.setWallId(id);
            loggedUser.setStatusList(user_statuses);
            String string_id = id.toString();
            statusSevice.saveTheStatus(status);
            return status;

        }
    }





}
