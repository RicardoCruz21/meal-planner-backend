package com.techelevator.model.meal;

public class MealEntry {

    private int id;
    private String mealDay;
    private String mealType;
    private Meal meal;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMealDay() {
        return mealDay;
    }

    public void setMealDay(String mealDay) {
        this.mealDay = mealDay;
    }

    public String getMealType() {
        return mealType;
    }

    public void setMealType(String mealType) {
        this.mealType = mealType;
    }

    public Meal getMeal() {
        return meal;
    }

    public void setMeal(Meal meal) {
        this.meal = meal;
    }
}
