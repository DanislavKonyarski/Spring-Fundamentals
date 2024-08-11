package com.bonappetit.controller;

import com.bonappetit.model.dto.LoginDto;
import com.bonappetit.service.UserService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class LoginController {

    public UserService userService;

    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @ModelAttribute("loginDto")
    public LoginDto loginDto(){
        return new LoginDto();
    }

    @GetMapping("/login")
    public String login(){
        return "/login";
    }

    @PostMapping("/login")
    public String doLogin(@Valid LoginDto loginDto,
                          BindingResult bindingResult,
                          RedirectAttributes redirectAttributes){

        if (bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("loginDto",loginDto);
            redirectAttributes.addFlashAttribute(
                    "org.springframework.validation.BindingResult.loginDto", bindingResult);
            return "redirect:/login";
        }

        if(!userService.login(loginDto)){
            redirectAttributes.addFlashAttribute("userPassMismatch", true);
            return "redirect:/login";
        }

        return "redirect:/home";
    }
}
