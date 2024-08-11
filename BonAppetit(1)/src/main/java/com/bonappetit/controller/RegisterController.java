package com.bonappetit.controller;

import com.bonappetit.model.dto.RegisterDto;
import com.bonappetit.service.UserService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class RegisterController {


    public UserService userService;

    public RegisterController(UserService userService) {
        this.userService = userService;
    }

    @ModelAttribute("registerDto")
    public RegisterDto registerDto(){
        return new RegisterDto();
    }

    @GetMapping("/register")
    public String register(){
        return "/register";
    }

    @PostMapping("/register")
    public String doRegister(@Valid RegisterDto registerDto,
                             BindingResult bindingResult,
                             RedirectAttributes redirectAttributes
                             ){

        if (bindingResult.hasErrors()||!userService.register(registerDto)){
            redirectAttributes.addFlashAttribute("registerDto", registerDto);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.registerDto",
                    bindingResult);
            return "redirect:/register";
        }


        return "redirect:/login";
    }
}
