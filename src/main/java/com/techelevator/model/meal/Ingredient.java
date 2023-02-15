package com.techelevator.model.meal;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

public class Ingredient {

    private int id;
    private String name;
    private double quantity;
    private String unitOfMeasure;

    public Ingredient() {
    }

    public Ingredient(int id, String name, double quantity, String unitOfMeasure) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.unitOfMeasure = unitOfMeasure;
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

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public String getUnitOfMeasure() {
        return unitOfMeasure;
    }

    public void setUnitOfMeasure(String unitOfMeasure) {
        this.unitOfMeasure = unitOfMeasure;
    }
}
