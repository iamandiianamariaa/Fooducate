package com.example.fooducate;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.squareup.picasso.Picasso;

public class IngredientsFragment extends Fragment {
    private ResponseObject object;

    public IngredientsFragment(ResponseObject object) {
        this.object = object;
    }

    public IngredientsFragment(int contentLayoutId, ResponseObject object) {
        super(contentLayoutId);
        this.object = object;
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.ingredients_fragment_layout, container, false);
        ImageView image = view.findViewById(R.id.ingredientsimg);
        ImageView nova = view.findViewById(R.id.nova);
        TextView novaScore = view.findViewById(R.id.novascore);
        TextView ingredients = view.findViewById(R.id.ingredients);

        if(object.getProduct().getImages()!=null && object.getProduct().getImages().getIngredients()!=null)
            Picasso.get().load(object.getProduct().getImages().getIngredients().getDisplay().getUrl()).into(image);

        String novascore, text ="";
        if(object.getProduct().getNova()==0)
            novascore = "nova";
        else novascore = "nova_" + object.getProduct().getNova();

        switch (object.getProduct().getNova()){
            case 0:
                text = "Novascore not known";
                break;
            case 1:
                text ="Group 1 - Unprocessed or minimally processed foods";
                break;
            case 2:
                text = "Group 2 - Processed culinary ingredients";
                break;
            case 3:
                text = "Group 3 - Processed foods";
                break;
            case 4:
                text = "Group 4 - Ultra-processed food";
                break;
        }
        novaScore.setText(text);
        int imageId = getResources().getIdentifier(novascore, "drawable", getContext().getPackageName());
        nova.setImageResource(imageId);

        if(object.getProduct().getIngredients()!=null)
            text = "Ingredients: " + object.getProduct().getIngredients();
        if (object.getProduct().getAllergens()!=null)
        {
            text+="\n" + "\n" + "Allergens: ";
            for(String elem : object.getProduct().getAllergens())
                text+=elem.substring(3) + ", ";
            text = text.substring(0, text.length() - 2);
        }

        if(object.getProduct().getAdditives()!=null){
            text+= "\n" + "\n" + "Additives: ";
            for(String elem : object.getProduct().getAdditives())
                text+=elem.substring(3) + ", ";
            text = text.substring(0, text.length() - 2);
        }
        ingredients.setText(text);
        return view;
    }
}
