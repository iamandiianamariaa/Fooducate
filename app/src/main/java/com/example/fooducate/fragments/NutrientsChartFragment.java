package com.example.fooducate.fragments;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.fooducate.adapters.Adapter;
import com.example.fooducate.utils.MyValueFormatter;
import com.example.fooducate.R;
import com.example.fooducate.models.SwipeModel;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.LegendEntry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;

import java.util.ArrayList;
import java.util.HashMap;

public class NutrientsChartFragment extends Fragment {
    private HashMap<String, Float> hashMap;
    private int numOfProducts;
    ArrayList<SwipeModel> modelArrayList;
    private String[] labelArray = {"Fat", "Saturated Fat", "Sugars", "Carbs", "Sodium", "Fiber","Protein"};
    private ViewPager viewPager;

    public NutrientsChartFragment(HashMap<String, Float> hashMap, int numOfProducts) {
        this.hashMap = hashMap;
        this.numOfProducts = numOfProducts;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.nutrients_fragment_layout, container, false);

        if(!hashMap.isEmpty()) {
            modelArrayList = new ArrayList<>();
            viewPager = view.findViewById(R.id.viewPager);
            PieChart piechart = view.findViewById(R.id.chart1);
            piechart.getDescription().setEnabled(false);
            piechart.setExtraOffsets(0, -100, 0, -50);

            piechart.setDragDecelerationFrictionCoef(0.99f);

            piechart.setCenterTextTypeface(Typeface.MONOSPACE);
            piechart.setDrawHoleEnabled(true);
            piechart.setHoleColor(Color.WHITE);
            piechart.setTransparentCircleRadius(61f);

            piechart.setCenterText("Nutrients report for the last day");
            piechart.setUsePercentValues(true);
            piechart.setCenterTextSize(20);
            piechart.setCenterTextColor(Color.BLACK);

            ArrayList<PieEntry> values = new ArrayList<>();
            ArrayList<Integer> colorsLegend = new ArrayList<Integer>();
            ArrayList<Integer> colors = new ArrayList<Integer>();
            colorsLegend.add(Color.parseColor("#FF555E"));
            colorsLegend.add(Color.parseColor("#FF8650"));
            colorsLegend.add(Color.parseColor("#FFE981"));
            colorsLegend.add(Color.parseColor("#8BF18B"));
            colorsLegend.add(Color.parseColor("#83B2FF"));
            colorsLegend.add(Color.parseColor("#9B6EF3"));
            colorsLegend.add(Color.parseColor("#4CD0A7"));

            if (hashMap.get("Fat") != null) {
                PieEntry pieEntry = new PieEntry(hashMap.get("Fat"), labelArray[0]);
                if (pieEntry.getValue() <= 1)
                    pieEntry.setLabel("");

                values.add(pieEntry);
                colors.add(Color.parseColor("#FF5553"));
                loadCards("Fat", pieEntry.getValue());
            }

            if (hashMap.get("Saturated") != null) {
                PieEntry pieEntry = new PieEntry(hashMap.get("Saturated"), labelArray[1]);
                if (pieEntry.getValue() <= 1)
                    pieEntry.setLabel("");
                values.add(pieEntry);
                colors.add(Color.parseColor("#FF8650"));
                loadCards("Saturated", pieEntry.getValue());
            }

            if (hashMap.get("Sugars") != null) {
                PieEntry pieEntry = new PieEntry(hashMap.get("Sugars"), labelArray[2]);
                if (pieEntry.getValue() <= 1)
                    pieEntry.setLabel("");
                values.add(pieEntry);
                colors.add(Color.parseColor("#FFE981"));
                loadCards("Sugars", pieEntry.getValue());
            }

            if (hashMap.get("Carbs") != null) {
                PieEntry pieEntry = new PieEntry(hashMap.get("Carbs"), labelArray[3]);
                if (pieEntry.getValue() <= 1)
                    pieEntry.setLabel("");
                values.add(pieEntry);
                colors.add(Color.parseColor("#8BF18B"));
                loadCards("Carbs", pieEntry.getValue());
            }

            if (hashMap.get("Sodium") != null) {
                PieEntry pieEntry = new PieEntry(hashMap.get("Sodium"), labelArray[4]);
                if (pieEntry.getValue() <= 1)
                    pieEntry.setLabel("");
                values.add(pieEntry);
                colors.add(Color.parseColor("#83B2FF"));
                loadCards("Sodium", pieEntry.getValue());
            }
            if (hashMap.get("Fiber") != null) {
                PieEntry pieEntry = new PieEntry(hashMap.get("Fiber"), labelArray[5]);
                if (pieEntry.getValue() <= 1)
                    pieEntry.setLabel("");
                values.add(pieEntry);
                colors.add(Color.parseColor("#9B6EF3"));
                loadCards("Fiber", pieEntry.getValue());
            }
            if (hashMap.get("Protein") != null) {
                PieEntry pieEntry = new PieEntry(hashMap.get("Protein"), labelArray[6]);
                if (pieEntry.getValue() <= 1)
                    pieEntry.setLabel("");
                values.add(pieEntry);
                colors.add(Color.parseColor("#4CD0A7"));
                loadCards("Protein", pieEntry.getValue());
            }

            Adapter adapter = new Adapter(getContext(), modelArrayList);
            viewPager.setAdapter(adapter);
            PieDataSet pieDataSet = new PieDataSet(values, "Nutrients report");

            pieDataSet.setSelectionShift(5f);
            pieDataSet.setColors(colors);

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
            l.setWordWrapEnabled(true);

            LegendEntry[] legendEntries = new LegendEntry[7];
            for (int i = 0; i < legendEntries.length; i++) {
                LegendEntry entry = new LegendEntry();
                entry.label = labelArray[i];
                entry.formColor = colorsLegend.get(i);
                legendEntries[i] = entry;
            }

            l.setCustom(legendEntries);

            piechart.animateY(1000, Easing.EaseInOutCubic);

            pieDataSet.setValueFormatter(new MyValueFormatter(piechart));
            PieData data = new PieData(pieDataSet);
            data.setValueTextSize(15f);
            data.setValueTextColor(Color.BLACK);

            piechart.setData(data);
        }
        else
        {
            CardView cardView = view.findViewById(R.id.card);
            cardView.setVisibility(View.INVISIBLE);
        }
        return view;
    }

    private void loadCards(String nutrient, double value){
        switch (nutrient){
            case "Fat":
                if(value>=25 && value<=35)
                    modelArrayList.add(new SwipeModel(
                            "Great job, your fat levels are optimal!",
                            "Based on your scanned products from the last day, we created a chart which shows how much of each nutrient you have consumed. Your fat levels are in the range of 25% and 35%, which is recommended for an active individual.",
                            R.drawable.fat1
                    ));
                else
                    modelArrayList.add(new SwipeModel(
                            "Not the greatest result!",
                            "Based on your scanned products from the last day, we created a chart which shows how much of each nutrient you have consumed. Your fat levels are not in the range of 25% and 35%, which is recommended for an active individual.",
                            R.drawable.fat1
                    ));
                break;
            case "Saturated Fat":
                if(value<=7)
                    modelArrayList.add(new SwipeModel(
                            "Great job, your saturated fat levels are optimal!",
                            "Based on your scanned products from the last day, we created a chart which shows how much of each nutrient you have consumed. Your saturated fat levels are lower than 7%, which is recommended for an active individual.",
                            R.drawable.saturated
                    ));
                else
                    modelArrayList.add(new SwipeModel(
                            "Not the greatest result!",
                            "Based on your scanned products from the last day, we created a chart which shows how much of each nutrient you have consumed. Your saturated fat levels are above the recommended percentage, which is lower than 7% for an active individual.",
                            R.drawable.saturated
                    ));
                break;
            case "Carbs":
                if(value>=45 && value<=55)
                    modelArrayList.add(new SwipeModel(
                            "Great job, your carbs levels are optimal!",
                            "Based on your scanned products from the last day, we created a chart which shows how much of each nutrient you have consumed. Your carbs levels are in the range of 45% and 55%, which is recommended for an active individual.",
                            R.drawable.carbs
                    ));
                else
                    modelArrayList.add(new SwipeModel(
                            "Not the greatest result!",
                            "Based on your scanned products from the last 7 days, we created a chart which shows how much of each nutrient you have consumed. Your carbs levels are not in the range of 45% and 55%, which is recommended for an active individual.",
                            R.drawable.carbs
                    ));
                break;
            case "Sugars":
                if(value<=10)
                    modelArrayList.add(new SwipeModel(
                            "Great job, your sugar levels are optimal!",
                            "Based on your scanned products from the last day, we created a chart which shows how much of each nutrient you have consumed. Your sugar levels are below 10%, which is recommended for an active individual.",
                            R.drawable.sugar2
                    ));
                else
                    modelArrayList.add(new SwipeModel(
                            "Not the greatest result!",
                            "Based on your scanned products from the last day, we created a chart which shows how much of each nutrient you have consumed. Your sugar levels are above the recommended percentage, which is 10% for an active individual.",
                            R.drawable.sugar2
                    ));
                break;
            case "Protein":
                if(value>=10 && value<=30)
                    modelArrayList.add(new SwipeModel(
                            "Great job, your protein levels are optimal!",
                            "Based on your scanned products from the last day, we created a chart which shows how much of each nutrient you have consumed. Your protein levels are in the range of 10% and 30%, which is recommended for an active individual.",
                            R.drawable.protein
                    ));
                else
                    modelArrayList.add(new SwipeModel(
                            "Not the greatest result!",
                            "Based on your scanned products from the last day, we created a chart which shows how much of each nutrient you have consumed. Your protein levels are are not in the range of 10% and 30%, which is recommended for an active individual.",
                            R.drawable.protein
                    ));
                break;
            case "Sodium":
                if(value<=1.5)
                    modelArrayList.add(new SwipeModel(
                            "Great job, your sodium levels are optimal!",
                            "Based on your scanned products from the last day, we created a chart which shows how much of each nutrient you have consumed. Your sodium levels are below 1500mg, which is recommended for an active individual.",
                            R.drawable.sodium
                    ));
                else
                    modelArrayList.add(new SwipeModel(
                            "Not the greatest result!",
                            "Based on your scanned products from the last day, we created a chart which shows how much of each nutrient you have consumed. Your sodium levels are above the recommended value, which is below 1500mg for an active individual.",
                            R.drawable.sodium
                    ));
                break;
            case "Fiber":
                if(value>=25)
                    modelArrayList.add(new SwipeModel(
                            "Great job, your fiber levels are optimal!",
                            "Based on your scanned products from the last day, we created a chart which shows how much of each nutrient you have consumed. Your fiber levels are in the range of 25g and 38g, which is recommended for an active individual.",
                            R.drawable.fruit
                    ));
                else
                    modelArrayList.add(new SwipeModel(
                            "Not the greatest result!",
                            "Based on your scanned products from the last day, we created a chart which shows how much of each nutrient you have consumed. Your fiber levels are not in the range of 25g and 38g, which is recommended for an active individual.",
                            R.drawable.fruit
                    ));
                break;
        }
    }
}
