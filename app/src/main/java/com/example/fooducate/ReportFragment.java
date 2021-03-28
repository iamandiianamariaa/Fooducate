package com.example.fooducate;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;

public class ReportFragment extends Fragment {
    private HashMap<String, Integer> nutrientsMap = new HashMap<String, Integer>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.reports_fragment_layout, container, false);
        TabLayout tabLayout = view.findViewById(R.id.tab_layout);
        ViewPager viewPager = view.findViewById(R.id.view_pager);
        MainAdapter adapter = new MainAdapter(getChildFragmentManager());
        HashMap<String, Integer> nutriMap = new HashMap<String, Integer>();
        HashMap<Integer, Integer> novaMap = new HashMap<Integer, Integer>();

        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        FirebaseDatabase mFirebaseDatabase = FirebaseDatabase.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        String userID = user.getUid();
        DatabaseReference myRef = mFirebaseDatabase.getReference("users").child(userID);

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot: snapshot.getChildren()) {
                    FirebaseModel obj = dataSnapshot.getValue(FirebaseModel.class);

                    if (obj.getObject().getProduct().getNutriscore() != null) {
                        Date today = new Date();
                        long diff =  today.getTime() - obj.getScanDate().getTime();
                        int numOfDays = (int) (diff / (1000 * 60 * 60 * 24));

                        if(numOfDays<=7) {
                            Integer score = nutriMap.get(obj.getObject().getProduct().getNutriscore());
                            if (score == null)
                                nutriMap.put(obj.getObject().getProduct().getNutriscore(), 1);
                            else
                                nutriMap.put(obj.getObject().getProduct().getNutriscore(), ++score);
                        }
                }
                    if (obj.getObject().getProduct().getNova() != 0) {
                        Date today = new Date();
                        long diff =  today.getTime() - obj.getScanDate().getTime();
                        int numOfDays = (int) (diff / (1000 * 60 * 60 * 24));

                        if(numOfDays<=7) {

                            Integer score = novaMap.get(obj.getObject().getProduct().getNova());
                            if (score == null)
                                novaMap.put(obj.getObject().getProduct().getNova(), 1);
                            else
                                novaMap.put(obj.getObject().getProduct().getNova(), ++score);
                        }
                    }
                    Date today = new Date();
                    long diff =  today.getTime() - obj.getScanDate().getTime();
                    int numOfDays = (int) (diff / (1000 * 60 * 60 * 24));
                    if(numOfDays<=7) {

                        addInHash("Fat", obj.getObject().getProduct().getNutriments().getFat_100g());
                        addInHash("Saturated", obj.getObject().getProduct().getNutriments().getSaturated_fat_100g());
                        addInHash("Sugars", obj.getObject().getProduct().getNutriments().getSugars_100g());
                        addInHash("Carbs", obj.getObject().getProduct().getNutriments().getCarbo_100g());
                        addInHash("Sodium", obj.getObject().getProduct().getNutriments().getSodium_100g());
                        addInHash("Salt", obj.getObject().getProduct().getNutriments().getSalt_100g());
                    }

                }
                adapter.notifyDataSetChanged();
                adapter.addFragment(new NutriscoreChartFragment(nutriMap),"Nutriscore");
                adapter.addFragment(new NovascoreChartFragment(novaMap),"Novascore");
                adapter.addFragment(new NutrientsChartFragment(nutrientsMap),"Nutrients");
                viewPager.setAdapter(adapter);
                tabLayout.setupWithViewPager(viewPager);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        return view;
    }

    private class MainAdapter extends FragmentPagerAdapter {
        private ArrayList<Fragment> fragmentArrayList = new ArrayList<>();
        private ArrayList<String> stringArrayList = new ArrayList<>();

        public void addFragment(Fragment fragment, String s){
            fragmentArrayList.add(fragment);
            stringArrayList.add(s);

        }
        public MainAdapter(@NonNull FragmentManager fm) {
            super(fm);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            return fragmentArrayList.get(position);
        }

        @Override
        public int getCount() {
            return fragmentArrayList.size();
        }


        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return stringArrayList.get(position);
        }
    }

    public void addInHash(String nutriment, double value){
        if (value != 0) {

            Integer score = nutrientsMap.get(nutriment);
            if (score == null)
                nutrientsMap.put(nutriment, 1);
            else
                nutrientsMap.put(nutriment, ++score);
        }

    }

}
