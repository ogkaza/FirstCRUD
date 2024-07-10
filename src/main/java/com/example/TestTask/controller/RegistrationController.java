package com.example.TestTask.controller;

import com.example.TestTask.entity.UserEntity;
import com.example.TestTask.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegistrationController {
    @Autowired
    UserService userService;

    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/registration")
    public String registration(Model model){
        model.addAttribute("userEntity", new UserEntity());
        return "registration";
    }
    @PostMapping("/registration")
    public String registration(@ModelAttribute("userEntity") UserEntity userEntity, Model model){
        userService.saveUser(userEntity);
        return "redirect:/login";
    }
}
