package com.mvc.controller;

import com.mvc.model.Status;
import com.mvc.model.User;
import com.mvc.service.StatusReplyService;
import com.mvc.service.StatusSevice;
import com.mvc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin("*"
        )
public class StatusController {

    @Autowired
    private StatusSevice statusSevice;

    @Autowired
    private UserService userService;

    @Autowired
    private StatusReplyService statusReplyService;


    @GetMapping(value = {"/{id}", "/home/{id}"})
    public List<Status> getAllStatus(@PathVariable("id") Long id) {
//        statusReplyService.findAllByIdStatus(id);
        return statusSevice.findWallStatuses(id);
    }


    @PostMapping("/status/{id}")
    public Status statusPostRoute(@PathVariable("id") Long id, @RequestBody Status status, BindingResult result, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
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
            statusSevice.saveStatus(status);
            return status;
        }
    }

    @PutMapping("/status/{id}")
    public Status statusPutRoute(@PathVariable("id") Long id, @RequestBody Status statusUpdate, BindingResult result, RedirectAttributes redirectAttributes) {
        Status status = statusSevice.findOne(id);
        status.setContent(statusUpdate.getContent());
        statusSevice.saveTheStatus(status);
        return status;
    }

    @DeleteMapping("status/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteStatus(@PathVariable("id") Long id) {
        Status status = statusSevice.findOne(id);
        statusSevice.deleteStatus(id);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }


}
