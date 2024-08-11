package com.bonappetit.controller;

import com.bonappetit.config.LoggedUser;
import com.bonappetit.model.dto.RecipeAddDto;
import com.bonappetit.service.RecipeService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class RecipeAddController {

    public LoggedUser loggedUser;
    public RecipeService recipeService;

    public RecipeAddController(LoggedUser loggedUser, RecipeService recipeService) {
        this.loggedUser = loggedUser;
        this.recipeService = recipeService;
    }

    @ModelAttribute("recipeAddDto")
    public RecipeAddDto recipeAddDto(){
        return new RecipeAddDto();
    }

    @GetMapping("/recipe-add")
    public String recipeAdd(){
        if (loggedUser.isLogin()){
            return "/recipe-add";
        }
        return "redirect:/";
    }

    @PostMapping("/recipe-add")
    public String doRecipeAdd(@Valid RecipeAddDto recipeAddDto,
                              BindingResult bindingResult,
                              RedirectAttributes redirectAttributes){

        if (bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("recipeAddDto",recipeAddDto);
            redirectAttributes.addFlashAttribute(
                    "org.springframework.validation.BindingResult.recipeAddDto",bindingResult);

            return "redirect:/recipe-add";
        }


        recipeService.save(recipeAddDto);

        return "redirect:/home";
    }
}
