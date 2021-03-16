package com.example.fooducate;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecommendationAdapter extends RecyclerView.Adapter<RecommendationAdapter.RecommendationViewHolder> {

    ArrayList<RecommendationHelperClass> recommendations;

    public RecommendationAdapter(ArrayList<RecommendationHelperClass> recommendations) {
        this.recommendations = recommendations;
    }

    @NonNull
    @Override
    public RecommendationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view, parent, false);
        RecommendationViewHolder recommendationViewHolder = new RecommendationViewHolder(view);
        return recommendationViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecommendationViewHolder holder, int position) {
        RecommendationHelperClass recommendationHelperClass = recommendations.get(position);

        holder.image.setImageResource(recommendationHelperClass.getImage());
        holder.title.setText(recommendationHelperClass.getTitle());
        holder.description.setText(recommendationHelperClass.getDescription());
    }

    @Override
    public int getItemCount() {
        return recommendations.size();
    }

    public static class RecommendationViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView title, description;

        public RecommendationViewHolder(@NonNull View itemView){
            super(itemView);

            image = itemView.findViewById(R.id.featured_image);
            title = itemView.findViewById(R.id.featured_title);
            description = itemView.findViewById(R.id.featured_desc);
        }

    }
}
