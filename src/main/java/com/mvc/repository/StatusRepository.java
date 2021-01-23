package com.mvc.repository;

import com.mvc.model.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StatusRepository extends JpaRepository<Status, Long> {
    Status findOneById(long id);
    List<Status> findAll();

    @Query(value = "SELECT * FROM STATUS WHERE WALL_ID = ?1", nativeQuery = true)
    List<Status> findWallStatuses(Long user_wall_id);
}
