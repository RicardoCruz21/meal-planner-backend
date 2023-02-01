package com.techelevator.model.meal;

import java.util.ArrayList;
import java.util.List;


public class MealPlan {

    private int id;
    private String name;
    private List<MealEntry> mealEntries = new ArrayList<>();

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

    public List<MealEntry> getMealEntries() {
        return mealEntries;
    }

    public void setMealEntries(List<MealEntry> mealEntries) {
        this.mealEntries = mealEntries;
    }
}
