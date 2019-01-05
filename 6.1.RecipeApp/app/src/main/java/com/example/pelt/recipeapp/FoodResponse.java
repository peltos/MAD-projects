package com.example.pelt.recipeapp;

import java.util.List;

public class FoodResponse {

    public int count;
    public List<Recipes> recipes;

    public FoodResponse(int count, List<Recipes> recipes) {
        this.count = count;
        this.recipes = recipes;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<Recipes> getRecipes() {
        return recipes;
    }

    public void setRecipes(List<Recipes> recipes) {
        this.recipes = recipes;
    }
}
