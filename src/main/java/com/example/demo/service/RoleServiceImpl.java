package com.example.demo.service;

import com.example.demo.dao.RoleRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.models.Role;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {

    private final RoleRepositories roleRepositories;

    @Autowired
    public RoleServiceImpl(RoleRepositories roleRepositories) {
        this.roleRepositories = roleRepositories;
    }

    @Override
    public List<Role> getAll() {
        return roleRepositories.findAll();
    }

    @Override
    public void add(Role role) {
        roleRepositories.save(role);
    }

    @Override
    public void delete(Role role) {
        roleRepositories.delete(role);
    }

    @Override
    public void edit(Role role) {
        roleRepositories.save(role);
    }



    @Override
    public Role roleByName(String name) {
        return roleRepositories.findByName(name);
    }

    @Override
    public List<Role> listOfRole() {
        return roleRepositories.findAll();
    }
}
