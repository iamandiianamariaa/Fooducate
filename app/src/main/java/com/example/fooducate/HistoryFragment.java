package com.example.fooducate;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

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
import java.util.Date;

public class HistoryFragment extends Fragment implements HistoryAdapter.OnProductListener {
    private RecyclerView recyclerView;
    private HistoryAdapter adapter;
    private FirebaseDatabase mFirebaseDatabase;
    private FirebaseAuth mAuth;
    private DatabaseReference myRef;
    private String userID;
    private ArrayList<HistoryModel> products;

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

        products = new ArrayList<>();

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot: snapshot.getChildren())
                {
                    FirebaseModel obj = dataSnapshot.getValue(FirebaseModel.class);
                    String nutriscore;
                    if(obj.getObject().getProduct().getNutriscore() == null)
                        nutriscore = "nutri";
                    else nutriscore = "nutri_" + obj.getObject().getProduct().getNutriscore();

                    int imageId = getResources().getIdentifier(nutriscore, "drawable", getContext().getPackageName());
                    products.add(new HistoryModel(obj.getObject().getProduct().getName(),obj.getObject().getProduct().getCompany(),obj.getObject().getProduct().getImages().getFront().getDisplay().getUrl(),imageId, obj.getScanDate(), obj.getObject().getProduct().getBarcode()));
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        adapter = new HistoryAdapter(products, this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onProductClick(int position) {
        HistoryModel productClicked = products.get(position);
        Bundle extras = new Bundle();
        extras.putString("barcode", productClicked.getBarcode());
        extras.putBoolean("scanned", false);
        Intent intent = new Intent(getContext(), ProductInformationActivity.class);
        intent.putExtras(extras);
        startActivity(intent);
    }
}
