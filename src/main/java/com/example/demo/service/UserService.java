package com.example.demo.service;

import com.example.demo.models.User;

import java.util.List;

public interface UserService {
    List<User> getAll();
    void add(User user);
    void delete(Long id);
    void edit(User user);
    User userById(Long id);
    User userByName(String name);
}
