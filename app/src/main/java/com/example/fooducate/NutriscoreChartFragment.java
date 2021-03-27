package com.example.fooducate;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.LegendEntry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;

import java.util.ArrayList;
import java.util.HashMap;

public class NutriscoreChartFragment extends Fragment {
    private HashMap<String, Integer> hashMap;
    String[] labelArray = {"A", "B", "C", "D", "E"};

    public NutriscoreChartFragment(HashMap<String, Integer> hashMap) {
        this.hashMap = hashMap;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.nutriscore_fragment_layout, container, false);

        PieChart piechart = view.findViewById(R.id.chart1);
        piechart.setUsePercentValues(true);
        piechart.getDescription().setEnabled(false);
        piechart.setExtraOffsets(0, -100, 0, 0);

        piechart.setDragDecelerationFrictionCoef(0.99f);

        piechart.setDrawHoleEnabled(true);
        piechart.setHoleColor(Color.WHITE);
        piechart.setTransparentCircleRadius(61f);

        piechart.setCenterText("Nutriscore report based on your scanned products");
        piechart.setCenterTextColor(Color.BLACK);

        ArrayList<PieEntry> values = new ArrayList<>();
        ArrayList<Integer> colorsLegend = new ArrayList<Integer>();
        ArrayList<Integer> colors = new ArrayList<Integer>();

        colorsLegend.add(ContextCompat.getColor(getContext(), R.color.deep_green));
        colorsLegend.add(ContextCompat.getColor(getContext(), R.color.light_green1));
        colorsLegend.add(ContextCompat.getColor(getContext(), R.color.yellow));
        colorsLegend.add(ContextCompat.getColor(getContext(), R.color.orange));
        colorsLegend.add(ContextCompat.getColor(getContext(), R.color.bright_red));

        if (hashMap.get("a") != null)
        {
            values.add(new PieEntry(hashMap.get("a"), labelArray[0]));
            colors.add(ContextCompat.getColor(getContext(), R.color.deep_green));
        }

        if (hashMap.get("b") != null)
        {
            values.add(new PieEntry(hashMap.get("b"), labelArray[1]));
            colors.add(ContextCompat.getColor(getContext(), R.color.light_green1));
        }

        if (hashMap.get("c") != null)
        {
            values.add(new PieEntry(hashMap.get("c"), labelArray[2]));
            colors.add(ContextCompat.getColor(getContext(), R.color.yellow));
        }

        if (hashMap.get("d") != null)
        {
            values.add(new PieEntry(hashMap.get("d"), labelArray[3]));
            colors.add(ContextCompat.getColor(getContext(), R.color.orange));
        }

        if (hashMap.get("e") != null)
        {
            values.add(new PieEntry(hashMap.get("e"), labelArray[4]));
            colors.add(ContextCompat.getColor(getContext(), R.color.bright_red));
        }

        PieDataSet pieDataSet = new PieDataSet(values, "Nutriscore report based on your scanned products");

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

        LegendEntry[] legendEntries =new LegendEntry[5];
        for(int i=0; i<legendEntries.length;i++)
        {
            LegendEntry entry = new LegendEntry();
            entry.formColor = colorsLegend.get(i);
            entry.label = labelArray[i];
            legendEntries[i]=entry;
        }

        l.setCustom(legendEntries);

        piechart.animateY(1000, Easing.EaseInOutCubic);
        PieData data = new PieData(pieDataSet);

        data.setValueTextSize(15f);
        data.setValueTextColor(Color.BLACK);

        piechart.setData(data);
        return view;
    }

}
