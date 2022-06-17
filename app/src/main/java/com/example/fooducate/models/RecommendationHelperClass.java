package com.example.fooducate.models;

public class RecommendationHelperClass {
    private int image;
    private String title, description;

    public RecommendationHelperClass(int image, String title, String description) {
        this.image = image;
        this.title = title;
        this.description = description;
    }

    public int getImage() {
        return image;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }
}
