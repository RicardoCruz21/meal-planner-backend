package com.techelevator.dao;

import com.techelevator.model.meal.Ingredient;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class JdbcIngredientDao implements IngredientDao {

    private final JdbcTemplate jdbcTemplate;

    public JdbcIngredientDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int create(String name) {
        return 0;
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
