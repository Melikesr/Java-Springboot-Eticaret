package com.example.Eticaret.Entity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {
    @Autowired
    private UserService service;

    @Autowired
    private UserRepository repo;

    @GetMapping("/register")
    public String showSingUpFrom(Model model){
        model.addAttribute("user",new User());

        return "Signup_form";
    }
    @PostMapping("/process_register")
    public String prosessRegistration(User user){
        service.saveUserWithDefaultRole(user);
        return "register_success";

    }

}
