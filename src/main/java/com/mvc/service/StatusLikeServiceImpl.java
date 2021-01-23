package com.mvc.service;

import com.mvc.model.StatusLike;
import com.mvc.repository.StatusLikeRepository;
import com.mvc.repository.StatusRepository;
import com.mvc.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class StatusLikeServiceImpl implements StatusLikeService{
    @Autowired
    private StatusLikeRepository statusLikeRepository;

    @Autowired
    private StatusRepository statusRepository;

    @Autowired
    private UserRepository userRepository;


    @Override
    public List<StatusLike> findAll() {
        return null;
    }

    @Override
    public StatusLike findById(Long id) {
        return  statusLikeRepository.findById(id).get();
    }

    @Override
    public StatusLike save(StatusLike statusLike) {
        return statusLikeRepository.save(statusLike);
    }

    @Override
    public boolean delete(Long id) {
        statusLikeRepository.deleteById(id);
        StatusLike statusLike = statusLikeRepository.findById(id).orElse(null);
        return statusLike == null;
    }

    @Override
    public StatusLike deleteByUserIdAndStatusId(Long user_id, Long status_id) {
        StatusLike deleteStatusLike = statusLikeRepository.findByUserIdAndStatusId(user_id, status_id);
        if(deleteStatusLike != null){
            statusLikeRepository.delete(deleteStatusLike);
            return deleteStatusLike;
        }
        return null;
    }

    @Override
    public List<Long> getAllStatusLikedIdByUserId(Long id) {
        List<StatusLike> statusLikeList = statusLikeRepository.findAllByUserId(id);
        List<Long> statusLikedIdList = new ArrayList<>();
        for(StatusLike statusLike: statusLikeList){
            statusLikedIdList.add(statusLike.getId());
        }
        return statusLikedIdList;
    }

    @Override
    public Iterable<StatusLike> findAllByStatus_Id(Long status_id) {

        return statusLikeRepository.findAllByStatus_Id(status_id);
    }

    @Override
    public void deleteAllStatusLikeByStatus_id(Iterable<StatusLike> statusLikes) {
        statusLikeRepository.deleteAll(statusLikes);

    }
}
