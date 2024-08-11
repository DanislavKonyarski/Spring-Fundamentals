package com.bonappetit.service;

import com.bonappetit.config.LoggedUser;
import com.bonappetit.model.dto.RecipeAddDto;
import com.bonappetit.model.entity.Category;
import com.bonappetit.model.entity.Recipe;
import com.bonappetit.model.entity.User;
import com.bonappetit.model.enums.CategoryName;
import com.bonappetit.repo.CategoryRepository;
import com.bonappetit.repo.RecipeRepository;
import com.bonappetit.repo.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RecipeServiceImpl implements RecipeService {

    public RecipeRepository recipeRepository;
    public CategoryRepository categoryRepository;
    public UserRepository userRepository;
    public LoggedUser loggedUser;

    public RecipeServiceImpl(RecipeRepository recipeRepository,
                             CategoryRepository categoryRepository,
                             UserRepository userRepository,
                             LoggedUser loggedUser) {
        this.recipeRepository = recipeRepository;
        this.categoryRepository = categoryRepository;
        this.userRepository = userRepository;
        this.loggedUser = loggedUser;
    }

    @Override
    public void save(RecipeAddDto recipeAddDto) {

        Recipe recipe = new Recipe();
        recipe.setName(recipeAddDto.getName());
        recipe.setIngredients(recipeAddDto.getIngredients());
        recipe.setCategory(categoryRepository.getByCategoryName(recipeAddDto.categoryName));
        recipe.setAddedBy(userRepository.getById(loggedUser.getId()));

        recipeRepository.save(recipe);
    }

    @Override
    public List<Recipe> getMainDish() {
        return recipeRepository.findAllByCategory_CategoryName(CategoryName.MAIN_DISH);
    }

    @Override
    public List<Recipe> getCocktails() {
        return recipeRepository.findAllByCategory_CategoryName(CategoryName.COCKTAIL);
    }

    @Override
    public List<Recipe> getDesserts() {
        return recipeRepository.findAllByCategory_CategoryName(CategoryName.DESSERT);
    }

}
