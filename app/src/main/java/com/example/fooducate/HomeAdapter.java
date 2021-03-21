package com.example.fooducate;

import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

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

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imgArticle  = itemView.findViewById(R.id.imgArticle);
            tvTitle     = itemView.findViewById(R.id.tvTitle);
            tvDesc      = itemView.findViewById(R.id.tvDesc);
            btReadMore  = itemView.findViewById(R.id.btReadMore);
            btReadMore.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int position =  getAdapterPosition();
            Uri uri;
            Intent intent;
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
        }
    }
}
