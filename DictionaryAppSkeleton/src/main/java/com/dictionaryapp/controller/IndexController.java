package com.dictionaryapp.controller;

import com.dictionaryapp.config.LoggedUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    public LoggedUser loggedUser;

    public IndexController(LoggedUser loggedUser) {
        this.loggedUser = loggedUser;
    }

    @GetMapping("/")
    public String index(){
        if (loggedUser.isLogin()){
            return "redirect:/home";
        }
        return "index";
    }
}
