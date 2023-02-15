package com.techelevator.model.meal;

import java.util.ArrayList;
import java.util.List;

public class Recipe {

    private int id;
    private String name;
    private String instructions;
    private List<Ingredient> ingredientList = new ArrayList<>();
    private List<Category> categoryList = new ArrayList<>();
    private boolean isSharable;

    public Recipe() {
    }

    public Recipe(int id, String name, String instructions, List<Ingredient> ingredientList, List<Category> categoryList, boolean isSharable) {
        this.id = id;
        this.name = name;
        this.instructions = instructions;
        this.ingredientList = ingredientList;
        this.categoryList = categoryList;
        this.isSharable = isSharable;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public List<Ingredient> getIngredientList() {
        return ingredientList;
    }

    public void setIngredientList(List<Ingredient> ingredientList) {
        this.ingredientList = ingredientList;
    }

    public List<Category> getCategoryList() {
        return categoryList;
    }

    public void setCategoryList(List<Category> categoryList) {
        this.categoryList = categoryList;
    }

    public boolean isSharable() {
        return isSharable;
    }

    public void setSharable(boolean sharable) {
        isSharable = sharable;
    }
}
