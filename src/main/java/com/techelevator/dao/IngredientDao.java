package com.techelevator.dao;

import com.techelevator.model.meal.Ingredient;
import com.techelevator.model.mealDto.IngredientDto;

import java.util.List;

public interface IngredientDao {

    int create(String name);
    List<IngredientDto> findAll();
    List<Ingredient> findAllByRecipeId(int recipeId);
}
