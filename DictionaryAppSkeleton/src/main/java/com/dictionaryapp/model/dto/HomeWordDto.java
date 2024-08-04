package com.dictionaryapp.model.dto;

import com.dictionaryapp.model.entity.Word;

import java.util.ArrayList;
import java.util.List;

public class HomeWordDto {

    public List<Word> germanWords;
    public List<Word> spanishWords;
    public List<Word> frenchWords;
    public List<Word> italianWords;

    public HomeWordDto() {
        this.germanWords = new ArrayList<>();
        this.spanishWords = new ArrayList<>();
        this.frenchWords = new ArrayList<>();
        this.italianWords = new ArrayList<>();
    }

    public List<Word> getGermanWords() {
        return germanWords;
    }

    public void setGermanWords(List<Word> germanWords) {
        this.germanWords = germanWords;
    }

    public List<Word> getSpanishWords() {
        return spanishWords;
    }

    public void setSpanishWords(List<Word> spanishWords) {
        this.spanishWords = spanishWords;
    }

    public List<Word> getFrenchWords() {
        return frenchWords;
    }

    public void setFrenchWords(List<Word> frenchWords) {
        this.frenchWords = frenchWords;
    }

    public List<Word> getItalianWords() {
        return italianWords;
    }

    public void setItalianWords(List<Word> italianWords) {
        this.italianWords = italianWords;
    }


}
