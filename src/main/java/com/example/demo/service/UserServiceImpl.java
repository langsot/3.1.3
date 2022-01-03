package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.example.demo.dao.UserRepositories;
import com.example.demo.models.User;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepositories userRepositories;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepositories userRepositories, PasswordEncoder passwordEncoder) {
        this.userRepositories = userRepositories;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public List<User> getAll() {
        return userRepositories.findAll();
    }

    @Override
    public void add(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepositories.save(user);
    }

    @Override
    public void delete(Long id) {
        userRepositories.deleteById(id);
    }

    @Override
    public void edit(User user) {
        if (user.getPassword() != null) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        }
        userRepositories.save(user);
    }

    @Override
    public User userById(Long id) {
        return userRepositories.getUserById(id);
    }

    @Override
    public User userByName(String name) {
        return userRepositories.getUserByName(name);
    }
}
