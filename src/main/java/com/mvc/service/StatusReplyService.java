package com.mvc.service;

import com.mvc.model.StatusReply;
import com.mvc.repository.StatusReplyRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StatusReplyService {
    private StatusReplyRepository statusReplyRepository;

    public StatusReplyService(StatusReplyRepository statusReplyRepository) {
        this.statusReplyRepository = statusReplyRepository;
    }

    public void saveStatusReply(StatusReply status_to_reply_to) {
        statusReplyRepository.save(status_to_reply_to);
    }

    public void deleteStatusReply(Long status_reply_id) {
        statusReplyRepository.deleteById(status_reply_id);
    }

    public StatusReply findOne(long id) {
       return statusReplyRepository.findOneById(id);
    }

    public List<StatusReply> findAllByIdStatus(long id) {
       return statusReplyRepository.findAllById(id);
    }

}
