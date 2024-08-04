package com.dictionaryapp.service;

import com.dictionaryapp.config.LoggedUser;
import com.dictionaryapp.model.dto.LoginDto;
import com.dictionaryapp.model.dto.RegisterDto;
import com.dictionaryapp.model.entity.User;
import com.dictionaryapp.repo.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    public UserRepository userRepository;
    public LoggedUser loggedUser;

    public UserServiceImpl(UserRepository userRepository,
                           LoggedUser loggedUser) {
        this.userRepository = userRepository;
        this.loggedUser = loggedUser;
    }

    @Override
    public boolean registerUser(RegisterDto registerDto) {

        if (userRepository.existsByUsernameOrEmail(registerDto.getUsername(),registerDto.getEmail()) ||
        !registerDto.getPassword().equals(registerDto.confirmPassword)){
            return false;
        }
        User user = new User();
        user.setUsername(registerDto.getUsername());
        user.setPassword(registerDto.getPassword());
        user.setEmail(registerDto.getEmail());


        userRepository.save(user);

        return true ;
    }

    @Override
    public boolean loginUser(LoginDto loginDto) {
        Optional<User> user = userRepository.findUserByUsernameAndPassword(loginDto.getUsername(),loginDto.getPassword());

        if (user.isPresent()){
            User userForLogin = user.get();
            loggedUser.login(userForLogin);
            return true;
        }
        return false;
    }

    @Override
    public void logout() {
        loggedUser.logout();
    }
}
