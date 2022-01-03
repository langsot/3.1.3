package com.example.demo.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.example.demo.models.Role;
import com.example.demo.models.User;
import com.example.demo.service.RoleService;
import com.example.demo.service.UserService;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.Set;

@Component
public class TestUser {

    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public TestUser(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @PostConstruct
    public void testUser() {
        roleService.add(new Role("ROLE_ADMIN"));
        roleService.add(new Role("ROLE_USER"));

        Set<Role> forHisenber = new HashSet<>();
        forHisenber.add(roleService.roleByName("ROLE_ADMIN"));
        forHisenber.add(roleService.roleByName("ROLE_USER"));

        User user = new User();
        user.setName("Heisenberg");
        user.setPassword("Scarlet");
        user.setAge(52);
        user.setRoles(forHisenber);
        user.setEmail("imALagend@met.org");
        userService.add(user);

        Set<Role> forJessy = new HashSet<>();
        forJessy.add(roleService.roleByName("ROLE_USER"));

        User user1 = new User();
        user1.setName("Jessy");
        user1.setPassword("dialler777");
        user1.setAge(27);
        user1.setRoles(forJessy);
        user1.setEmail("legalBoy@jc.com");
        userService.add(user1);

    }

}
