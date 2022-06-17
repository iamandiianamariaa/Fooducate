package com.example.fooducate.fragments;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fooducate.adapters.HistoryAdapter;
import com.example.fooducate.R;
import com.example.fooducate.activities.ProductInformationActivity;
import com.example.fooducate.db.UserRepository;
import com.example.fooducate.models.FirebaseModel;
import com.example.fooducate.models.HistoryModel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Timer;
import java.util.TimerTask;

import it.xabaras.android.recyclerview.swipedecorator.RecyclerViewSwipeDecorator;

public class HistoryFragment extends Fragment implements HistoryAdapter.OnProductListener {
    private RecyclerView recyclerView;
    private HistoryAdapter adapter;
    private DatabaseReference myRef;
    private ArrayList<HistoryModel> products;
    private Context context;
    ImageView imageView;
    private UserRepository userRepository;

    public HistoryFragment(Context context) {
        this.context = context;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.history_fragment_layout, container, false);

        imageView= view.findViewById(R.id.notfound);
        imageView.setVisibility(View.INVISIBLE);
        context = container.getContext();
        recyclerView = view.findViewById(R.id.recyclerViewHistory);
        userRepository = new UserRepository(getActivity().getApplication());

        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        FirebaseDatabase mFirebaseDatabase = FirebaseDatabase.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        String userID = user.getUid();
        myRef = mFirebaseDatabase.getReference("users").child(userID);
        showProgressDialog();
        Recycler();

        //createExampleList();

        EditText editText = view.findViewById(R.id.search_history);
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                filter(s.toString());
            }
        });

        return view;
    }

    private void filter(String text) {
        ArrayList<HistoryModel> filteredList = new ArrayList<>();

        for (HistoryModel item : products) {
            if (item.getTitle().toLowerCase().contains(text.toLowerCase())) {
                filteredList.add(item);
            }
        }

        adapter.filterList(filteredList);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void Recycler() {

        recyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));

        products = new ArrayList<>();

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                products.clear();
                if(snapshot.getChildrenCount()==0)
                    imageView.setVisibility(View.VISIBLE);
                else
                    imageView.setVisibility(View.INVISIBLE);
                for(DataSnapshot dataSnapshot: snapshot.getChildren())
                {
                    FirebaseModel obj = dataSnapshot.getValue(FirebaseModel.class);
                    String nutriscore;
                    if(obj.getObject().getProduct().getNutriscore() == null)
                        nutriscore = "nutri";
                    else nutriscore = "nutri_" + obj.getObject().getProduct().getNutriscore().toLowerCase();

                    int imageId = getResources().getIdentifier(nutriscore, "drawable", context.getPackageName());
                    if(obj.getObject().getProduct().getImages()!=null && obj.getObject().getProduct().getImages().getFront()!=null)
                        products.add(new HistoryModel(obj.getObject().getProduct().getName(),obj.getObject().getProduct().getCompany(),obj.getObject().getProduct().getImages().getFront().getDisplay().getUrl(),imageId, obj.getScanDate(), obj.getObject().getProduct().getBarcode()));
                    else
                        products.add(new HistoryModel(obj.getObject().getProduct().getName(),obj.getObject().getProduct().getCompany(),"https://www.toyland.md/front-assets/img/no-image.png",imageId, obj.getScanDate(), obj.getObject().getProduct().getBarcode()));
                    products.sort(Comparator.comparing(HistoryModel::getScanDate, Comparator.reverseOrder()));


                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleCallback);
        itemTouchHelper.attachToRecyclerView(recyclerView);
        adapter = new HistoryAdapter(products, this);
        recyclerView.setAdapter(adapter);
    }

    ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
            return false;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {

            int position = viewHolder.getAdapterPosition();
            FirebaseAuth mAuth = FirebaseAuth.getInstance();
            FirebaseDatabase mFirebaseDatabase = FirebaseDatabase.getInstance();
            FirebaseUser user = mAuth.getCurrentUser();
            String userID = user.getUid();
            DatabaseReference myRef = mFirebaseDatabase.getReference("users");
            myRef.child(userID).child(products.get(position).getBarcode()).removeValue();
            userRepository.delete(products.get(position).getBarcode());
            products.remove(position);
            adapter.notifyItemRemoved(position);

        }

        @Override
        public void onChildDraw(@NonNull Canvas c, @NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {

            new RecyclerViewSwipeDecorator.Builder(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
                    .addSwipeLeftBackgroundColor(ContextCompat.getColor(getContext(), R.color.red))
                    .addSwipeLeftActionIcon(R.drawable.ic_baseline_delete_24)
                    .create()
                    .decorate();

            super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
        }
    };

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
