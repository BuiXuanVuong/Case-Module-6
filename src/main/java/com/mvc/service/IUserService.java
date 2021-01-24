package com.mvc.service;

import com.mvc.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Optional;

public interface IUserService extends IGeneralService<User>, UserDetailsService {
    User findByUserName(String username);
    User save(User user);



}
