package com.example.fooducate;

import com.google.gson.annotations.SerializedName;

public class NutriscoreData {
    @SerializedName("is_cheese")
    private int isCheese;

    @SerializedName("is_water")
    private int isWater;

    @SerializedName("is_fat")
    private int isFat;

    @SerializedName("is_beverage")
    private int isBeverage;

    @SerializedName("proteins_value")
    private float proteins;

    @SerializedName("fiber_value")
    private float fiber;

    @SerializedName("sugars_value")
    private float sugars;

    @SerializedName("sodium_value")
    private float sodium;

    @SerializedName("energy_value")
    private float energy;

    @SerializedName("saturated_fat_value")
    private float saturatedFat;

    @SerializedName("fruits_vegetables_nuts_colza_walnut_olive_oils_value")
    private float fruits;

    public int getIsCheese() {
        return isCheese;
    }

    public void setIsCheese(int isCheese) {
        this.isCheese = isCheese;
    }

    public int getIsWater() {
        return isWater;
    }

    public void setIsWater(int isWater) {
        this.isWater = isWater;
    }

    public int getIsFat() {
        return isFat;
    }

    public void setIsFat(int isFat) {
        this.isFat = isFat;
    }

    public int getIsBeverage() {
        return isBeverage;
    }

    public void setIsBeverage(int isBeverage) {
        this.isBeverage = isBeverage;
    }

    public float getProteins() {
        return proteins;
    }

    public void setProteins(float proteins) {
        this.proteins = proteins;
    }

    public float getFiber() {
        return fiber;
    }

    public void setFiber(float fiber) {
        this.fiber = fiber;
    }

    public float getSugars() {
        return sugars;
    }

    public void setSugars(float sugars) {
        this.sugars = sugars;
    }

    public float getSodium() {
        return sodium;
    }

    public void setSodium(float sodium) {
        this.sodium = sodium;
    }

    public float getEnergy() {
        return energy;
    }

    public void setEnergy(float energy) {
        this.energy = energy;
    }

    public float getSaturatedFat() {
        return saturatedFat;
    }

    public void setSaturatedFat(float saturatedFat) {
        this.saturatedFat = saturatedFat;
    }

    public float getFruits() {
        return fruits;
    }

    public void setFruits(float fruits) {
        this.fruits = fruits;
    }
}
