package com.techelevator.model.meal;

public class MealEntry {

    private int id;
    private MealDay mealDay;
    private MealType mealType;
    private Meal meal;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public MealDay getMealDay() {
        return mealDay;
    }

    public void setMealDay(MealDay mealDay) {
        this.mealDay = mealDay;
    }

    public MealType getMealType() {
        return mealType;
    }

    public void setMealType(MealType mealType) {
        this.mealType = mealType;
    }

    public Meal getMeal() {
        return meal;
    }

    public void setMeal(Meal meal) {
        this.meal = meal;
    }
}
