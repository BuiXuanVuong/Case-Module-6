package com.mvc.service.impl;

import com.mvc.model.Status;
import com.mvc.repository.StatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StatusSevice {

    @Autowired
    private StatusRepository statusRepository;

    public StatusSevice(StatusRepository statusRepository) {
        this.statusRepository = statusRepository;
    }

    public void saveStatus(Status status) {
        statusRepository.save(status);
    }

    public void saveTheStatus(Status status) {
        statusRepository.save(status);
    }

    public Status findOne(long id) {
        return statusRepository.findOneById(id);
    }

    public List<Status> findAll() {
        return statusRepository.findAll();
    };

    public void deleteStatus(long id) {
        statusRepository.deleteById(id);
    }

    public List<Status> findWallStatuses(Long user_wall_id) {
        return statusRepository.findWallStatuses(user_wall_id);
    }


}
