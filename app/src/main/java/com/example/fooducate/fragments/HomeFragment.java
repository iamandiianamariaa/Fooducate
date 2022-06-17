package com.example.fooducate.fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.widget.Button;
import android.widget.EditText;
import android.widget.MediaController;
import android.widget.RelativeLayout;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import com.example.fooducate.adapters.HomeAdapter;
import com.example.fooducate.R;
import com.example.fooducate.adapters.RecommendationAdapter;
import com.example.fooducate.models.RecommendationHelperClass;
import com.example.fooducate.activities.ProductInformationActivity;
import com.example.fooducate.models.SwipeHomeModel;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class HomeFragment extends Fragment {
    private EditText searchText;
    private RecyclerView recommendationRecycler;

    RecyclerView rvArticles;
    HomeAdapter mAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.home_fragment_layout, container, false);
        searchText = view.findViewById(R.id.search_bar);
        recommendationRecycler = view.findViewById(R.id.featured_recycler);

        Button button = view.findViewById(R.id.sbutton);

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
        recommendationRecycler();

        button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String barCode = searchText.getText().toString().trim();
                if (!TextUtils.isEmpty(barCode)) {

                    Intent intent = new Intent(getContext(), ProductInformationActivity.class);
                    Bundle extras = new Bundle();
                    Date date = new Date();
                    extras.putString("barcode", barCode);
                    extras.putBoolean("scanned", true);
                    extras.putLong("time", date.getTime());
                    intent.putExtras(extras);
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

        Calendar cal = Calendar.getInstance();

        if(cal.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY){

            recommendations.add(new RecommendationHelperClass(R.drawable.avocado, "Avocado", "Avocados are different than most fruits because they are loaded with healthy fats instead of carbs."));
            recommendations.add(new RecommendationHelperClass(R.drawable.blueberry, "Blueberries", "Blueberries are not only delicious but also among the most powerful sources of antioxidants in the world."));
            recommendations.add(new RecommendationHelperClass(R.drawable.banana, "Banana", "Bananas are among the world’s best sources of potassium. They’re also high in vitamin B6 and fiber."));
        }
        else if(cal.get(Calendar.DAY_OF_WEEK) == Calendar.TUESDAY){

            recommendations.add(new RecommendationHelperClass(R.drawable.almond, "Almonds", "Almonds are a popular nut loaded with vitamin E, antioxidants, magnesium, and fiber."));
            recommendations.add(new RecommendationHelperClass(R.drawable.chia, "Chia seeds", "Chia seeds are among the most nutrient-dense foods on the planet."));
            recommendations.add(new RecommendationHelperClass(R.drawable.macadamia, "Macadamia nuts", "Macadamia nuts are much higher in monounsaturated fats and lower in omega-6."));
        }
        else if(cal.get(Calendar.DAY_OF_WEEK) == Calendar.WEDNESDAY){

            recommendations.add(new RecommendationHelperClass(R.drawable.broccoli, "Broccoli", "It’s an excellent source of fiber and vitamins C, K and contains a decent amount of protein compared with other vegetables."));
            recommendations.add(new RecommendationHelperClass(R.drawable.carrot, "Carrots", "They are loaded with nutrients like fiber, vitamin K and carotene antioxidants."));
            recommendations.add(new RecommendationHelperClass(R.drawable.garlic, "Garlic", "It contains bioactive organosulfur compounds that improve immune function."));
        }
        else if(cal.get(Calendar.DAY_OF_WEEK) == Calendar.THURSDAY){

            recommendations.add(new RecommendationHelperClass(R.drawable.salmon, "Salmon", "Salmon is a type of oily fish containing high amount of nutrients, including protein and omega-3 fatty acids."));
            recommendations.add(new RecommendationHelperClass(R.drawable.sardines, "Sardines", "Sardines are small, oily fish that are among the most nutritious foods you can eat."));
            recommendations.add(new RecommendationHelperClass(R.drawable.shrimp, "Shrimps", "They are low in fat, high in protein and also loaded with selenium and vitamin B12."));
        }
        else if(cal.get(Calendar.DAY_OF_WEEK) == Calendar.FRIDAY){

            recommendations.add(new RecommendationHelperClass(R.drawable.oat, "Oats", "Whole oats are the only food source of avenanthramides, a unique group of antioxidants believed to protect against heart disease."));
            recommendations.add(new RecommendationHelperClass(R.drawable.rice, "Brown rice", "Brown rice is fairly nutritious, with a decent amount of fiber, vitamin B1, and magnesium."));
            recommendations.add(new RecommendationHelperClass(R.drawable.quinoa, "Quinoa", "It’s a tasty grain high in fiber and magnesium and an excellent source of plant-based protein."));
        }
        else if(cal.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY){

            recommendations.add(new RecommendationHelperClass(R.drawable.milk, "Whole milk", "Whole milk is very high in vitamins, minerals, healthy fats and is one of the best dietary sources of calcium."));
            recommendations.add(new RecommendationHelperClass(R.drawable.yoghurt, "Yogurt", "It has the same health effects as milk, but yogurt with live cultures has the added benefit of friendly probiotic bacteria."));
            recommendations.add(new RecommendationHelperClass(R.drawable.oil, "Extra virgin olive oil", "It is one of the healthiest vegetable oils you can find."));
        }
        else{

            recommendations.add(new RecommendationHelperClass(R.drawable.potato, "Sweet Potatoes", "Sweet potatoes contain beta-carotene, which protects against diseases like cancer and heart disease."));
            recommendations.add(new RecommendationHelperClass(R.drawable.spinach, "Spinach", "It's loaded with iron, folate, beta carotene, lutein and has double the fiber of most other leafy greens."));
            recommendations.add(new RecommendationHelperClass(R.drawable.kiwi, "Kiwi", "Kiwis have about twice as much of the vitamin C as oranges. They are also an excellent source of potassium and phytonutrients."));
        }

        RecyclerView.Adapter recycleAdapter = new RecommendationAdapter(recommendations);
        recommendationRecycler.setAdapter(recycleAdapter);
    }
}
