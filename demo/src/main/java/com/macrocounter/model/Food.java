package com.macrocounter.model;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

public class Food {
    @Min(value = 1)
    private int protein;

    @Min(value = 1)
    private int carbs;

    @Min(value = 1)
    private int fats;

    private int calories;

    @NotBlank
    private String meal;

    public Food(int protein, int carbs, int fats, String meal) {
        this.protein = protein;
        this.carbs = carbs;
        this.fats = fats;
        this.meal = meal;
    }
    // Generic constructor ----------------------------------
    public Food() {

    }
    // Getters ----------------------------------------------
    public int getProtein() {

        return protein;
    }

    public int getCarbs() {

        return carbs;
    }

    public int getFats() {

        return fats;
    }

    public String getMeal() {
        return meal;
    }

    // Setters--------------------------------------------
    public void setProtein(int protein) {

        this.protein = protein;
    }

    public void setCarbs(int carbs) {

        this.carbs = carbs;
    }

    public void setFats(int fats) {

        this.fats = fats;
    }

    public void setCalories(int calories) {

        this.calories = calories;
    }

    public void setMeal(String meal) {

        this.meal = meal;
    }
    // Calculation of calories based input
    public int calculateCalories() {
        int calories = (protein * 4) + (carbs * 4) + (fats * 9);
        return calories;
    }
}
