package com.example.demo.dao;

import com.example.demo.models.Role;

import java.util.List;
import java.util.Set;

public interface RoleDao {
    List<Role> getAll();
    void add(Role role);
    void delete(Role role);
    void edit(Role role);
    Role roleById(Long id);
    Role roleByName(String name);
    Set<Role> setOfRole(String[] role);
}
