package com.dictionaryapp.service;

import com.dictionaryapp.model.dto.HomeWordDto;
import com.dictionaryapp.model.dto.WordDto;

public interface WordService {
    boolean save(WordDto wordDto);

    public HomeWordDto getAllWords();

    void delete(long id);

    void deleteAll();
}
