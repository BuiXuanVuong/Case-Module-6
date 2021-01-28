package com.mvc.controller;

import com.mvc.model.ResultResponse;
import com.mvc.model.Status;
import com.mvc.model.StatusLike;
import com.mvc.model.User;
import com.mvc.service.StatusLikeService;
import com.mvc.service.impl.StatusSevice;
import com.mvc.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@CrossOrigin("*")
@RestController
public class StatusLikeController {

    @Autowired
    private StatusLikeService statusLikeService;

    @Autowired
    private StatusSevice statusSevice;
    @Autowired
    private UserService userService;
    @GetMapping("/api/like/{id}")
    public List<Long> getAllLikeStatusByStatusId(@PathVariable Long id){
        List<Long> likeList = statusLikeService.getAllStatusLikedIdByUserId(id);
        return likeList;
    }

    @PostMapping("/api/{userName}/like/{status_id}")
    private ResultResponse likeStatus(@PathVariable String userName, @PathVariable Long status_id){
        ResultResponse resultResponse = new ResultResponse();
        try {
            StatusLike statusLike = new StatusLike();
            Status status = statusSevice.findOne(status_id);
            User user = userService.findByUserName(userName);
            status.setTotalLikes(status.getTotalLikes()+ 1);
            statusSevice.saveStatus(status);
            statusLike.setUser(user);
            statusLike.setStatus(status);
            statusLikeService.save(statusLike);
            resultResponse.setMessage("Ok");
        }catch (Exception e){
            resultResponse.setMessage("Lỗi");
        }
        return resultResponse;
    }

    @DeleteMapping("/api/{userName}/unlike/{status_id}")
    private ResultResponse unlikeStatus2(@PathVariable String userName, @PathVariable Long status_id){
        ResultResponse resultResponse = new ResultResponse();
        try {
            StatusLike statusLike = new StatusLike();
            Status status = statusSevice.findOne(status_id);
            User user = userService.findByUserName(userName);
            status.setTotalLikes(status.getTotalLikes()- 1);
            statusSevice.saveStatus(status);
            statusLike.setUser(user);
            statusLike.setStatus(status);
            statusLikeService.save(statusLike);
            resultResponse.setMessage("Ok");

        }catch (Exception e){
            resultResponse.setMessage("Lỗi");
        }
        return resultResponse;
    }
}
