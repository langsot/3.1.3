package com.example.demo.service;

import com.example.demo.models.Role;

import java.util.List;

public interface RoleService {
    List<Role> getAll();
    void add(Role role);
    void delete(Role role);
    void edit(Role role);
    Role roleByName(String name);
    List<Role> listOfRole();
}
