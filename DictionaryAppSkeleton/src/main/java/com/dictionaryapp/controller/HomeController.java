package com.dictionaryapp.controller;

import com.dictionaryapp.config.LoggedUser;
import com.dictionaryapp.model.dto.HomeWordDto;
import com.dictionaryapp.service.UserService;
import com.dictionaryapp.service.WordService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@Controller
public class HomeController {

    public UserService userService;
    public LoggedUser loggedUser;
    public WordService wordService;

    public HomeController(UserService userService,
                          LoggedUser loggedUser,
                          WordService wordService) {
        this.userService = userService;
        this.loggedUser = loggedUser;
        this.wordService = wordService;
    }

    @ModelAttribute("HomeWordDto")
    public HomeWordDto homeWordDto(){
        return new HomeWordDto();
    }

    @GetMapping("/home")
    public String homeController( Model model){
        if(!loggedUser.isLogin()){
            return "redirect:/";
        }
        HomeWordDto homeWordDto = wordService.getAllWords();
        model.addAttribute("HomeWordDto", homeWordDto);

        return "/home";
    }

    @PostMapping("/logout")
    public String logout(){
        userService.logout();
        return "redirect:/";
    }

    @DeleteMapping("/word/{id}")
    public String deleteWord(@PathVariable long id){

        wordService.delete(id);

        return "redirect:/home";
    }

    @DeleteMapping("/word/deleteAll")
    public String deleteAll(){

        wordService.deleteAll();

        return "redirect:/home";
    }
}
