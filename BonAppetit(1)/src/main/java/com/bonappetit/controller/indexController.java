package com.bonappetit.controller;

import com.bonappetit.config.LoggedUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class indexController {

    public LoggedUser loggedUser;

    public indexController(LoggedUser loggedUser) {
        this.loggedUser = loggedUser;
    }

    @GetMapping("/")
    public String index(){
        if(loggedUser.isLogin()){
            return "redirect:/home";
        }
        return "/index";
    }
}
