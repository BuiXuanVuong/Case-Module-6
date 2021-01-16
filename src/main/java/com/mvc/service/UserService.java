package com.mvc.service;

import com.mvc.model.User;
import com.mvc.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User getUser(long id) {
        return userRepository.findOneById(id);
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public void deleteUser(long id) {
        userRepository.deleteById(id);
    }

    public User findOneById(long id) {
        return userRepository.findOneById(id);
    }

    public User findByName(String name) {
        return userRepository.findOneByUserName(name);
    }

    public List<User> searchByName(String name) {
        return userRepository.findByUserNameContaining(name);
    }


}
