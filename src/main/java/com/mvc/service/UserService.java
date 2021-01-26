package com.mvc.service;

import com.mvc.model.User;
import com.mvc.model.UserPrincipal;
import com.mvc.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements IUserService {


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

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public void remove(Long id) {
        userRepository.deleteById(id);
    }

    public List<User> searchByName(String name) {
        return userRepository.findByUserNameContaining(name);
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findOneByUserName(username);
        return UserPrincipal.buid(user);
    }

    @Override
    public User findByUserName(String username) {
        return userRepository.findOneByUserName(username);
    }

    public List<User> searchByName(Optional<String> name) {
        return userRepository.findByUserNameContaining(name);
    }
}
