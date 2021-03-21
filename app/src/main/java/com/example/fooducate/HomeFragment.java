package com.example.fooducate;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

public class HomeFragment extends Fragment {
    private EditText searchText;
    private Button button;
    private ViewPager viewPager;
    private ArrayList<SwipeModel> modelArrayList;
    private Adapter adapter;
    private RecyclerView recommendationRecycler;
    private RecyclerView.Adapter recycleAdapter;

    RecyclerView rvArticles;
    HomeAdapter mAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.home_fragment_layout, container, false);
        searchText = view.findViewById(R.id.search_bar);
        recommendationRecycler = view.findViewById(R.id.featured_recycler);

        button = view.findViewById(R.id.sbutton);

        rvArticles = view.findViewById(R.id.rvArticles);
        rvArticles.setHasFixedSize(true);

        final RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        rvArticles.setLayoutManager(layoutManager);

        ArrayList<SwipeHomeModel> dataArticles = new ArrayList<>();
        dataArticles.add(new SwipeHomeModel("Nutriscore","The Nutri-Score is a logo that shows the nutritional quality of food products with A to E grades. The Nutri-Score grade is determined by the amount of healthy and unhealthy nutrients.", R.drawable.nutri_a));
        dataArticles.add(new SwipeHomeModel("Nutriscore","Negative points: energy, saturated fat, sugars, sodium (high levels are considered unhealthy). Positive points: the proportion of fruits, vegetables and nuts, of olive, rapeseed, walnut and oils, of fibers and proteins (high levels are considered good for health).", R.drawable.nutri_a));
        dataArticles.add(new SwipeHomeModel("Nova", "Group 1 - Unprocessed or minimally processed foods\n" +
                "Group 2 - Processed culinary ingredients\n" +
                "Group 3 - Processed foods\n" +
                "Group 4 - Ultra-processed food\n" + "and drink products", R.drawable.nova));
        dataArticles.add(new SwipeHomeModel("Ecoscore", "The eco-score takes into account the analysis of the whole production lifecycle such as production, transport and manufacturing of packaging. Each product is given a score of 100, taking account of the recyclability of packaging, labels, country of origin, seasonality.", R.drawable.ecoscore));

        mAdapter = new HomeAdapter(dataArticles);
        rvArticles.setAdapter(mAdapter);
        rvArticles.setPadding(130,100,130,100);

        final SnapHelper snapHelper = new LinearSnapHelper();
        snapHelper.attachToRecyclerView(rvArticles);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                RecyclerView.ViewHolder viewHolder = rvArticles.findViewHolderForAdapterPosition(0);
                RelativeLayout rl1 = viewHolder.itemView.findViewById(R.id.rl1);
                rl1.animate().scaleY(1).scaleX(1).setDuration(350).setInterpolator(new AccelerateInterpolator()).start();
            }
        },100);

        rvArticles.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                View v = snapHelper.findSnapView(layoutManager);
                int pos = layoutManager.getPosition(v);

                RecyclerView.ViewHolder viewHolder = rvArticles.findViewHolderForAdapterPosition(pos);
                RelativeLayout rl1 = viewHolder.itemView.findViewById(R.id.rl1);

                if (newState == RecyclerView.SCROLL_STATE_IDLE){
                    rl1.animate().setDuration(350).scaleX(1).scaleY(1).setInterpolator(new AccelerateInterpolator()).start();
                }else{
                    rl1.animate().setDuration(350).scaleX(0.75f).scaleY(0.75f).setInterpolator(new AccelerateInterpolator()).start();
                }
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }
        });
        //viewPager = view.findViewById(R.id.viewPager);
        recommendationRecycler();
        //loadCards();

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
    private void recommendationRecycler() {

        recommendationRecycler.setHasFixedSize(true);
        recommendationRecycler.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));

        ArrayList<RecommendationHelperClass> recommendations = new ArrayList<>();

        recommendations.add(new RecommendationHelperClass(R.drawable.sleep, "Mcdonald's", "asbkd asudhlasn saudnas jasdjasl hisajdl asjdlnas"));
        recommendations.add(new RecommendationHelperClass(R.drawable.home, "Edenrobe", "asbkd asudhlasn saudnas jasdjasl hisajdl asjdlnas"));
        recommendations.add(new RecommendationHelperClass(R.drawable.water_consumption, "Walmart", "asbkd asudhlasn saudnas jasdjasl hisajdl asjdlnas"));

        recycleAdapter = new RecommendationAdapter(recommendations);
        recommendationRecycler.setAdapter(recycleAdapter);
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
