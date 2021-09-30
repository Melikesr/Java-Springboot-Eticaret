package com.example.Eticaret.Customers;

import com.example.Eticaret.Entity.Role;
import com.example.Eticaret.Entity.User;
import com.example.Eticaret.Entity.UserRepository;
import com.example.Eticaret.Entity.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class CustomerControlller {
    @Autowired
    private UserService service;
    @Autowired
    private UserRepository repo;

    @GetMapping("/list_users")
    public String viewUserList(Model model){

        List<User> listUsers=service.listAll();
        model.addAttribute("listUsers",listUsers);
        return "users";


    }


    @GetMapping("/users/edit/{id}")
    public String editUser(@PathVariable("id") Long id, Model model){

       User user= service.get(id);
       List<Role> listRoles=service.getRoles();
       model.addAttribute("user",user);
        model.addAttribute("listRoles",listRoles);
        return "user_form";
    }

    @PostMapping("/users/save")
    public String saveUser(User user){
        service.save(user);
        return "redirect:/list_users";
    }

}
