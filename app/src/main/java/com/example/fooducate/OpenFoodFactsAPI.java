package com.example.fooducate;


import com.example.fooducate.models.ResponseObject;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;


public interface OpenFoodFactsAPI {

    @Headers("User-Agent: Fooducate - Android - Version 1.0")
    @GET("/api/v0/product/{barcode}")
    Call<ResponseObject> getProducts(@Path("barcode") String barcode);
}
