package com.example.fooducate;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.ViewHolder> {
    private ArrayList<HistoryModel>products;

    public HistoryAdapter(ArrayList<HistoryModel> products) {
        this.products = products;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_view_history, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        HistoryModel historyModel = products.get(position);

        holder.productImage.setImageResource(historyModel.getProductImage());
        holder.nutriImage.setImageResource(historyModel.getNutriImage());
        holder.time.setImageResource(historyModel.getTime());
        holder.title.setText(historyModel.getTitle());
        holder.description.setText(historyModel.getDescription());

    }

    @Override
    public int getItemCount() {

        return products.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView productImage, nutriImage, time;
        TextView title, description;

        public ViewHolder(@NonNull View itemView) {

            super(itemView);

            productImage = itemView.findViewById(R.id.productimg);
            nutriImage = itemView.findViewById(R.id.nutriimg);
            time = itemView.findViewById(R.id.time);
            title = itemView.findViewById(R.id.productTitle);
            description = itemView.findViewById(R.id.productBrand);
        }
    }
}
