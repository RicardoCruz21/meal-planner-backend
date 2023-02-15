package com.techelevator.dao;

import com.techelevator.model.meal.Category;

import java.util.List;

public interface CategoryDao {

    int create(String name);
    List<Category> findAll();
    List<Category> findAllByRecipeId(int recipeId);
}
