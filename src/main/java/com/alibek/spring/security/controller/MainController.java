package com.alibek.spring.security.controller;

import com.alibek.spring.security.entities.User;
import com.alibek.spring.security.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class MainController {

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String homePage(){
        return "home";
    }

    @GetMapping("/authenticated")
    public String pageForAuthenticatedUsers(Principal principal) {
        User user = userService.findByUsername(principal.getName());
        return "secured part of web service " + user.getUsername() + " " + user.getEmail();
    }

    @GetMapping("/read_profile")
    public String pageForReadProfile() {
        return "page for read profile";
    }

    @GetMapping("/only_for_admins")
    public String pageForOnlyForAdmins() {
        return "admins page";
    }
}
