package com.example.fooducate;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.anychart.AnyChart;
import com.anychart.AnyChartView;
import com.anychart.chart.common.dataentry.DataEntry;
import com.anychart.chart.common.dataentry.ValueDataEntry;
import com.anychart.chart.common.listener.Event;
import com.anychart.chart.common.listener.ListenersInterface;
import com.anychart.charts.Pie;
import com.anychart.enums.Align;
import com.anychart.enums.LegendLayout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class NutriscoreChartFragment extends Fragment {
    private HashMap<String, Integer> hashMap;

    public NutriscoreChartFragment(HashMap<String, Integer> hashMap) {
        this.hashMap = hashMap;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.nutriscore_fragment_layout, container, false);

        AnyChartView anyChartView = view.findViewById(R.id.any_chart_view);

        Pie pie = AnyChart.pie();

        pie.setOnClickListener(new ListenersInterface.OnClickListener(new String[]{"x", "value"}) {
            @Override
            public void onClick(Event event) {
            }
        });

        List<DataEntry> data = new ArrayList<>();
        System.out.println(hashMap.get("a"));
        data.add(new ValueDataEntry("A", hashMap.get("a")));
        data.add(new ValueDataEntry("B", hashMap.get("b")));
        data.add(new ValueDataEntry("C", hashMap.get("c")));
        data.add(new ValueDataEntry("D", hashMap.get("d")));
        data.add(new ValueDataEntry("E", hashMap.get("e")));

        pie.data(data);

        pie.title("Nutriscore report based on your scanned products");

        pie.labels().position("outside");

        pie.legend().title().enabled(true);
        pie.legend().title()
                .text("Legend")
                .padding(0d, 0d, 10d, 0d);

        pie.legend()
                .position("center-bottom")
                .itemsLayout(LegendLayout.HORIZONTAL)
                .align(Align.CENTER);

        anyChartView.setChart(pie);
        return view;
    }

}
