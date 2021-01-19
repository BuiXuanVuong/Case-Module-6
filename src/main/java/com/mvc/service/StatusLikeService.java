package com.mvc.service;

import com.mvc.model.StatusLike;

import java.util.List;

public interface StatusLikeService {
    List<StatusLike> findAll();
    StatusLike findById(Long id);
    StatusLike save( StatusLike statusLike);
    boolean delete( Long id);

//    boolean isLike(Long user_id, Long status_id) ;

    StatusLike deleteByUserIdAndStatusId(Long user_id, Long status_id);

    List<Long> getAllStatusLikedIdByUserId(Long id);

    Iterable<StatusLike> findAllByStatus_Id(Long user_id);

    void deleteAllStatusLikeByStatus_id(Iterable<StatusLike> statusLikes);


}
