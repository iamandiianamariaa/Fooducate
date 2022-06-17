package com.example.fooducate.models;

import com.google.gson.annotations.SerializedName;

public class Display {
    @SerializedName(value="fr", alternate={"ro", "en","de"})
    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
