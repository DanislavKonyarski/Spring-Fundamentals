package com.bonappetit.service;

import com.bonappetit.model.dto.LoginDto;
import com.bonappetit.model.dto.RegisterDto;
import com.bonappetit.model.entity.Recipe;

import java.util.List;

public interface UserService {
    boolean register(RegisterDto registerDto);
    boolean login(LoginDto loginDto);

    void addFavorite(long id);

    List<Recipe> getFavorite();
}
