package com.example.fooducate;

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
