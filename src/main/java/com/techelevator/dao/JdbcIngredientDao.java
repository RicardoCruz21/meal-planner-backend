package com.techelevator.dao;

import com.techelevator.model.meal.Ingredient;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class JdbcIngredientDao implements IngredientDao {

    private final JdbcTemplate jdbcTemplate;

    public JdbcIngredientDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public boolean create(String name) {
        return false;
    }

    @Override
    public List<Ingredient> findAll() {
        return null;
    }

    @Override
    public List<Ingredient> findAllByRecipeId(int recipeId) {
        return null;
    }
}
