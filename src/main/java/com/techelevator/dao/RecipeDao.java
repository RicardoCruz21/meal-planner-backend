package com.techelevator.dao;

import com.techelevator.model.meal.Recipe;

import java.util.List;

public interface RecipeDao {

    boolean create(Recipe recipe);
    List<Recipe> findAllByUserId(int userId);
    List<Recipe> findAllPublic();
    Recipe getRecipeById(int recipeId);
    boolean update(Recipe recipe);
    boolean delete(int recipeId);
}
