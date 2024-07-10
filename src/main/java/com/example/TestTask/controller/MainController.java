package com.example.TestTask.controller;

import com.example.TestTask.entity.UserEntity;
import com.example.TestTask.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
public class MainController {
    @Autowired
    UserService userService;

    public MainController(UserService userService) {
        this.userService = userService;
    }
    @GetMapping("/")
    public String main(Model model){
        model.addAttribute("users", userService.getAll());
        return "main";
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable(name = "id") Long id) {
        userService.deleteUser(id);
        return "redirect:/";
    }

    @GetMapping("/edit/{id}")
    public String editUser(@PathVariable(name = "id") Long id, Model model){
        model.addAttribute("userEntity", userService.findById(id));
        return "edit";
    }

    @PostMapping("/edit")
    public String updateUser(@RequestParam("id") Long id, UserEntity userEntity) {
        userService.updateUser(id, userEntity);
        return "redirect:/";
    }
}
