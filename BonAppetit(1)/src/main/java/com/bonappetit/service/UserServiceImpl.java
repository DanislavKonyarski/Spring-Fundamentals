package com.bonappetit.service;

import com.bonappetit.config.LoggedUser;
import com.bonappetit.model.dto.LoginDto;
import com.bonappetit.model.dto.RegisterDto;
import com.bonappetit.model.entity.Recipe;
import com.bonappetit.model.entity.User;
import com.bonappetit.repo.RecipeRepository;
import com.bonappetit.repo.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    public UserRepository userRepository;
    public LoggedUser loggedUser;
    public RecipeRepository recipeRepository;

    public UserServiceImpl(UserRepository userRepository, LoggedUser loggedUser, RecipeRepository recipeRepository) {
        this.userRepository = userRepository;
        this.loggedUser = loggedUser;
        this.recipeRepository = recipeRepository;
    }

    @Override
    public boolean register(RegisterDto registerDto) {

        if (!registerDto.getPassword().equals(registerDto.getConfirmPassword())){
            return false;
        }


        if (userRepository.findUserByEmail(registerDto.getEmail()).isPresent()||
        userRepository.findUserByUsername(registerDto.getUsername()).isPresent()){
            return false;
        }


        User user = new User();
        user.setUsername(registerDto.getUsername());
        user.setPassword(registerDto.getPassword());
        user.setEmail(registerDto.getEmail());
        userRepository.save(user);
        return true;
    }

    @Override
    public boolean login(LoginDto loginDto) {

        Optional<User> user = userRepository.findUserByUsernameAndPassword(
                loginDto.getUsername(), loginDto.password);

        if (user.isPresent()){
            loggedUser.login(user.get());
            return true;
        }

        return false;
    }

    @Override
    public void addFavorite(long id) {
        Optional<User> user = userRepository.findById(loggedUser.getId());
        if (!user.isPresent()){
            return;
        }

        Recipe recipe = recipeRepository.findById(id).orElseThrow();
        for (int i = 0; i < user.get().getFavouriteRecipes().size(); i++) {
            if (user.get().getFavouriteRecipes().get(i) == recipe){
                return;
            }
        }
        user.get().getFavouriteRecipes().add(recipe);
        userRepository.save(user.get());
    }

    @Override
    public List<Recipe> getFavorite() {
        return  userRepository.findById(loggedUser.getId()).orElseThrow().getFavouriteRecipes();
    }
}
