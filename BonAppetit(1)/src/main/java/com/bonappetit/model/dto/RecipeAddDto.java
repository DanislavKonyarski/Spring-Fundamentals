package com.bonappetit.model.dto;

import com.bonappetit.model.enums.CategoryName;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class RecipeAddDto {

    @NotBlank
    @Size(min = 2,max = 40)
    public String name;

    @NotBlank
    @Size(min = 2, max = 150)
    public String ingredients;

    @NotNull
    public CategoryName categoryName;

    public RecipeAddDto() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public CategoryName getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(CategoryName categoryName) {
        this.categoryName = categoryName;
    }
}
