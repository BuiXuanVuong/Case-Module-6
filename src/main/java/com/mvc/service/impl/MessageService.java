package com.mvc.service.impl;

import com.mvc.model.Message;
import com.mvc.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageService {

    @Autowired
    private MessageRepository messageRepository;

    public void saveMessage(Message message) {
        messageRepository.save(message);
    }

    public List<Message> findWallMessages(Long user_wall_id) {
        return messageRepository.findWallMessages(user_wall_id);
    }

    public Message findOneById(Long id) {
        return messageRepository.findOneById(id);
    }

    public void remove(Message message_to_delete) {
        messageRepository.delete(message_to_delete);
    }
}
