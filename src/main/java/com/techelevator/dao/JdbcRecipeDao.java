package com.techelevator.dao;

import com.techelevator.model.meal.Category;
import com.techelevator.model.meal.Ingredient;
import com.techelevator.model.meal.Recipe;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcRecipeDao implements RecipeDao {

    private final JdbcTemplate jdbcTemplate;
    private final IngredientDao ingredientDao;
    private final CategoryDao categoryDao;

    public JdbcRecipeDao(JdbcTemplate jdbcTemplate, IngredientDao ingredientDao, CategoryDao categoryDao) {
        this.jdbcTemplate = jdbcTemplate;
        this.ingredientDao = ingredientDao;
        this.categoryDao = categoryDao;
    }

    @Override
    public void create(Recipe recipe, int userId) {

        // Insert recipe into recipe table return the new recipe id
        String sql = "INSERT INTO recipe (recipe_name, instructions, is_sharable) VALUES (?, ?, ?) RETURNING recipe_id;";
        int recipeId = jdbcTemplate.queryForObject(sql, int.class, recipe.getName(), recipe.getInstructions(), recipe.isSharable());

        // Insert recipeId and userId into the users_recipe table
        String sqlForUserRecipe = "INSERT INTO users_recipe (user_id, recipe_id) VALUES (?, ?);";
        jdbcTemplate.update(sqlForUserRecipe, userId, recipeId);

        // Insert each ingredient in recipe into recipe_ingredient table
        for (Ingredient ingredient : recipe.getIngredientList()) {
            String sqlForIngredients = "INSERT INTO recipe_ingredient (recipe_id, ingredient_id, quantity, unit_of_measure) VALUES (?, ?, ?, ?);";
            jdbcTemplate.update(sqlForIngredients, recipeId, ingredient.getId(), ingredient.getQuantity(), ingredient.getUnitOfMeasure());
        }

        // Insert each category in recipe into recipe_category table
        for (Category category : recipe.getCategoryList()) {
            String sqlForCategories = "INSERT INTO recipe_category (recipe_id, category_id) VALUES (?, ?);";
            jdbcTemplate.update(sqlForCategories, recipeId, category.getId());
        }
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

        recipe.setIngredientList(ingredientDao.findAllByRecipeId(recipeId));
        recipe.setCategoryList(categoryDao.findAllByRecipeId(recipeId));

        return recipe;
    }

    @Override
    public void update(Recipe recipe) {

        // Update recipe data in recipe table
        String sql = "UPDATE recipe SET recipe_name = ?, instructions = ?, is_sharable = ? WHERE recipe_id = ?;";
        jdbcTemplate.update(sql, recipe.getName(), recipe.getInstructions(), recipe.isSharable(), recipe.getId());

        // Update ingredients in recipe_ingredient table.
        for (Ingredient ingredient : recipe.getIngredientList()) {
            String sqlForIngredients = "UPDATE recipe_ingredient SET quantity = ?, unit_of_measure = ? WHERE recipe_id = ? AND ingredient_id = ?;";
            jdbcTemplate.update(sqlForIngredients, ingredient.getQuantity(), ingredient.getUnitOfMeasure(), recipe.getId(), ingredient.getId());
        }

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

}
