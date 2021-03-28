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


import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.LegendEntry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;

import java.util.ArrayList;
import java.util.HashMap;

public class NovascoreChartFragment extends Fragment {
    private HashMap<Integer, Integer> hashMap;
    private String[] labelArray = {"1", "2", "3", "4"};

    public NovascoreChartFragment(HashMap<Integer, Integer> hashMap) {
        this.hashMap = hashMap;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.novascore_fragment_layout, container, false);

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

        LegendEntry[] legendEntries =new LegendEntry[4];
        for(int i=0; i<legendEntries.length;i++)
        {
            LegendEntry entry = new LegendEntry();
            entry.formColor = colorsLegend.get(i);
            entry.label = labelArray[i];
            legendEntries[i]=entry;
        }
        if (hashMap.get(1) != null)
        {
            values.add(new PieEntry(hashMap.get(1), labelArray[0]));
            colors.add(ContextCompat.getColor(getContext(), R.color.deep_green));
        }

        if (hashMap.get(2) != null)
        {
            values.add(new PieEntry(hashMap.get(2), labelArray[1]));
            colors.add(ContextCompat.getColor(getContext(), R.color.yellow));
        }

        if (hashMap.get(3) != null)
        {
            values.add(new PieEntry(hashMap.get(3), labelArray[2]));
            colors.add(ContextCompat.getColor(getContext(), R.color.orange));
        }

        if (hashMap.get(4) != null)
        {
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
        return view;
    }
}
