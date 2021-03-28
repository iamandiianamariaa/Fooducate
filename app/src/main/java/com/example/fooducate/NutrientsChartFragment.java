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
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.HashMap;

public class NutrientsChartFragment extends Fragment {
    private HashMap<String, Integer> hashMap;
    private String[] labelArray = {"Fat", "Saturated Fat", "Sugars", "Carbs", "Sodium", "Salt"};

    public NutrientsChartFragment(HashMap<String, Integer> hashMap) {
        this.hashMap = hashMap;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.nutrients_fragment_layout, container, false);

        PieChart piechart = view.findViewById(R.id.chart1);
        piechart.setUsePercentValues(true);
        piechart.getDescription().setEnabled(false);
        piechart.setExtraOffsets(0, -200, 0, -50);

        piechart.setDragDecelerationFrictionCoef(0.99f);

        piechart.setCenterTextTypeface(Typeface.MONOSPACE);
        piechart.setDrawHoleEnabled(true);
        piechart.setHoleColor(Color.WHITE);
        piechart.setTransparentCircleRadius(61f);

        piechart.setCenterText("Nutrients report");
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

        if (hashMap.get("Fat") != null)
        {
            values.add(new PieEntry(hashMap.get("Fat"), labelArray[0]));
            colors.add(Color.parseColor("#FF5553"));
        }

        if (hashMap.get("Saturated") != null)
        {
            values.add(new PieEntry(hashMap.get("Saturated"), labelArray[1]));
            colors.add(Color.parseColor("#FF8650"));
        }

        if (hashMap.get("Sugars") != null)
        {
            values.add(new PieEntry(hashMap.get("Sugars"), labelArray[2]));
            colors.add(Color.parseColor("#FFE981"));
        }

        if (hashMap.get("Carbs") != null)
        {
            values.add(new PieEntry(hashMap.get("Carbs"), labelArray[3]));
            colors.add(Color.parseColor("#8BF18B"));
        }

        if (hashMap.get("Sodium") != null)
        {
            values.add(new PieEntry(hashMap.get("Sodium"), labelArray[4]));
            colors.add(Color.parseColor("#83B2FF"));
        }
        if (hashMap.get("Salt") != null)
        {
            values.add(new PieEntry(hashMap.get("Salt"), labelArray[5]));
            colorsLegend.add(Color.parseColor("#9B6EF3"));
        }

        PieDataSet pieDataSet = new PieDataSet(values, "Nutrients report");

        pieDataSet.setSliceSpace(3f);
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

        LegendEntry[] legendEntries =new LegendEntry[5];
        for(int i=0; i<legendEntries.length;i++)
        {
            LegendEntry entry = new LegendEntry();
            entry.label = labelArray[i];
            entry.formColor = colorsLegend.get(i);
            legendEntries[i]=entry;
        }

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
