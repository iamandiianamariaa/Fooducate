package com.example.fooducate.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.fooducate.fragments.IngredientsFragment;
import com.example.fooducate.NutriscoreAlgorithm;
import com.example.fooducate.fragments.NutritionFragment;
import com.example.fooducate.OpenFoodFactsAPI;
import com.example.fooducate.R;
import com.example.fooducate.models.ResponseObject;
import com.example.fooducate.db.Scan;
import com.example.fooducate.fragments.SummaryFragment;
import com.example.fooducate.db.User;
import com.example.fooducate.db.UserRepository;
import com.example.fooducate.db.UserWithScans;
import com.example.fooducate.enums.FoodType;
import com.example.fooducate.models.FirebaseModel;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ProductInformationActivity extends AppCompatActivity {
    private DatabaseReference myRef;
    private String userID;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private MainAdapter adapter;
    private UserRepository userRepository;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_information);
        tabLayout = findViewById(R.id.tab_layout);
        viewPager = findViewById(R.id.view_pager);
        adapter = new MainAdapter(getSupportFragmentManager());

        userRepository = new UserRepository(getApplication());

        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        FirebaseDatabase mFirebaseDatabase = FirebaseDatabase.getInstance();
        myRef = mFirebaseDatabase.getReference("users");
        FirebaseUser user = mAuth.getCurrentUser();
        userID = user.getUid();

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
                        String nutri = null;
                        if (product.getProduct().getNutriscoreData().getIsCheese() ==1)
                            nutri = NutriscoreAlgorithm.getNutriscore(product.getProduct(), FoodType.CHEESE);
                        else if (product.getProduct().getNutriscoreData().getIsBeverage() ==1)
                            nutri = NutriscoreAlgorithm.getNutriscore(product.getProduct(), FoodType.BEVERAGE);
                        else if (product.getProduct().getNutriscoreData().getIsFat() ==1)
                            nutri = NutriscoreAlgorithm.getNutriscore(product.getProduct(), FoodType.FAT_MATTER);
                        else nutri = NutriscoreAlgorithm.getNutriscore(product.getProduct(), FoodType.GENERAL);

                        product.getProduct().setNutriscore(nutri);
                        myRef.child(userID).child(product.getProduct().getBarcode()).setValue(addInFirebase, new DatabaseReference.CompletionListener() {
                            @Override
                            public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
                                List<Scan> list = new ArrayList<>();
                                list.add(new Scan(product.getStatus(), product.getProduct().getBarcode(),product.getStatus_verbose(), scanDate));
                                UserWithScans userWithScans =
                                        new UserWithScans(new User(userID), list);

                                userRepository.insert(userWithScans);
                                if (error != null) {
                                    Log.d("FIREBASEDB", "Data could not be saved " + error.getMessage());
                                } else {
                                    Log.d("FIREBASEDB", "Data saved successfully.");
                                }

                            }

                        });
                    }
                }

                adapter.addFragment(new SummaryFragment(product),"Summary");
                adapter.addFragment(new IngredientsFragment(product),"Ingredients");
                adapter.addFragment(new NutritionFragment(product),"Nutrition");
                viewPager.setAdapter(adapter);
                tabLayout.setupWithViewPager(viewPager);

                }
            @Override
            public void onFailure(Call<ResponseObject> call, Throwable t) {
            }
        });

    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
        finish();
    }

    public void onBackButtonPressed(View view) {
        startActivity(new Intent(getApplicationContext(), AnimationActivity.class));
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
