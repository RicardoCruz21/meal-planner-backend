package com.techelevator.controller;

import com.techelevator.dao.RecipeDao;
import com.techelevator.model.meal.Recipe;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

@RestController
public class RecipeController {

    private RecipeDao recipeDao;

    public RecipeController(RecipeDao recipeDao) {
        this.recipeDao = recipeDao;
    }

    @GetMapping("/recipes")
    public List<Recipe> getRecipes(@RequestParam(defaultValue = "") String user_id) {
        if (user_id.equals("")) {
            return recipeDao.findAllPublic();
        } else {
            return recipeDao.findAllByUserId(Integer.parseInt(user_id));
        }
    }

    @GetMapping("/recipes/{id}")
    public Recipe getRecipe(@PathVariable int id) {
        return recipeDao.getRecipeById(id);
    }
}
