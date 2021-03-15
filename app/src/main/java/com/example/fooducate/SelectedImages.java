package com.example.fooducate;

import com.google.gson.annotations.SerializedName;

public class SelectedImages {

    @SerializedName("front")
    private Front front;

    @SerializedName("ingredients")
    private Ingredients ingredients;

    @SerializedName("nutrition")
    private Nutrition nutrition;

    public Front getFront() {
        return front;
    }

    public void setFront(Front front) {
        this.front = front;
    }

    public Ingredients getIngredients() {
        return ingredients;
    }

    public void setIngredients(Ingredients ingredients) {
        this.ingredients = ingredients;
    }

    public Nutrition getNutrition() {
        return nutrition;
    }

    public void setNutrition(Nutrition nutrition) {
        this.nutrition = nutrition;
    }
}
