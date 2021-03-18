package com.example.fooducate;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class HistoryFragment extends Fragment {
    private RecyclerView recyclerView;
    private HistoryAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.history_fragment_layout, container, false);

        recyclerView = view.findViewById(R.id.recyclerViewHistory);
        Recycler();
        return view;
    }

    private void Recycler() {

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));

        ArrayList<HistoryModel> products = new ArrayList<>();

        products.add(new HistoryModel("Mcdonald's", "asbkd asudhlasn saudnas jasdjasl hisajdl asjdlnas", R.drawable.home, R.drawable.home, R.drawable.home));
        products.add(new HistoryModel("Mcdonald's", "asbkd asudhlasn saudnas jasdjasl hisajdl asjdlnas", R.drawable.home, R.drawable.home, R.drawable.home));
        products.add(new HistoryModel("Mcdonald's", "asbkd asudhlasn saudnas jasdjasl hisajdl asjdlnas", R.drawable.home, R.drawable.home, R.drawable.home));
        products.add(new HistoryModel("Mcdonald's", "asbkd asudhlasn saudnas jasdjasl hisajdl asjdlnas", R.drawable.home, R.drawable.home, R.drawable.home));
        products.add(new HistoryModel("Mcdonald's", "asbkd asudhlasn saudnas jasdjasl hisajdl asjdlnas", R.drawable.home, R.drawable.home, R.drawable.home));
        products.add(new HistoryModel("Mcdonald's", "asbkd asudhlasn saudnas jasdjasl hisajdl asjdlnas", R.drawable.home, R.drawable.home, R.drawable.home));

        adapter = new HistoryAdapter(products);
        recyclerView.setAdapter(adapter);
    }
}
