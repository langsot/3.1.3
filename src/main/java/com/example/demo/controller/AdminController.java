package com.example.demo.controller;

import com.example.demo.models.User;
import com.example.demo.service.RoleService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserService service;
    private final RoleService role;

    @Autowired
    public AdminController(UserService userService, RoleService role) {
        this.service = userService;
        this.role = role;
    }

    @GetMapping()
    public String allUser(@AuthenticationPrincipal User user, ModelMap model) {
        model.addAttribute("user", user);
        model.addAttribute("allUser", service.getAll());
        model.addAttribute("allRole", role.getAll());
        return "/admin";
    }

//    @GetMapping(value = "/add")
//    public String newUser(ModelMap model) {
//        model.addAttribute("newUser", new User());
//        model.addAttribute("role", role.getAll());
//        return "/create";
//    }

    @PostMapping("/add")
    public String create(@ModelAttribute("newUser") User user, @RequestParam(value = "role") String[] nameRole) {
        //тут мы должны ловить искл если роли не выбраны (так же и в эдите)
        user.setRoles(role.setOfRole(nameRole));
        service.add(user);
        return "redirect:/admin";
    }

//    @GetMapping(value = "/edit/{id}")
//    public String editUser(@PathVariable("id") Long id, ModelMap model) {
//        model.addAttribute("userEdit", service.userById(id));
//        model.addAttribute("role", role.getAll());
//        return "/edit";
//    }

    @PutMapping("/edit")
    public String refactor(@ModelAttribute User user,
                           @RequestParam(value = "role") String[] nameRole) {
        user.setRoles(role.setOfRole(nameRole));
        service.edit(user);
        return "redirect:/admin";
    }

    @DeleteMapping("/delete")
    public String deleteUser(@ModelAttribute User user) {
        service.delete(user.getId());
        return "redirect:/admin";
    }

}
