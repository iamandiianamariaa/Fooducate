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

public class Summary extends Fragment {
    private ResponseObject object;

    public Summary(ResponseObject object) {
        this.object = object;
    }

    public Summary(int contentLayoutId, ResponseObject object) {
        super(contentLayoutId);
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

        String nutriscore;
        String content = "";
        title.setText(object.getProduct().getName());
        brand.setText(object.getProduct().getCompany());
        Picasso.get().load(object.getProduct().getImages().getFront().getDisplay().getUrl()).into(productImg);

        if(object.getProduct().getNutriscore() == null)
            nutriscore = "nutri";
        else nutriscore = "nutri_" + object.getProduct().getNutriscore();

        int imageId = getResources().getIdentifier(nutriscore, "drawable", getContext().getPackageName());
        nutri.setImageResource(imageId);

        //if(object.getProduct().getNova())
            nutriscore = "nutri";
        //else nutriscore = "nutri_" + object.getProduct().getNutriscore();

        imageId = getResources().getIdentifier(nutriscore, "drawable", getContext().getPackageName());
        nova.setImageResource(imageId);

        if(object.getProduct().getIngredients()!=null)
            content += "Ingredients: " + object.getProduct().getIngredients() + "\n";
        quantity.setText(object.getProduct().getQuantity());
        content += "SERVING SIZE: " + object.getProduct().getServing_size() + "\n";
        content += "NOVA: " + object.getProduct().getNova() + "\n";
        content += "NUTRISCORE: " + object.getProduct().getNutriscore() + "\n";
        System.out.println(object.getProduct().getBarcode());
        if(object.getProduct().getEcoscore()!=null)
            content += "ECOSCORE: " + object.getProduct().getEcoscore() + "\n";
        if(object.getProduct().getAllergens()!=null)
            content += "ALLERGENS: " + object.getProduct().getAllergens() + "\n";
//                    for(String elem : product.getProduct().getAdditives())
//                        content += "ADDITIVES: " + elem + "\n";
//                    for(String elem : product.getProduct().getLabels())
//                        content += "LABELS: " + elem + "\n";
//                    for(String elem : product.getProduct().getAnalysis())
//                        content += "ANALYSIS: " + elem + "\n";
        if(object.getProduct().getLevels()!=null)
        {
            content += "FAT: " + object.getProduct().getLevels().getFat() + "\n";
            content += "SALT: " + object.getProduct().getLevels().getSalt() + "\n";
            content += "SUGAR: " + object.getProduct().getLevels().getSugars() + "\n";
            content += "SATURATED FAT: " + object.getProduct().getLevels().getSaturated_fat() + "\n";
        }
        content += "NUTRIMENTS: " + object.getProduct().getNutriments().getCarbo_100g() + "\n";
        content += "NUTRIMENTS: " + object.getProduct().getNutriments().getCarbo_serving() + "\n";
        //text.append(content);
        return view;
    }
}
