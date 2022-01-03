package com.example.demo.dao;

import com.example.demo.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepositories extends JpaRepository<Role, Long> {
    Role findByName(String name);
}
