package com.example.fooducate;

import com.google.gson.annotations.SerializedName;

public class ResponseObject {
    @SerializedName("status")
    private int status;

    @SerializedName("status_verbose")
    private String status_verbose;

    @SerializedName("product")
    private Product product;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getStatus_verbose() {
        return status_verbose;
    }

    public void setStatus_verbose(String status_verbose) {
        this.status_verbose = status_verbose;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
