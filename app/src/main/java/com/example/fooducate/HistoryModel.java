package com.example.fooducate;

public class HistoryModel {
    private String title, description, productImage;
    int nutriImage;

    public HistoryModel(String title, String description, String productImage, int nutriImage) {
        this.title = title;
        this.description = description;
        this.productImage = productImage;
        this.nutriImage = nutriImage;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getProductImage() {
        return productImage;
    }

    public void setProductImage(String productImage) {
        this.productImage = productImage;
    }

    public int getNutriImage() {
        return nutriImage;
    }

    public void setNutriImage(int nutriImage) {
        this.nutriImage = nutriImage;
    }
}
