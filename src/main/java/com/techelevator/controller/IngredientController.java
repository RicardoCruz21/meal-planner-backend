package com.techelevator.controller;

import com.techelevator.dao.IngredientDao;
import com.techelevator.model.meal.Ingredient;
import com.techelevator.model.mealDto.IngredientDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class IngredientController {

    private IngredientDao ingredientDao;

    public IngredientController(IngredientDao ingredientDao) {
        this.ingredientDao = ingredientDao;
    }

    @GetMapping("/ingredients")
    public List<IngredientDto> getIngredients() {
        return ingredientDao.findAll();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/ingredients")
    public int addNewIngredient(@RequestBody String ingredient) {
        return ingredientDao.create(ingredient);
    }
}
