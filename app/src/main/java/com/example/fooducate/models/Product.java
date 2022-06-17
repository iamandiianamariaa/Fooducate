package com.example.fooducate.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Product {
    @SerializedName("code")
    private String barcode;

    @SerializedName("product_name")
    private String name;

    @SerializedName("brands")
    private String company;

    @SerializedName("quantity")
    private String quantity;

    @SerializedName("serving_size")
    private String serving_size;

    @SerializedName("nova_group")
    private int nova;

    @SerializedName("nutriscore_grade")
    private String nutriscore;

    @SerializedName("ecoscore_grade")
    private String ecoscore;

    @SerializedName("ingredients_text")
    private String ingredients;

    @SerializedName("allergens_tags")
    private List<String> allergens;

    @SerializedName("additives_tags")
    private List<String> additives;

    @SerializedName("labels_tags")
    private List<String> labels;

    @SerializedName("ingredients_analysis_tags")
    private List<String> analysis;

    @SerializedName("nutrient_levels")
    private NutrientLevels levels;

    @SerializedName("nutriments")
    private Nutriments nutriments;

    @SerializedName("selected_images")
    private SelectedImages images;

    @SerializedName("nutriscore_data")
    private NutriscoreData nutriscoreData;

    public NutriscoreData getNutriscoreData() {
        return nutriscoreData;
    }

    public void setNutriscoreData(NutriscoreData nutriscoreData) {
        this.nutriscoreData = nutriscoreData;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getServing_size() {
        return serving_size;
    }

    public void setServing_size(String serving_size) {
        this.serving_size = serving_size;
    }

    public int getNova() {
        return nova;
    }

    public void setNova(int nova) {
        this.nova = nova;
    }

    public String getNutriscore() {
        return nutriscore;
    }

    public void setNutriscore(String nutriscore) {
        this.nutriscore = nutriscore;
    }

    public String getEcoscore() {
        return ecoscore;
    }

    public void setEcoscore(String ecoscore) {
        this.ecoscore = ecoscore;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public List<String> getAllergens() {
        return allergens;
    }

    public void setAllergens(List<String> allergens) {
        this.allergens = allergens;
    }

    public List<String> getAdditives() {
        return additives;
    }

    public void setAdditives(List<String> additives) {
        this.additives = additives;
    }

    public List<String> getLabels() {
        return labels;
    }

    public void setLabels(List<String> labels) {
        this.labels = labels;
    }

    public List<String> getAnalysis() {
        return analysis;
    }

    public void setAnalysis(List<String> analysis) {
        this.analysis = analysis;
    }

    public NutrientLevels getLevels() {
        return levels;
    }

    public void setLevels(NutrientLevels levels) {
        this.levels = levels;
    }

    public Nutriments getNutriments() {
        return nutriments;
    }

    public void setNutriments(Nutriments nutriments) {
        this.nutriments = nutriments;
    }

    public SelectedImages getImages() {
        return images;
    }

    public void setImages(SelectedImages images) {
        this.images = images;
    }

    public String getName() {
        return name;
    }

    public String getCompany() {
        return company;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

}
