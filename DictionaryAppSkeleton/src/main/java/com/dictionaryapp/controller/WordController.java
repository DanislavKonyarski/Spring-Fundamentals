package com.dictionaryapp.controller;

import com.dictionaryapp.config.LoggedUser;
import com.dictionaryapp.model.dto.WordDto;
import com.dictionaryapp.service.WordService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class WordController {

    public WordService wordService;
    public LoggedUser loggedUser;

    public WordController(WordService wordService, LoggedUser loggedUser) {
        this.wordService = wordService;
        this.loggedUser = loggedUser;
    }

    @ModelAttribute("wordDto")
    public WordDto wordDto(){
        return new WordDto();
    }

    @GetMapping("/word-add")
    public String wordAdd(){
        if (loggedUser.isLogin()){
        return "/word-add";
        }
        return "redirect:/";
    }

    @PostMapping("/word-add")
    public String doWordAdd(@Valid WordDto wordDto,
                            BindingResult bindingResult,
                            RedirectAttributes redirectAttributes){

        if (bindingResult.hasErrors()||!wordService.save(wordDto)){
            redirectAttributes.addFlashAttribute("wordDto", wordDto);
            redirectAttributes.addFlashAttribute(
                    "org.springframework.validation.BindingResult.wordDto", bindingResult);
            return "redirect:/word-add";
        }


        return "redirect:/home";
    }
}
