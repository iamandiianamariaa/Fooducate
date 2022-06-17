package com.example.fooducate.adapters;

import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fooducate.R;
import com.example.fooducate.models.SwipeHomeModel;

import java.util.ArrayList;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ViewHolder>  {
    ArrayList<SwipeHomeModel> list;

    public HomeAdapter(ArrayList<SwipeHomeModel> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.swipe_view, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        SwipeHomeModel model = list.get(position);
        holder.tvTitle.setText(model.getTitle());
        holder.tvDesc.setText(model.getDescription());
        holder.imgArticle.setImageResource(model.getImg());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public ImageView imgArticle;
        public TextView tvTitle,tvDesc;
        public Button btReadMore;
        public Button btShare;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imgArticle  = itemView.findViewById(R.id.imgArticle);
            tvTitle     = itemView.findViewById(R.id.tvTitle);
            tvDesc      = itemView.findViewById(R.id.tvDesc);
            btReadMore  = itemView.findViewById(R.id.btReadMore);
            btShare = itemView.findViewById(R.id.btShare);
            btReadMore.setOnClickListener(this);
            btShare.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int position =  getAdapterPosition();
            Uri uri;
            Intent intent;
            Intent shareIntent = new Intent();
            switch (v.getId()){
                case R.id.btReadMore:
                    switch (position){
                        case 0:
                        case 1:
                            uri = Uri.parse("https://nutriscore.colruytgroup.com/colruytgroup/en/about-nutri-score/");
                            intent = new Intent(Intent.ACTION_VIEW, uri);
                            v.getContext().startActivity(intent);
                            break;
                        case 2:
                            uri = Uri.parse("https://www.dietsensor.com/guide-to-understanding-the-nutri-score-and-nova-classification/");
                            intent = new Intent(Intent.ACTION_VIEW, uri);
                            v.getContext().startActivity(intent);
                            break;
                        case 3:
                            uri = Uri.parse("https://www.foodnavigator.com/Article/2021/01/12/Eco-Score-New-FOP-label-measures-the-environmental-impact-of-food");
                            intent = new Intent(Intent.ACTION_VIEW, uri);
                            v.getContext().startActivity(intent);
                            break;

                    }
                case R.id.btShare:
                    switch (position){
                        case 0:
                        case 1:
                            shareIntent.setType("text/plain");
                            shareIntent.setAction(Intent.ACTION_SEND);
                            shareIntent.putExtra(Intent.EXTRA_SUBJECT, "Find more about NutriScore");
                            shareIntent.putExtra(Intent.EXTRA_TEXT, "https://nutriscore.colruytgroup.com/colruytgroup/en/about-nutri-score/");
                            v.getContext().startActivity(Intent.createChooser(shareIntent, null));
                            break;
                        case 2:
                            shareIntent.setType("text/plain");
                            shareIntent.setAction(Intent.ACTION_SEND);
                            shareIntent.putExtra(Intent.EXTRA_SUBJECT, "Find more about NovaScore");
                            shareIntent.putExtra(Intent.EXTRA_TEXT, "https://www.dietsensor.com/guide-to-understanding-the-nutri-score-and-nova-classification/");
                            v.getContext().startActivity(Intent.createChooser(shareIntent, null));
                            break;
                        case 3:
                            shareIntent.setType("text/plain");
                            shareIntent.setAction(Intent.ACTION_SEND);
                            shareIntent.putExtra(Intent.EXTRA_SUBJECT, "Find more about Ecoscore");
                            shareIntent.putExtra(Intent.EXTRA_TEXT, "https://www.foodnavigator.com/Article/2021/01/12/Eco-Score-New-FOP-label-measures-the-environmental-impact-of-food");
                            v.getContext().startActivity(Intent.createChooser(shareIntent, null));
                            break;

                    }
            }
        }
    }
}
