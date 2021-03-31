package com.example.fooducate;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.squareup.picasso.Picasso;

public class SummaryFragment extends Fragment {
    private ResponseObject object;

    public SummaryFragment(ResponseObject object) {
        this.object = object;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.summary_fragment_layout, container, false);

        TextView title = view.findViewById(R.id.title);
        TextView brand = view.findViewById(R.id.brand);
        TextView quantity = view.findViewById(R.id.quantity);
        ImageView productImg = view.findViewById(R.id.productimg);
        ImageView nutri = view.findViewById(R.id.nutri);
        ImageView nova = view.findViewById(R.id.nova);
        ImageView eco = view.findViewById(R.id.eco);
        TextView labels = view.findViewById(R.id.labels);

        String nutriscore, novascore, ecoscore, text = "";
        title.setText(object.getProduct().getName());
        brand.setText(object.getProduct().getCompany());

        if(object.getProduct().getImages()!=null && object.getProduct().getImages().getFront()!=null)
            Picasso.get().load(object.getProduct().getImages().getFront().getDisplay().getUrl()).into(productImg);


        quantity.setText(object.getProduct().getQuantity());
        if(object.getProduct().getNutriscore() == null)
            nutriscore = "nutri";
        else nutriscore = "nutri_" + object.getProduct().getNutriscore();

        int imageId = getResources().getIdentifier(nutriscore, "drawable", getContext().getPackageName());
        nutri.setImageResource(imageId);

        if(object.getProduct().getNova()==0)
            novascore = "nova";
        else novascore = "nova_" + object.getProduct().getNova();

        imageId = getResources().getIdentifier(novascore, "drawable", getContext().getPackageName());
        nova.setImageResource(imageId);

        if(object.getProduct().getEcoscore()==null)
            ecoscore = "ecoscore";
        else ecoscore = "eco_" + object.getProduct().getEcoscore();

        imageId = getResources().getIdentifier(ecoscore, "drawable", getContext().getPackageName());
        eco.setImageResource(imageId);

        String level = object.getProduct().getLevels().getFat();
        CardView cardView = view.findViewById(R.id.fat);
        setLevelColor(level, cardView);

        TextView textView = view.findViewById(R.id.fatvalue);
        if(level!=null)
            text = object.getProduct().getNutriments().getFat_100g() + " g Fat"+"\n"+"in "+level+" quantity";
        else
            text = object.getProduct().getNutriments().getFat_100g() + " g Fat"+"\n";
        textView.setText(text);

        level = object.getProduct().getLevels().getSaturated_fat();
        cardView = view.findViewById(R.id.trans);
        setLevelColor(level, cardView);

        textView = view.findViewById(R.id.transvalue);
        if(level!=null)
            text = object.getProduct().getNutriments().getSaturated_fat_100g() + " g Saturated fat"+"\n"+"in "+level+" quantity";
        else text = object.getProduct().getNutriments().getSaturated_fat_100g() + " g Saturated fat"+"\n";
        textView.setText(text);

        level = object.getProduct().getLevels().getSugars();
        cardView = view.findViewById(R.id.sugar);
        setLevelColor(level, cardView);

        textView = view.findViewById(R.id.sugarvalue);
        if(level!=null)
            text = object.getProduct().getNutriments().getSugars_100g() + " g Sugars"+"\n"+"in "+level+" quantity";
        else
            text = object.getProduct().getNutriments().getSugars_100g() + " g Sugars"+"\n";
        textView.setText(text);

        level = object.getProduct().getLevels().getSalt();
        cardView = view.findViewById(R.id.salt);
        setLevelColor(level, cardView);

        textView = view.findViewById(R.id.saltvalue);
        if (level!=null)
            text = object.getProduct().getNutriments().getSalt_100g() + " g Salt"+"\n"+"in "+level+" quantity";
        else
            text = object.getProduct().getNutriments().getSalt_100g() + " g Salt"+"\n";
        textView.setText(text);


        if (object.getProduct().getLabels()!=null)
        {
            text="\n" + "\n" + "Labels: ";
            for(String elem : object.getProduct().getLabels())
                text+=elem.substring(3) + ", ";
            text = text.substring(0, text.length() - 2);
            labels.setText(text);
        }

        return view;
    }

    public void setLevelColor(String level, CardView cardView) {

        if (level != null) {
            switch (level) {
                case "low":
                    cardView.setCardBackgroundColor(Color.parseColor("#50c878"));
                    break;
                case "moderate":
                    cardView.setCardBackgroundColor(Color.parseColor("#f0e130"));
                    break;
                case "high":
                    cardView.setCardBackgroundColor(Color.parseColor("#cf352e"));
                    break;
            }
        }
    }
}
