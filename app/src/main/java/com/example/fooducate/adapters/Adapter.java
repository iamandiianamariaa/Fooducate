package com.example.fooducate.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.example.fooducate.R;
import com.example.fooducate.models.SwipeModel;

import java.util.ArrayList;

public class Adapter extends PagerAdapter {
    private Context context;
    private ArrayList<SwipeModel> modelArrayList;

    public Adapter(Context context, ArrayList<SwipeModel> modelArrayList) {
        this.context = context;
        this.modelArrayList = modelArrayList;
    }

    @Override
    public int getCount() {
        return modelArrayList.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view.equals(object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view = LayoutInflater.from(context).inflate(R.layout.card_item, container, false);

        ImageView photo = view.findViewById(R.id.banner);
        TextView title = view.findViewById(R.id.swipe_title);
        TextView description = view.findViewById(R.id.description);

        SwipeModel model = modelArrayList.get(position);
        photo.setImageResource(model.getImage());
        title.setText(model.getTitle());
        description.setText(model.getDescription());

        container.addView(view, 0);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View)object);
    }
}
