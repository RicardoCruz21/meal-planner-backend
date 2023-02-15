package com.techelevator.controller;

import com.techelevator.dao.RecipeDao;
import com.techelevator.dao.UserDao;
import com.techelevator.model.meal.Category;
import com.techelevator.model.meal.Ingredient;
import com.techelevator.model.meal.Recipe;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
public class RecipeController {

    private RecipeDao recipeDao;
    private UserDao userDao;

    public RecipeController(RecipeDao recipeDao, UserDao userDao) {
        this.recipeDao = recipeDao;
        this.userDao = userDao;
    }

    @GetMapping("/recipes")
    public List<Recipe> getRecipes(@RequestParam(name = "user_id", defaultValue = "") String userId) {
        if (userId.equals("")) {
            return recipeDao.findAllPublic();
        } else {
            return recipeDao.findAllByUserId(Integer.parseInt(userId));
        }
    }

    @GetMapping("/recipes/{id}")
    public Recipe getRecipe(@PathVariable int id) {
        return recipeDao.getRecipeById(id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/recipes")
    public void createRecipe(@RequestBody Recipe recipe, Principal principal) {
        int userId = userDao.findIdByUsername(principal.getName());
        recipeDao.create(recipe, userId);
    }

    @PutMapping("/recipes/{id}")
    public void updateRecipe(@PathVariable int id, @RequestBody Recipe recipe) {
        recipeDao.update(recipe);
    }

    @DeleteMapping("/recipes/{id}")
    public void deleteRecipe(@PathVariable int id) {
        recipeDao.delete(id);
    }

    @PostMapping("/recipes/{recipe_id}/ingredients/{ingredient_id}")
    public void addRecipeIngredient(@PathVariable(name = "recipe_id") int recipeId, @PathVariable(name = "ingredient_id") int ingredientId, @RequestBody Ingredient ingredient) {}

    @PostMapping("/recipes/{recipe_id}/categories/{category_id}")
    public void addRecipeCategory(@PathVariable(name = "recipe_id") int recipeId, @PathVariable(name = "category_id") int categoryId, @RequestBody Category category) {}

    @DeleteMapping("/recipes/{recipe_id}/ingredients/{ingredient_id}")
    public void deleteRecipeIngredient(@PathVariable(name = "recipe_id") int recipeId, @PathVariable(name = "ingredient_id") int ingredientId) {}

    @DeleteMapping("/recipes/{recipe_id}/categories/{category_id}")
    public void deleteRecipeCategory(@PathVariable(name = "recipe_id") int recipeId, @PathVariable(name = "category_id") int categoryId) {}
}
