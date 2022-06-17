package com.example.fooducate.models;

import com.google.gson.annotations.SerializedName;

public class Nutriments {
    @SerializedName("fat_100g")
    private double fat_100g;

    @SerializedName("fat_serving")
    private double fat_serving;

    @SerializedName("saturated-fat_100g")
    private double saturated_fat_100g;

    @SerializedName("saturated-fat_serving")
    private double saturated_fat_serving;

    @SerializedName("sugars_100g")
    private double sugars_100g;

    @SerializedName("sugars_serving")
    private double sugars_serving;

    @SerializedName("proteins_100g")
    private double proteins_100g;

    @SerializedName("proteins_serving")
    private double proteins_serving;

    @SerializedName("carbohydrates_100g")
    private double carbo_100g;

    @SerializedName("carbohydrates_serving")
    private double carbo_serving;

    @SerializedName("fiber_100g")
    private double fiber_100g;

    @SerializedName("fiber_serving")
    private double fiber_serving;

    @SerializedName("salt_100g")
    private double salt_100g;

    @SerializedName("salt_serving")
    private double salt_serving;

    @SerializedName("sodium_100g")
    private double sodium_100g;

    @SerializedName("sodium_serving")
    private double sodium_serving;

    @SerializedName("energy-kcal_100g")
    private double kcal_100g;

    @SerializedName("energy-kcal_serving")
    private double kcal_serving;

    public double getFat_100g() {
        return fat_100g;
    }

    public void setFat_100g(double fat_100g) {
        this.fat_100g = fat_100g;
    }

    public double getFat_serving() {
        return fat_serving;
    }

    public void setFat_serving(double fat_serving) {
        this.fat_serving = fat_serving;
    }

    public double getSaturated_fat_100g() {
        return saturated_fat_100g;
    }

    public void setSaturated_fat_100g(double saturated_fat_100g) {
        this.saturated_fat_100g = saturated_fat_100g;
    }

    public double getSaturated_fat_serving() {
        return saturated_fat_serving;
    }

    public void setSaturated_fat_serving(double saturated_fat_serving) {
        this.saturated_fat_serving = saturated_fat_serving;
    }

    public double getSugars_100g() {
        return sugars_100g;
    }

    public void setSugars_100g(double sugars_100g) {
        this.sugars_100g = sugars_100g;
    }

    public double getSugars_serving() {
        return sugars_serving;
    }

    public void setSugars_serving(double sugars_serving) {
        this.sugars_serving = sugars_serving;
    }

    public double getProteins_100g() {
        return proteins_100g;
    }

    public void setProteins_100g(double proteins_100g) {
        this.proteins_100g = proteins_100g;
    }

    public double getProteins_serving() {
        return proteins_serving;
    }

    public void setProteins_serving(double proteins_serving) {
        this.proteins_serving = proteins_serving;
    }

    public double getCarbo_100g() {
        return carbo_100g;
    }

    public void setCarbo_100g(double carbo_100g) {
        this.carbo_100g = carbo_100g;
    }

    public double getCarbo_serving() {
        return carbo_serving;
    }

    public void setCarbo_serving(double carbo_serving) {
        this.carbo_serving = carbo_serving;
    }

    public double getFiber_100g() {
        return fiber_100g;
    }

    public void setFiber_100g(double fiber_100g) {
        this.fiber_100g = fiber_100g;
    }

    public double getFiber_serving() {
        return fiber_serving;
    }

    public void setFiber_serving(double fiber_serving) {
        this.fiber_serving = fiber_serving;
    }

    public double getSalt_100g() {
        return salt_100g;
    }

    public void setSalt_100g(double salt_100g) {
        this.salt_100g = salt_100g;
    }

    public double getSalt_serving() {
        return salt_serving;
    }

    public void setSalt_serving(double salt_serving) {
        this.salt_serving = salt_serving;
    }

    public double getSodium_100g() {
        return sodium_100g;
    }

    public void setSodium_100g(double sodium_100g) {
        this.sodium_100g = sodium_100g;
    }

    public double getSodium_serving() {
        return sodium_serving;
    }

    public void setSodium_serving(double sodium_serving) {
        this.sodium_serving = sodium_serving;
    }

    public double getKcal_100g() {
        return kcal_100g;
    }

    public void setKcal_100g(int kcal_100g) {
        this.kcal_100g = kcal_100g;
    }

    public double getKcal_serving() {
        return kcal_serving;
    }

    public void setKcal_serving(int kcal_serving) {
        this.kcal_serving = kcal_serving;
    }
}
