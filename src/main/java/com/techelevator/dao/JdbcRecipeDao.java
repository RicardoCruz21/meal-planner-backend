package com.techelevator.dao;

import com.techelevator.model.meal.Category;
import com.techelevator.model.meal.Ingredient;
import com.techelevator.model.meal.Recipe;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
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
        List<Recipe> recipes = new ArrayList<>();
        String sql = "SELECT recipe_id FROM users_recipe WHERE user_id = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, userId);
        while (results.next()) {
            recipes.add(getRecipeById(results.getInt("recipe_id")));
        }
        return recipes;
    }

    @Override
    public List<Recipe> findAllPublic() {
        List<Recipe> recipes = new ArrayList<>();
        String sql = "SELECT recipe_id FROM recipe WHERE is_sharable = true;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        while (results.next()) {
            recipes.add(getRecipeById(results.getInt("recipe_id")));
        }
        return recipes;
    }

    @Override
    public Recipe getRecipeById(int recipeId) {
        Recipe recipe = null;

        String sql = "SELECT recipe_id, recipe_name, instructions, is_sharable " +
                     "FROM recipe " +
                     "WHERE recipe_id = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, recipeId);
        if (results.next()) {
            recipe = mapRowToRecipe(results);
        }

        String sqlForIngredients = "SELECT i.ingredient_id, i.ingredient_name, ri.quantity, ri.unit_of_measure " +
                                   "FROM ingredient AS i " +
                                   "JOIN recipe_ingredient AS ri ON i.ingredient_id = ri.ingredient_id " +
                                   "WHERE ri.recipe_id = ?;";
        SqlRowSet ingredients = jdbcTemplate.queryForRowSet(sqlForIngredients, recipeId);
        while (ingredients.next()) {
            recipe.getIngredientList().add(mapRowToIngredient(ingredients));
        }

        String sqlForCategories = "SELECT c.category_id, c.category_name " +
                "FROM category AS c " +
                "JOIN recipe_category AS rc ON c.category_id = rc.category_id " +
                "WHERE rc.recipe_id = ?;";
        SqlRowSet categories = jdbcTemplate.queryForRowSet(sqlForCategories, recipeId);
        if (categories.next()) {
            recipe.getCategoryList().add(mapRowToCategory(categories));
        }

        return recipe;
    }

    @Override
    public boolean update(Recipe recipe) {
        return false;
    }

    @Override
    public boolean delete(int recipeId) {
        return false;
    }

    private Recipe mapRowToRecipe(SqlRowSet rowSet) {
        Recipe recipe = new Recipe();
        recipe.setId(rowSet.getInt("recipe_id"));
        recipe.setName(rowSet.getString("recipe_name"));
        recipe.setInstructions(rowSet.getString("instructions"));
        recipe.setSharable(rowSet.getBoolean("is_sharable"));
        return recipe;
    }

    private Ingredient mapRowToIngredient(SqlRowSet rowSet) {
        Ingredient ingredient = new Ingredient();
        ingredient.setId(rowSet.getInt("ingredient_id"));
        ingredient.setName(rowSet.getString("ingredient_name"));
        ingredient.setQuantity(rowSet.getDouble("quantity"));
        ingredient.setUnitOfMeasure(rowSet.getString("unit_of_measure"));
        return ingredient;
    }

    private Category mapRowToCategory(SqlRowSet rowSet) {
        Category category = new Category();
        category.setId(rowSet.getInt("category_id"));
        category.setName(rowSet.getString("category_name"));
        return category;
    }
}
