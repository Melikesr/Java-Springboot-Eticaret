package com.example.Eticaret;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AppController {
    @GetMapping("")

    public String viewHomePage(){
        return "index";
    }

    @GetMapping("/iletisim")

    public String iletisimPage(){
        return "iletisim";
    }

    @GetMapping("/admin")
    public String viewAdminPage(){
        return "admin";
    }


}
