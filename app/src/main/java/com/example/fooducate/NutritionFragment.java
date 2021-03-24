package com.example.fooducate;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.squareup.picasso.Picasso;

public class NutritionFragment extends Fragment {
    private ResponseObject object;

    public NutritionFragment(ResponseObject object) {
        this.object = object;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.nutrition_fragment_layout, container, false);

        ImageView nutrition = view.findViewById(R.id.nutritionimg);
        ImageView nutri = view.findViewById(R.id.nutriimg);
        String nutriscore;

        if(object.getProduct().getNutriscore()==null)
            nutriscore = "nutri";
        else nutriscore = "nutri_" + object.getProduct().getNutriscore();

        int imageId = getResources().getIdentifier(nutriscore, "drawable", getContext().getPackageName());
        nutri.setImageResource(imageId);

        if(object.getProduct().getImages()!=null && object.getProduct().getImages().getNutrition()!=null)
            Picasso.get().load(object.getProduct().getImages().getNutrition().getDisplay().getUrl()).into(nutrition);
        return view;
    }
}
