package com.example.shop_fashion.controller;

import com.example.shop_fashion.dto.*;
import com.example.shop_fashion.services.impl.*;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class UserController {
    @Autowired
    public UserServices userServices;

    @GetMapping("/login")
    public String login(){
        return "login";
    }
    @GetMapping("/logout")
    public String logout(Model model,HttpSession session){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            SecurityContextHolder.getContext().setAuthentication(null);
        }
        SecurityContextHolder.clearContext();
        session.removeAttribute("customer");
        session.removeAttribute("roles");
        return "redirect:/login";
    }
    @GetMapping("/register")
    public String register(Model model){
        model.addAttribute("user",new UserDTO());
        return "register";
    }
    @PostMapping("/register")
    public String register(@ModelAttribute UserDTO userDTO){
        userServices.saveUser(userDTO);
        return "login";
    }
    @GetMapping("/blog")
    public String blog(Model model){
        return "blog";
    }
    @GetMapping("/blog_details")
    public String blogDetails(Model model){
        return "blog-details";
    }
    @GetMapping("/contact")
    public String contact(Model model){
        return "contact";
    } @GetMapping("/about_us")
    public String about(Model model){
        return "about";
    }



}
