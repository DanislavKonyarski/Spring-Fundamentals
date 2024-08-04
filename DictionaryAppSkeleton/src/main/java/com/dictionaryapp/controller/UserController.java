package com.dictionaryapp.controller;

import com.dictionaryapp.model.dto.LoginDto;
import com.dictionaryapp.model.dto.RegisterDto;
import com.dictionaryapp.service.UserService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class UserController {

    public final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @ModelAttribute("registerDto")
    public RegisterDto registerDto(){
        return new RegisterDto();
    }

    @ModelAttribute("loginDto")
    public LoginDto loginDto(){
        return new LoginDto();
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @PostMapping("/login")
    public String doLogin(@Valid LoginDto loginDto,
                          BindingResult bindingResult,
                          RedirectAttributes redirectAttributes){

        if (bindingResult.hasErrors()|| !userService.loginUser(loginDto)){
            redirectAttributes.addFlashAttribute("loginDto",loginDto);
            redirectAttributes.addFlashAttribute(
                    "org.springframework.validation.BindingResult.loginDto",bindingResult);
            redirectAttributes.addFlashAttribute("userPassMismatch", true);
            return "redirect:/login";
        }


        return "redirect:/home";
    }

    @GetMapping("/register")
    public String register(){
        return "register";
    }

    @PostMapping("/register")
    public String doRegister(@Valid RegisterDto registerDto,
                             BindingResult bindingResult,
                             RedirectAttributes redirectAttributes){

        if (bindingResult.hasErrors() || !userService.registerUser(registerDto)){
            redirectAttributes.addFlashAttribute("registerDto", registerDto);
            redirectAttributes.addFlashAttribute(
                    "org.springframework.validation.BindingResult.registerDto",bindingResult);

            return "redirect:/register";
        }

        return "redirect:/login";
    }
}
