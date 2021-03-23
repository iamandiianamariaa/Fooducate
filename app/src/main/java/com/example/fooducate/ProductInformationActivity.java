package com.example.fooducate;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ProductInformationActivity extends AppCompatActivity {
    private FirebaseDatabase mFirebaseDatabase;
    private FirebaseAuth mAuth;
    private DatabaseReference myRef;
    private String userID;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private MainAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_information);
        tabLayout = findViewById(R.id.tab_layout);
        viewPager = findViewById(R.id.view_pager);
        adapter = new MainAdapter(getSupportFragmentManager());
        adapter.addFragment(new One(),"Summary");
        adapter.addFragment(new Two(),"Ingredients");
        adapter.addFragment(new Three(),"Nutrition");
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

        mAuth = FirebaseAuth.getInstance();
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        myRef = mFirebaseDatabase.getReference("users");
        FirebaseUser user = mAuth.getCurrentUser();
        userID = user.getUid();

        TextView text = findViewById(R.id.txt);

        Bundle b = getIntent().getExtras();
        String id = b.getString("barcode");
        Boolean scanned = b.getBoolean("scanned");

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://us.openfoodfacts.org")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        OpenFoodFactsAPI jsonPlaceHolderApi = retrofit.create(OpenFoodFactsAPI.class);

        Call<ResponseObject> call = jsonPlaceHolderApi.getProducts(id);
        call.enqueue(new Callback<ResponseObject>() {
            @Override
            public void onResponse(Call<ResponseObject> call, Response<ResponseObject> response) {
                if (!response.isSuccessful()) {
                    //text.setText("Code: " + response.code());
                    return;
                }

                ResponseObject product = response.body();
                if(scanned)
                {
                    Date scanDate = new Date();
                    scanDate.setTime(b.getLong("time", -1));
                    FirebaseModel addInFirebase = new FirebaseModel(product, scanDate);
                    if(product.getStatus() == 0)
                    {
                        startActivity(new Intent(getApplicationContext(), FoodNotFoundActivity.class));
                        finish();
                    }
                    else {

                        myRef.child(userID).child(product.getProduct().getBarcode()).setValue(addInFirebase, new DatabaseReference.CompletionListener() {
                            @Override
                            public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
                                if (error != null) {
                                    Log.d("FIREBASEDB", "Data could not be saved " + error.getMessage());
                                } else {
                                    Log.d("FIREBASEDB", "Data saved successfully.");
                                }

                            }

                        });
                    }
                }

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
            @Override
            public void onFailure(Call<ResponseObject> call, Throwable t) {
                //text.setText(t.getMessage());
            }
        });

    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
        finish();
    }

    private class MainAdapter extends FragmentPagerAdapter{
        private ArrayList<Fragment> fragmentArrayList = new ArrayList<>();
        private ArrayList<String> stringArrayList = new ArrayList<>();

        public void addFragment(Fragment fragment, String s){
            fragmentArrayList.add(fragment);
            stringArrayList.add(s);

        }
        public MainAdapter(@NonNull FragmentManager fm) {
            super(fm);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            return fragmentArrayList.get(position);
        }

        @Override
        public int getCount() {
            return fragmentArrayList.size();
        }


        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return stringArrayList.get(position);
        }
    }
}
