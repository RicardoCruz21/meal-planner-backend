package com.techelevator.dao;

import com.techelevator.model.meal.Recipe;

import java.util.List;

public interface RecipeDao {

    void create(Recipe recipe, int userId);
    List<Recipe> findAllByUserId(int userId);
    List<Recipe> findAllPublic();
    Recipe getRecipeById(int recipeId);
    void update(Recipe recipe);
    boolean delete(int recipeId);
}
