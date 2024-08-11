package com.bonappetit.controller;

import com.bonappetit.config.LoggedUser;
import com.bonappetit.model.entity.Recipe;
import com.bonappetit.repo.RecipeRepository;
import com.bonappetit.service.RecipeService;
import com.bonappetit.service.UserService;
import com.bonappetit.service.UserServiceImpl;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.beans.Transient;

@Controller
public class HomeController {

    public LoggedUser loggedUser;
    public RecipeService recipeService;
    public UserService userService;

    public HomeController(LoggedUser loggedUser, RecipeService recipeService, UserService userService) {
        this.loggedUser = loggedUser;
        this.recipeService = recipeService;
        this.userService = userService;
    }


    @GetMapping("home")
    public String home(Model model){

        if(!loggedUser.isLogin()){
            return "redirect:/";
        }
        model.addAttribute("mainDishRecipes", recipeService.getMainDish());
        model.addAttribute("cocktails", recipeService.getCocktails());
        model.addAttribute("desserts", recipeService.getDesserts());
        model.addAttribute("favorite", userService.getFavorite());

        return "/home";
    }

    @GetMapping("/logout")
    public String logout(){
        loggedUser.logout();
        return "redirect:/";
    }

    @Transactional
    @PostMapping("/add-favorite/{id}")
    public String addFavorite(@PathVariable long id){

        if(!loggedUser.isLogin()){
            return "redirect:/";
        }

        userService.addFavorite(id);

        return "redirect:/home";
    }
}
