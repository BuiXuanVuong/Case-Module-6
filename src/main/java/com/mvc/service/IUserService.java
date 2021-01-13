package com.mvc.service;

import com.mvc.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface IUserService extends UserDetailsService {
    public List<User> getAll();
    public User saveUser(User user);
}
