package com.mvc.controller;

import com.mvc.model.Post;
import com.mvc.model.Status;
import com.mvc.repository.StatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class StatusController {

    @Autowired
    private StatusRepository statusRepository;


    @GetMapping("/status")
    public List<Status> getAllStatus() {
        return statusRepository.findAll();
    }

    @PostMapping("/status")
    public Status createStatus(@RequestBody Status status) {
        return statusRepository.save(status);
    }




}
