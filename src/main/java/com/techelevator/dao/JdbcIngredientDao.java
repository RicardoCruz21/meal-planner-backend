package com.techelevator.dao;

import com.techelevator.model.meal.Ingredient;
import com.techelevator.model.mealDto.IngredientDto;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcIngredientDao implements IngredientDao {

    private final JdbcTemplate jdbcTemplate;

    public JdbcIngredientDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int create(String name) {
        String sql = "INSERT INTO ingredient (ingredient_name) VALUES (?) RETURNING ingredient_id;";
        int ingredientId = jdbcTemplate.queryForObject(sql, int.class, name);
        return ingredientId;
    }

    @Override
    public List<IngredientDto> findAll() {
        List<IngredientDto> ingredients = new ArrayList<>();
        String sql = "SELECT * FROM ingredient ORDER BY ingredient_name;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        while(results.next()) {
            ingredients.add(mapRowToIngredientDto(results));
        }
        return ingredients;
    }

    @Override
    public List<Ingredient> findAllByRecipeId(int recipeId) {
        List<Ingredient> ingredients = new ArrayList<>();
        String sql = "SELECT i.ingredient_id, i.ingredient_name, ri.quantity, ri.unit_of_measure " +
                "FROM ingredient AS i " +
                "JOIN recipe_ingredient AS ri ON i.ingredient_id = ri.ingredient_id " +
                "WHERE ri.recipe_id = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, recipeId);
        while(results.next()) {
            ingredients.add(mapRowToIngredient(results));
        }
        return ingredients;
    }

    private Ingredient mapRowToIngredient(SqlRowSet rowSet) {
        Ingredient ingredient = new Ingredient();
        ingredient.setId(rowSet.getInt("ingredient_id"));
        ingredient.setName(rowSet.getString("ingredient_name"));
        ingredient.setQuantity(rowSet.getDouble("quantity"));
        ingredient.setUnitOfMeasure(rowSet.getString("unit_of_measure"));
        return ingredient;
    }

    private IngredientDto mapRowToIngredientDto(SqlRowSet rowSet) {
        IngredientDto ingredient = new IngredientDto();
        ingredient.setId(rowSet.getInt("ingredient_id"));
        ingredient.setName(rowSet.getString("ingredient_name"));
        return ingredient;
    }
}
