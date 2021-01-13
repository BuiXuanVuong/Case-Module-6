package com.mvc.repository;

import com.mvc.model.Status;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StatusRepository extends JpaRepository<Status, Long> {
    Status findOneById(long id);
    List<Status> findAll();
}
