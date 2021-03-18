package com.example.fooducate;

public class HistoryModel {
    private String title, description;
    private int productImage, nutriImage, time;

    public HistoryModel(String title, String description, int productImage, int nutriImage, int time) {
        this.title = title;
        this.description = description;
        this.productImage = productImage;
        this.nutriImage = nutriImage;
        this.time = time;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
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

    public int getProductImage() {
        return productImage;
    }

    public void setProductImage(int productImage) {
        this.productImage = productImage;
    }

    public int getNutriImage() {
        return nutriImage;
    }

    public void setNutriImage(int nutriImage) {
        this.nutriImage = nutriImage;
    }
}
