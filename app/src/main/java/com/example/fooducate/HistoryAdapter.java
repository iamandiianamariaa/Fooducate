package com.example.fooducate;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.ViewHolder> {
    private ArrayList<HistoryModel>products;
    private OnProductListener mOnProductListener;

    public HistoryAdapter(ArrayList<HistoryModel> products, OnProductListener onProductListener) {
        this.products = products;
        this.mOnProductListener = onProductListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_view_history, parent, false);
        return new ViewHolder(view, mOnProductListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        HistoryModel historyModel = products.get(position);

        Picasso.get().load(historyModel.getProductImage()).into(holder.productImage);
        Picasso.get().load(historyModel.getNutriImage()).into(holder.nutriImage);
        holder.title.setText(historyModel.getTitle());
        holder.description.setText(historyModel.getDescription());

        Date today = new Date();
        long diff =  today.getTime() - historyModel.getScanDate().getTime();
        int numOfDays = (int) (diff / (1000 * 60 * 60 * 24));
        int hours = (int) (diff / (1000 * 60 * 60));
        int minutes = (int) (diff / (1000 * 60));
        int seconds = (int) (diff / (1000));
        if(numOfDays!=0)
        {
            String text = numOfDays+" days ago";
            holder.date.setText(text);
        }
        else if (hours!=0)
        {
            String text = hours+" hours ago";
            holder.date.setText(text);
        }
        else if (minutes!=0){
            String text = minutes+" minutes ago";
            holder.date.setText(text);
        }
        else {
            String text = seconds+" seconds ago";
            holder.date.setText(text);
        }

    }

    @Override
    public int getItemCount() {

        return products.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        ImageView productImage, nutriImage, time;
        TextView title, description, date;
        OnProductListener mOnProductListener;

        public ViewHolder(@NonNull View itemView, OnProductListener onProductListener) {

            super(itemView);

            productImage = itemView.findViewById(R.id.productimg);
            nutriImage = itemView.findViewById(R.id.nutriimg);
            time = itemView.findViewById(R.id.time);
            date = itemView.findViewById(R.id.days);
            title = itemView.findViewById(R.id.productTitle);
            description = itemView.findViewById(R.id.productBrand);
            mOnProductListener = onProductListener;

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            mOnProductListener.onProductClick(getAdapterPosition());
        }
    }

    public interface OnProductListener{
        void onProductClick(int position);
    }
}
