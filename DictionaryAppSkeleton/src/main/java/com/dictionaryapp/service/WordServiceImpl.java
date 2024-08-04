package com.dictionaryapp.service;

import com.dictionaryapp.config.LoggedUser;
import com.dictionaryapp.model.dto.HomeWordDto;
import com.dictionaryapp.model.dto.WordDto;
import com.dictionaryapp.model.entity.Language;
import com.dictionaryapp.model.entity.User;
import com.dictionaryapp.model.entity.Word;
import com.dictionaryapp.model.enums.LanguageName;
import com.dictionaryapp.repo.LanguageRepository;
import com.dictionaryapp.repo.UserRepository;
import com.dictionaryapp.repo.WordRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class WordServiceImpl implements WordService {

    public WordRepository wordRepository;
    public UserRepository userRepository;
    public LoggedUser loggedUser;
    public LanguageRepository languageRepository;

    public WordServiceImpl(WordRepository wordRepository,
                           UserRepository userRepository,
                           LoggedUser loggedUser,
                           LanguageRepository languageRepository) {
        this.wordRepository = wordRepository;
        this.userRepository = userRepository;
        this.loggedUser = loggedUser;
        this.languageRepository = languageRepository;
    }




    @Override
    public boolean save(WordDto wordDto) {
        User user = userRepository.getById(loggedUser.getId());
        Language language = languageRepository.getByName(wordDto.getLanguage());

        if (language==null){
            return false;
        }

        Word word = new Word();
        word.setTerm(wordDto.getTerm());
        word.setTranslation(wordDto.getTranslation());
        word.setExample(wordDto.getExample());
        word.setInputDate(wordDto.getInputDate());
        word.setLanguage(language);
        word.setAddedBy(user);

        wordRepository.save(word);



        return true;
    }

    @Override
    public HomeWordDto getAllWords() {
        HomeWordDto homeWordDto = new HomeWordDto();


        homeWordDto.setGermanWords(wordRepository.findAllByLanguageName(LanguageName.GERMAN));

        homeWordDto.setFrenchWords(wordRepository.findAllByLanguageName(LanguageName.FRENCH));

        homeWordDto.setSpanishWords(wordRepository.findAllByLanguageName(LanguageName.SPANISH));

        homeWordDto.setItalianWords(wordRepository.findAllByLanguageName(LanguageName.ITALIAN));

        return homeWordDto;
    }

    @Override
    public void delete(long id) {
        wordRepository.deleteById(id);
    }

    @Override
    public void deleteAll() {
        wordRepository.deleteAll();
    }
}