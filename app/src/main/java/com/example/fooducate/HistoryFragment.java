package com.example.fooducate;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
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
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class HistoryFragment extends Fragment implements HistoryAdapter.OnProductListener {
    private RecyclerView recyclerView;
    private HistoryAdapter adapter;
    private DatabaseReference myRef;
    private ArrayList<HistoryModel> products;
    private Context context;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.history_fragment_layout, container, false);

        context = container.getContext();
        recyclerView = view.findViewById(R.id.recyclerViewHistory);
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        FirebaseDatabase mFirebaseDatabase = FirebaseDatabase.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        String userID = user.getUid();
        myRef = mFirebaseDatabase.getReference("users").child(userID);
        showProgressDialog();
        Recycler();
        return view;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
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

                    int imageId = getResources().getIdentifier(nutriscore, "drawable", context.getPackageName());
                    if(obj.getObject().getProduct().getImages()!=null && obj.getObject().getProduct().getImages().getFront()!=null)
                        products.add(new HistoryModel(obj.getObject().getProduct().getName(),obj.getObject().getProduct().getCompany(),obj.getObject().getProduct().getImages().getFront().getDisplay().getUrl(),imageId, obj.getScanDate(), obj.getObject().getProduct().getBarcode()));
                    else
                        products.add(new HistoryModel(obj.getObject().getProduct().getName(),obj.getObject().getProduct().getCompany(),"http://www.essdetbol.ru/images/no_photo.png",imageId, obj.getScanDate(), obj.getObject().getProduct().getBarcode()));
                    products.sort(Comparator.comparing(HistoryModel::getScanDate, Comparator.reverseOrder()));


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

    private void showProgressDialog(){
        final Dialog lottieProgressbarDialog = new Dialog(getContext(), R.style.Blur);

        View lottieProgressbarView = getLayoutInflater().inflate(R.layout.dialog_lottie, null);

        lottieProgressbarDialog.setContentView(lottieProgressbarView);
        lottieProgressbarDialog.setCancelable(false);
        lottieProgressbarDialog.show();
        final Timer t = new Timer();
        t.schedule(new TimerTask() {
            public void run() {
                lottieProgressbarDialog.dismiss(); //close the dialog
                t.cancel();
            }
        }, 2000);
    }
}
