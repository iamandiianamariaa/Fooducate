package com.example.fooducate;

import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class HistoryFragment extends Fragment {
    private RecyclerView recyclerView;
    private HistoryAdapter adapter;
    private FirebaseDatabase mFirebaseDatabase;
    private FirebaseAuth mAuth;
    private DatabaseReference myRef;
    private String userID;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.history_fragment_layout, container, false);

        recyclerView = view.findViewById(R.id.recyclerViewHistory);
        mAuth = FirebaseAuth.getInstance();
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        userID = user.getUid();
        myRef = mFirebaseDatabase.getReference("users").child(userID);
        Recycler();
        return view;
    }

    private void Recycler() {

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));

        ArrayList<HistoryModel> products = new ArrayList<>();

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot: snapshot.getChildren())
                {
                    ResponseObject obj = dataSnapshot.getValue(ResponseObject.class);
                    String nutriscore;
                    if(obj.getProduct().getNutriscore() == null)
                        nutriscore = "nutri";
                    else nutriscore = "nutri_" + obj.getProduct().getNutriscore();
                    Resources res = getResources();
                    int id = res.getIdentifier(nutriscore, "drawable", getContext().getPackageName());
                    System.out.println("id"+id);
                    products.add(new HistoryModel(obj.getProduct().getName(),obj.getProduct().getCompany(),obj.getProduct().getImages().getFront().getDisplay().getUrl(),id));
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        adapter = new HistoryAdapter(products);
        recyclerView.setAdapter(adapter);
    }
}
