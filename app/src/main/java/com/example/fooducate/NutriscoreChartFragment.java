package com.example.fooducate;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.LegendEntry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.DefaultValueFormatter;
import com.github.mikephil.charting.formatter.PercentFormatter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NutriscoreChartFragment extends Fragment {
    private HashMap<String, Integer> hashMap;
    private String[] labelArray = {"A", "B", "C", "D", "E"};
    private ViewPager viewPager;

    public NutriscoreChartFragment(HashMap<String, Integer> hashMap) {
        this.hashMap = hashMap;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.nutriscore_fragment_layout, container, false);

        viewPager = view.findViewById(R.id.viewPager);
        if(!hashMap.isEmpty()) {

            loadCards();
            PieChart piechart = view.findViewById(R.id.chart1);
            piechart.setUsePercentValues(true);
            piechart.getDescription().setEnabled(false);
            piechart.setExtraOffsets(0, -100, 0, 0);

            piechart.setDragDecelerationFrictionCoef(0.99f);

            piechart.setDrawHoleEnabled(true);
            piechart.setHoleColor(Color.WHITE);
            piechart.setTransparentCircleRadius(61f);

            piechart.setCenterTextTypeface(Typeface.MONOSPACE);
            piechart.setCenterText("Nutriscore report for the last 7 days");
            piechart.setCenterTextSize(20);
            piechart.setUsePercentValues(true);
            piechart.setCenterTextColor(Color.BLACK);

            ArrayList<PieEntry> values = new ArrayList<>();
            ArrayList<Integer> colorsLegend = new ArrayList<Integer>();
            ArrayList<Integer> colors = new ArrayList<Integer>();

            colorsLegend.add(ContextCompat.getColor(getContext(), R.color.deep_green));
            colorsLegend.add(ContextCompat.getColor(getContext(), R.color.light_green1));
            colorsLegend.add(ContextCompat.getColor(getContext(), R.color.yellow));
            colorsLegend.add(ContextCompat.getColor(getContext(), R.color.orange));
            colorsLegend.add(ContextCompat.getColor(getContext(), R.color.bright_red));

            if (hashMap.get("a") != null) {
                values.add(new PieEntry(hashMap.get("a"), labelArray[0]));
                colors.add(ContextCompat.getColor(getContext(), R.color.deep_green));
            }

            if (hashMap.get("b") != null) {
                values.add(new PieEntry(hashMap.get("b"), labelArray[1]));
                colors.add(ContextCompat.getColor(getContext(), R.color.light_green1));
            }

            if (hashMap.get("c") != null) {
                values.add(new PieEntry(hashMap.get("c"), labelArray[2]));
                colors.add(ContextCompat.getColor(getContext(), R.color.yellow));
            }

            if (hashMap.get("d") != null) {
                values.add(new PieEntry(hashMap.get("d"), labelArray[3]));
                colors.add(ContextCompat.getColor(getContext(), R.color.orange));
            }

            if (hashMap.get("e") != null) {
                values.add(new PieEntry(hashMap.get("e"), labelArray[4]));
                colors.add(ContextCompat.getColor(getContext(), R.color.bright_red));
            }

            PieDataSet pieDataSet = new PieDataSet(values, "Nutriscore report");

            pieDataSet.setColors(colors);
            pieDataSet.setSliceSpace(3f);
            pieDataSet.setSelectionShift(5f);


            Legend l = piechart.getLegend();
            l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
            l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);
            l.setOrientation(Legend.LegendOrientation.HORIZONTAL);
            l.setDrawInside(false);
            l.setEnabled(true);
            l.setTextColor(Color.GRAY);
            l.setTextSize(15);
            l.setForm(Legend.LegendForm.SQUARE);
            l.setFormSize(20);

            LegendEntry[] legendEntries = new LegendEntry[5];
            for (int i = 0; i < legendEntries.length; i++) {
                LegendEntry entry = new LegendEntry();
                entry.formColor = colorsLegend.get(i);
                entry.label = labelArray[i];
                legendEntries[i] = entry;
            }

            l.setCustom(legendEntries);

            piechart.animateY(1000, Easing.EaseInOutCubic);
            PieData data = new PieData(pieDataSet);

            //pieDataSet.setValueFormatter(new MyValueFormatter());
            data.setValueTextSize(15f);
            data.setValueTextColor(Color.BLACK);
            data.setValueFormatter(new PercentFormatter(piechart));

            piechart.setData(data);
        }
        return view;
    }

    private void loadCards(){
        ArrayList<SwipeModel> modelArrayList = new ArrayList<>();
        int max = Collections.max(hashMap.values());
        List<String> keys = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : hashMap.entrySet()) {
            if (entry.getValue()==max) {
                keys.add(entry.getKey());
            }
        }

        if(keys.contains("a") || keys.contains("b")) {

            modelArrayList.add(new SwipeModel(
                    "Congrats, you ate plenty of nutritious foods!",
                    "Based on your scanned products from the last 7 days, we created a chart which shows how many products of each Nutriscore level you have eaten. As you can see, you have eaten mostly A or B level foods, which are the healthiest and most nutrient-dense. Keep going and you will see great results!",
                    R.drawable.ok
            ));
        }
        if(keys.contains("c")){
            modelArrayList.add(new SwipeModel(
                    "Not good, not bad!",
                    "Based on your scanned products from the last 7 days, we created a chart which shows how many products of each Nutriscore level you have eaten. As you can see, you have eaten mostly C level foods. It's not terrible, but try to stick to the healthiest choices. ",
                    R.drawable.good
            ));
        }
        if(keys.contains("d") || keys.contains("e")) {

            modelArrayList.add(new SwipeModel(
                    "Not the greatest result!",
                    "Based on your scanned products from the last 7 days, we created a chart which shows how many products of each Nutriscore level you have eaten. As you can see, you have eaten mostly D or E level foods. You haven't made the best food choices, but you should read our recommendations, because your health may be in danger in the long run.",
                    R.drawable.good
            ));

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
                    R.drawable.sugar1
            ));
        }
        Adapter adapter = new Adapter(getContext(), modelArrayList);
        viewPager.setAdapter(adapter);
    }
}
