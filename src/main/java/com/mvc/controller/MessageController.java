package com.mvc.controller;

import com.mvc.model.Message;
import com.mvc.model.Status;
import com.mvc.model.User;
import com.mvc.service.MessageService;
import com.mvc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@RestController
@CrossOrigin("*")
public class MessageController {

    @Autowired
    private UserService userService;

    @Autowired
    private MessageService messageService;

    @PostMapping("/message/{userLogin}/{userPath}")
    private Message createMessage(@PathVariable("userLogin") String userLogin, @PathVariable("userPath") String userPath, @RequestBody Message message) {
        User loggedUser = userService.findByUserName(userLogin);
        User userGet = userService.findByUserName(userPath);
        message.setMessagePoster(loggedUser);
        message.setUser_wall_id(userGet.getId());
        message.setSender(loggedUser.getUserName());
        message.setImageSender(loggedUser.getImage());
        messageService.saveMessage(message);
        return message;
    }

    @GetMapping(value = {"/message/{userLogin}"})
    public List<Message> getAllMessage(@PathVariable("userLogin") String userLogin) {
        User user = userService.findByUserName(userLogin);
        System.out.println(user.getId());
//        statusReplyService.findAllByIdStatus(id);
        return messageService.findWallMessages(user.getId());
    }



}



