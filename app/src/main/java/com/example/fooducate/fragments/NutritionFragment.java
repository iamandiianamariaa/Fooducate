package com.example.fooducate.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.fooducate.R;
import com.example.fooducate.models.ResponseObject;
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

        TextView energy100, energyServing, fat100, fatServing, sat100, satServing, carb100, carbServing, sugar100, sugarServing, fibre100, fibreServing, protein100, proteinServing, sodium100, sodiumServing, salt100, saltServing;
        energy100 = view.findViewById(R.id.energy100);
        energyServing = view.findViewById(R.id.energyserv);
        fat100 = view.findViewById(R.id.fat100);
        fatServing = view.findViewById(R.id.fatserv);
        sat100 = view.findViewById(R.id.sat100);
        satServing = view.findViewById(R.id.satserv);
        carb100 = view.findViewById(R.id.carb100);
        carbServing = view.findViewById(R.id.carbserv);
        sugar100 = view.findViewById(R.id.sugar100);
        sugarServing = view.findViewById(R.id.sugarserv);
        fibre100 = view.findViewById(R.id.fiber100);
        fibreServing = view.findViewById(R.id.fiberserv);
        protein100 = view.findViewById(R.id.protein100);
        proteinServing = view.findViewById(R.id.proteinserv);
        sodium100 = view.findViewById(R.id.sodium100);
        sodiumServing = view.findViewById(R.id.sodiumserv);
        salt100 = view.findViewById(R.id.salt100);
        saltServing = view.findViewById(R.id.saltserv);


        ImageView nutrition = view.findViewById(R.id.nutritionimg);
        ImageView nutri = view.findViewById(R.id.nutriimg);
        String nutriscore;

        if(object.getProduct().getNutriscore()==null)
            nutriscore = "nutri";
        else nutriscore = "nutri_" + object.getProduct().getNutriscore().toLowerCase();

        int imageId = getResources().getIdentifier(nutriscore, "drawable", getContext().getPackageName());
        nutri.setImageResource(imageId);

        if(object.getProduct().getImages()!=null && object.getProduct().getImages().getNutrition()!=null)
            Picasso.get().load(object.getProduct().getImages().getNutrition().getDisplay().getUrl()).into(nutrition);

        energy100.setText(object.getProduct().getNutriments().getKcal_100g() + " kcal");
        energyServing.setText(object.getProduct().getNutriments().getKcal_serving() + " kcal");
        fat100.setText(object.getProduct().getNutriments().getFat_100g() + " g");
        fatServing.setText(object.getProduct().getNutriments().getFat_serving() + " g");
        sat100.setText(object.getProduct().getNutriments().getSaturated_fat_100g() + " g");
        satServing.setText(object.getProduct().getNutriments().getSaturated_fat_serving() + " g");
        carb100.setText(object.getProduct().getNutriments().getCarbo_100g() + " g");
        carbServing.setText(object.getProduct().getNutriments().getCarbo_serving() + " g");
        sugar100.setText(object.getProduct().getNutriments().getSugars_100g() + " g");
        sugarServing.setText(object.getProduct().getNutriments().getSugars_serving() + " g");
        fibre100.setText(object.getProduct().getNutriments().getFiber_100g() + " g");
        fibreServing.setText(object.getProduct().getNutriments().getFiber_serving() + " g");
        protein100.setText(object.getProduct().getNutriments().getProteins_100g() + " g");
        proteinServing.setText(object.getProduct().getNutriments().getProteins_serving() + " g");
        sodium100.setText(object.getProduct().getNutriments().getSodium_100g() + " g");
        sodiumServing.setText(object.getProduct().getNutriments().getSodium_serving() + " g");
        salt100.setText(object.getProduct().getNutriments().getSalt_100g() + " g");
        saltServing.setText(object.getProduct().getNutriments().getSalt_serving() + " g");
        return view;
    }
}
