package com.techelevator.controller;

import com.techelevator.dao.CategoryDao;
import com.techelevator.model.meal.Category;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CategoryController {

    private CategoryDao categoryDao;

    public CategoryController(CategoryDao categoryDao) {
        this.categoryDao = categoryDao;
    }

    @GetMapping("/categories")
    public List<Category> getCategories() {
        return categoryDao.findAll();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/categories")
    public int addNewCategory(@RequestBody Category category) {
        return categoryDao.create(category.getName());
    }
}
