package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.example.demo.dao.UserDao;
import com.example.demo.models.User;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserDao userDao;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserDao userDao, @Lazy PasswordEncoder passwordEncoder) {
        this.userDao = userDao;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public List<User> getAll() {
        return userDao.getAll();
    }

    @Override
    public void add(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userDao.add(user);
    }

    @Override
    public void delete(Long id) {
        userDao.delete(id);
    }

//
//    Равзе это не костыли? Есть ли способ лучше?
//
    @Override
    @Transactional
    public void edit(User user) {
        String oldPass = userById(user.getId()).getPassword();
        if (passwordEncoder.matches(user.getPassword(), passwordEncoder.encode(oldPass))) {
            user.setPassword(oldPass);
        } else {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        }
        userDao.edit(user);
    }

    @Override
    public User userById(Long id) {
        return userDao.userById(id);
    }

    @Override
    public User userByName(String name) {
        return userDao.userByName(name);
    }
}
