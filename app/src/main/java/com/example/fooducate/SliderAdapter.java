package com.example.fooducate;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

public class SliderAdapter extends PagerAdapter {
    private Context context;
    private LayoutInflater layoutInflater;
    ImageView[] dotsImages= new ImageView[4];
    String[] descriptions = {
            "Quickly scan the barcode of a product",
            "See the nutritional values and NutriScore of the food you scan",
            "Once the account is created, you can see the search history for your products",
            "Get statistics and recommendations based on the food you consume"
    };

    int[] slide_images = {
            R.drawable.food,
            R.drawable.nutrition,
            R.drawable.history,
            R.drawable.stats
    };

    public SliderAdapter(Context context){
        this.context = context;
    }

    @Override
    public int getCount() {
        return slide_images.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);

        View view = layoutInflater.inflate(R.layout.slide_layout, container, false);

        ImageView slideImageView = view.findViewById(R.id.imageView3);
        TextView slideDescription = view.findViewById(R.id.descrip);
        dotsImages[0] = view.findViewById(R.id.dot1);
        dotsImages[1] = view.findViewById(R.id.dot2);
        dotsImages[2] = view.findViewById(R.id.dot3);
        dotsImages[3] = view.findViewById(R.id.dot4);

        for(int i=0;i<slide_images.length;i++)
            dotsImages[i].setImageResource(R.drawable.unselected_dot);

        dotsImages[position].setImageResource(R.drawable.selected_dot);

        slideImageView.setImageResource(slide_images[position]);
        slideDescription.setText(descriptions[position]);

        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
