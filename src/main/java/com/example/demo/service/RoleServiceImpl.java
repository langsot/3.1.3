package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.dao.RoleDao;
import com.example.demo.models.Role;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {

    private RoleDao roleDao;

    @Autowired
    public RoleServiceImpl(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    @Override
    public List<Role> getAll() {
        return roleDao.getAll();
    }

    @Override
    public void add(Role role) {
        roleDao.add(role);
    }

    @Override
    public void delete(Role role) {
        roleDao.delete(role);
    }

    @Override
    public void edit(Role role) {
        roleDao.edit(role);
    }

    @Override
    public Role roleById(Long id) {
        return roleDao.roleById(id);
    }

    @Override
    public Role roleByName(String name) {
        return roleDao.roleByName(name);
    }

    @Override
    public Set<Role> setOfRole(String[] role) {
        return roleDao.setOfRole(role);
    }
}
