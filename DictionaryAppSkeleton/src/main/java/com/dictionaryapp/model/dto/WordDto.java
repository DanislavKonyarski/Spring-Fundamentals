package com.dictionaryapp.model.dto;

import com.dictionaryapp.model.entity.Language;
import com.dictionaryapp.model.enums.LanguageName;
import jakarta.validation.constraints.*;

import java.time.LocalDate;

public class WordDto {


    @NotBlank
    @Size(min = 2, max = 40)
    public String term;
    @NotBlank
    @Size(min = 2, max = 80)
    public String translation;
    @Size(min = 2, max = 200)
    public String example;
    @PastOrPresent
    @NotNull
      public LocalDate inputDate;
    @NotNull
    public LanguageName language;

    public WordDto() {
    }


    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public String getTranslation() {
        return translation;
    }

    public void setTranslation(String translation) {
        this.translation = translation;
    }

    public String getExample() {
        return example;
    }

    public void setExample(String example) {
        this.example = example;
    }

    public LocalDate getInputDate() {
        return inputDate;
    }

    public void setInputDate(LocalDate inputDate) {
        this.inputDate = inputDate;
    }

    public LanguageName getLanguage() {
        return language;
    }

    public void setLanguage(LanguageName language) {
        this.language = language;
    }
}
