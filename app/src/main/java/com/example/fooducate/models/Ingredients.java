package com.example.fooducate.models;

import com.example.fooducate.models.Display;
import com.google.gson.annotations.SerializedName;

public class Ingredients {
    @SerializedName("display")
    private Display display;

    public Display getDisplay() {
        return display;
    }

    public void setDisplay(Display display) {
        this.display = display;
    }
}
