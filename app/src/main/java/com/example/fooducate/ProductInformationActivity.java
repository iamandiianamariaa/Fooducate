package com.example.fooducate;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ProductInformationActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_information);

        TextView text = findViewById(R.id.txt);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://us.openfoodfacts.org")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        OpenFoodFactsAPI jsonPlaceHolderApi = retrofit.create(OpenFoodFactsAPI.class);
        Bundle b = getIntent().getExtras();
        String id = b.getString("barcode");
        Call<ResponseObject> call = jsonPlaceHolderApi.getProducts(id);
        call.enqueue(new Callback<ResponseObject>() {
            @Override
            public void onResponse(Call<ResponseObject> call, Response<ResponseObject> response) {
                if (!response.isSuccessful()) {
                    text.setText("Code: " + response.code());
                    return;
                }

                ResponseObject product = response.body();
                if(product.getStatus() == 0)
                {
                    text.setText("The product you are searching for cannot be found. Please try again...");
                }
                else{
                    String content = "";
                    content += "NAME: " + product.getProduct().getName()+ "\n";
                    content += "COMPANY: " + product.getProduct().getCompany() + "\n";
                    if(product.getProduct().getIngredients()!=null)
                        content += "Ingredients: " + product.getProduct().getIngredients() + "\n";
                    content += "QUANTITY: " + product.getProduct().getQuantity() + "\n";
                    content += "SERVING SIZE: " + product.getProduct().getServing_size() + "\n";
                    content += "NOVA: " + product.getProduct().getNova() + "\n";
                    content += "NUTRISCORE: " + product.getProduct().getNutriscore() + "\n";
                    if(product.getProduct().getEcoscore()!=null)
                        content += "ECOSCORE: " + product.getProduct().getEcoscore() + "\n";
                    if(product.getProduct().getAllergens()!=null)
                        content += "ALLERGENS: " + product.getProduct().getAllergens() + "\n";
//                    for(String elem : product.getProduct().getAdditives())
//                        content += "ADDITIVES: " + elem + "\n";
//                    for(String elem : product.getProduct().getLabels())
//                        content += "LABELS: " + elem + "\n";
//                    for(String elem : product.getProduct().getAnalysis())
//                        content += "ANALYSIS: " + elem + "\n";
                    if(product.getProduct().getLevels()!=null)
                    {
                        content += "FAT: " + product.getProduct().getLevels().getFat() + "\n";
                        content += "SALT: " + product.getProduct().getLevels().getSalt() + "\n";
                        content += "SUGAR: " + product.getProduct().getLevels().getSugars() + "\n";
                        content += "SATURATED FAT: " + product.getProduct().getLevels().getSaturated_fat() + "\n";
                    }
                    content += "NUTRIMENTS: " + product.getProduct().getNutriments().getCarbo_100g() + "\n";
                    content += "NUTRIMENTS: " + product.getProduct().getNutriments().getCarbo_serving() + "\n";
                    //content += "IMAGES: " + product.getProduct().getImages().getIngredients().getDisplay().getUrl() + "\n";
                    text.append(content);
                }
            }
            @Override
            public void onFailure(Call<ResponseObject> call, Throwable t) {
                text.setText(t.getMessage());
            }
        });

    }

}
