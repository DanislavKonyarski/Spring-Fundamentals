package com.bonappetit.service;

import com.bonappetit.model.dto.RecipeAddDto;
import com.bonappetit.model.entity.Recipe;

import java.util.List;

public interface RecipeService {
    void save(RecipeAddDto recipeAddDto);
    List<Recipe> getMainDish();

    List<Recipe> getCocktails();

    List<Recipe> getDesserts();

}
