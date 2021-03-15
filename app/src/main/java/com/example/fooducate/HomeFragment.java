package com.example.fooducate;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HomeFragment extends Fragment {
    private EditText searchText;
    private Button button;
    private ViewPager viewPager;
    private ArrayList<SwipeModel> modelArrayList;
    private Adapter adapter;
    private RecyclerView featuredRecycler, mostViewedRecycler, categoriesRecycler;
    private RecyclerView.Adapter recycleAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_fragment_layout, container, false);
        searchText = view.findViewById(R.id.search_bar);
        featuredRecycler = view.findViewById(R.id.featured_recycler);

        button = view.findViewById(R.id.sbutton);
        viewPager = view.findViewById(R.id.viewPager);
        featuredRecycler();
        loadCards();

        button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String barCode = searchText.getText().toString().trim();
                if (!TextUtils.isEmpty(barCode)) {

                    Intent intent = new Intent(getContext(), ProductInformationActivity.class);
                    intent.putExtra("barcode", barCode);
                    startActivity(intent);
                }
                else {
                    final View viewPos = view.findViewById(R.id.myCoordinatorLayout);
                    Snackbar.make(viewPos, "Please add a bar code", Snackbar.LENGTH_SHORT)
                            .show();
                }
            }
        });
        return view;
    }
    private void featuredRecycler() {

        featuredRecycler.setHasFixedSize(true);
        featuredRecycler.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));

        ArrayList<FeaturedHelperClass> featuredLocations = new ArrayList<>();

        featuredLocations.add(new FeaturedHelperClass(R.drawable.sleep, "Mcdonald's", "asbkd asudhlasn saudnas jasdjasl hisajdl asjdlnas"));
        featuredLocations.add(new FeaturedHelperClass(R.drawable.home, "Edenrobe", "asbkd asudhlasn saudnas jasdjasl hisajdl asjdlnas"));
        featuredLocations.add(new FeaturedHelperClass(R.drawable.water_consumption, "Walmart", "asbkd asudhlasn saudnas jasdjasl hisajdl asjdlnas"));

        recycleAdapter = new FeaturedAdapter(featuredLocations);
        featuredRecycler.setAdapter(recycleAdapter);


    }

    private void loadCards(){
        modelArrayList = new ArrayList<>();

        modelArrayList.add(new SwipeModel(
                "Don't forget to drink water",
                "Water plays many roles in your body, including maintaining electrolyte balance and blood pressure, lubricating joints, regulating body temperature, and promoting cell health.",
                R.drawable.water_consumption
        ));
        modelArrayList.add(new SwipeModel(
                "Eat vegetables and fruits",
                "Fruits and vegetables are low in fat, salt and sugar. They are a good source of dietary fibre. As part of a well-balanced, regular diet and a healthy, active lifestyle, a high intake of fruit and vegetables can help you to reduce obesity and maintain a healthy weight, lower your cholesterol and lower your blood pressure.",
                R.drawable.fruit
        ));

        modelArrayList.add(new SwipeModel(
                "Avoid processed junk food",
                "Processed junk food is incredibly unhealthy. These foods have been engineered to trigger your pleasure centers, so they trick your brain into overeating — even promoting food addiction in some people. They’re usually low in fiber, protein, and micronutrients but high in unhealthy ingredients like added sugar and refined grains. ",
                R.drawable.junk_food
        ));

        modelArrayList.add(new SwipeModel(
                "Consume less salt and sugar",
                "Reduce your salt intake to 5g per day, equivalent to about one teaspoon. Consuming excessive amounts of sugars increases the risk of tooth decay and unhealthy weight gain. The maximum amount per day is 50g or about 12 teaspoons for an adult. ",
                R.drawable.sugar
        ));

        adapter = new Adapter(getContext(), modelArrayList);
        viewPager.setAdapter(adapter);
    }
}
