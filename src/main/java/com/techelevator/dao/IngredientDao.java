package com.techelevator.dao;

import com.techelevator.model.meal.Ingredient;

import java.util.List;

public interface IngredientDao {

    boolean create(String name);
    List<Ingredient> findAll();
    List<Ingredient> findAllByRecipeId(int recipeId);
}