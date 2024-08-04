package com.dictionaryapp.service;

import com.dictionaryapp.model.dto.LoginDto;
import com.dictionaryapp.model.dto.RegisterDto;

public interface UserService {

    public boolean registerUser(RegisterDto registerDto);
    public boolean loginUser(LoginDto loginDto);

    void logout();
}
