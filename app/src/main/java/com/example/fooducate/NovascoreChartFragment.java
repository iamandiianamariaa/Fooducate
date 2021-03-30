package com.example.fooducate;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;


import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.LegendEntry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NovascoreChartFragment extends Fragment {
    private HashMap<Integer, Integer> hashMap;
    private String[] labelArray = {"1", "2", "3", "4"};
    private ViewPager viewPager;

    public NovascoreChartFragment(HashMap<Integer, Integer> hashMap) {
        this.hashMap = hashMap;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.novascore_fragment_layout, container, false);

        viewPager = view.findViewById(R.id.viewPager);

        if(!hashMap.isEmpty()) {
            loadCards();
            PieChart piechart = view.findViewById(R.id.chart1);
            piechart.setUsePercentValues(true);
            piechart.getDescription().setEnabled(false);
            piechart.setExtraOffsets(0, -100, 0, 0);

            piechart.setDragDecelerationFrictionCoef(0.99f);

            piechart.setCenterTextTypeface(Typeface.MONOSPACE);
            piechart.setDrawHoleEnabled(true);
            piechart.setHoleColor(Color.WHITE);
            piechart.setTransparentCircleRadius(61f);

            piechart.setCenterText("Novascore report for the last 7 days");
            piechart.setUsePercentValues(true);
            piechart.setCenterTextSize(20);
            piechart.setCenterTextColor(Color.BLACK);

            ArrayList<PieEntry> values = new ArrayList<>();
            ArrayList<Integer> colorsLegend = new ArrayList<Integer>();
            ArrayList<Integer> colors = new ArrayList<Integer>();
            colorsLegend.add(ContextCompat.getColor(getContext(), R.color.deep_green));
            colorsLegend.add(ContextCompat.getColor(getContext(), R.color.yellow));
            colorsLegend.add(ContextCompat.getColor(getContext(), R.color.orange));
            colorsLegend.add(ContextCompat.getColor(getContext(), R.color.bright_red));

            LegendEntry[] legendEntries = new LegendEntry[4];
            for (int i = 0; i < legendEntries.length; i++) {
                LegendEntry entry = new LegendEntry();
                entry.formColor = colorsLegend.get(i);
                entry.label = labelArray[i];
                legendEntries[i] = entry;
            }
            if (hashMap.get(1) != null) {
                values.add(new PieEntry(hashMap.get(1), labelArray[0]));
                colors.add(ContextCompat.getColor(getContext(), R.color.deep_green));
            }

            if (hashMap.get(2) != null) {
                values.add(new PieEntry(hashMap.get(2), labelArray[1]));
                colors.add(ContextCompat.getColor(getContext(), R.color.yellow));
            }

            if (hashMap.get(3) != null) {
                values.add(new PieEntry(hashMap.get(3), labelArray[2]));
                colors.add(ContextCompat.getColor(getContext(), R.color.orange));
            }

            if (hashMap.get(4) != null) {
                values.add(new PieEntry(hashMap.get(4), labelArray[3]));
                colors.add(ContextCompat.getColor(getContext(), R.color.bright_red));
            }

            PieDataSet pieDataSet = new PieDataSet(values, "Novascore report based on your scanned products");

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
            l.setCustom(legendEntries);

            piechart.animateY(1000, Easing.EaseInOutCubic);
            PieData data = new PieData(pieDataSet);

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
        List<Integer> keys = new ArrayList<>();
        for (Map.Entry<Integer, Integer> entry : hashMap.entrySet()) {
            if (entry.getValue()==max) {
                keys.add(entry.getKey());
            }
        }

        if(keys.contains(1) || keys.contains(2)) {

            modelArrayList.add(new SwipeModel(
                    "Congrats, you managed to avoid overly processed foods!",
                    "Based on your scanned products from the last 7 days, we created a chart which shows how many products of each Novascore level you have eaten. As you can see, you have eaten mostly 1 or 2 level foods, which are least processed. Keep going and you will see great results!",
                    R.drawable.ok
            ));
        }
        if(keys.contains(3) || keys.contains(4)) {

            modelArrayList.add(new SwipeModel(
                    "Not the greatest result!",
                    "Based on your scanned products from the last 7 days, we created a chart which shows how many products of each Novascore level you have eaten. As you can see, you have eaten mostly D or E level foods. You haven't made the best food choices, but you should read our recommendations, because your health may be in danger in the long run.",
                    R.drawable.good
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

            modelArrayList.add(new SwipeModel(
                    "Use substitutes for highly processed snacks and foods",
                    "Instead of potato chips, try nonfat popcorn, which is whole grain and a good source of fiber and still gives the crunch you're looking for. You can also replace sugar-sweetened cereal with unsweetened oatmeal and add fruit for flavor.",
                    R.drawable.fresh
            ));
            modelArrayList.add(new SwipeModel(
                    "Make healthier versions of processed foods",
                    "Make time and cook your meals in the weekend or in the evening for the next days. Be creative and try to reproduce your favourite junk food with healthier ingredients.",
                    R.drawable.wholefood
            ));
        }
        Adapter adapter = new Adapter(getContext(), modelArrayList);
        viewPager.setAdapter(adapter);
    }
}
