package com.example.fooducate.models;

import com.google.gson.annotations.SerializedName;

public class NutrientLevels {
    @SerializedName("salt")
    private String salt;

    @SerializedName("sugars")
    private String sugars;

    @SerializedName("saturated-fat")
    private String saturated_fat;

    @SerializedName("fat")
    private String fat;

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getSugars() {
        return sugars;
    }

    public void setSugars(String sugars) {
        this.sugars = sugars;
    }

    public String getSaturated_fat() {
        return saturated_fat;
    }

    public void setSaturated_fat(String saturated_fat) {
        this.saturated_fat = saturated_fat;
    }

    public String getFat() {
        return fat;
    }

    public void setFat(String fat) {
        this.fat = fat;
    }
}
