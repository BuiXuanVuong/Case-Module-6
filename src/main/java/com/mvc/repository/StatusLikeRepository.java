package com.mvc.repository;

import com.mvc.model.StatusLike;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface StatusLikeRepository extends CrudRepository<StatusLike, Long> {
    StatusLike findByUserIdAndStatusId(Long user_id, Long status_id);
    List<StatusLike> findAllByUserId(Long user_id);

    Iterable<StatusLike> findAllByStatus_Id(Long user_id);
}
