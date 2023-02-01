package com.techelevator.dao;

import com.techelevator.model.meal.Recipe;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class JdbcRecipeDao implements RecipeDao {

    private final JdbcTemplate jdbcTemplate;

    public JdbcRecipeDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public boolean create(Recipe recipe) {
        return false;
    }

    @Override
    public List<Recipe> findAllByUserId(int userId) {
        return null;
    }

    @Override
    public List<Recipe> findAllPublic() {
        return null;
    }

    @Override
    public Recipe getRecipeById(int recipeId) {
        return null;
    }

    @Override
    public boolean update(Recipe recipe) {
        return false;
    }

    @Override
    public boolean delete(int recipeId) {
        return false;
    }
}
