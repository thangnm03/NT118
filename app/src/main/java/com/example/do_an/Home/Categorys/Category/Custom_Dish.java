package com.example.do_an.Home.Categorys.Category;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;

import androidx.recyclerview.widget.RecyclerView;

import com.example.do_an.R;

import java.util.ArrayList;

public class Custom_Dish extends RecyclerView.Adapter<Custom_Dish.ViewHolder> {

    private ArrayList<FoodItem> mData;

    public Custom_Dish(ArrayList<FoodItem> data) {
        this.mData = data;
    }

    // ViewHolder cho item
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textView_Name;
        public ImageView imageView_Dish;
        public ToggleButton toggleButton_fav;

        public ViewHolder(View itemView) {
            super(itemView);
            textView_Name = itemView.findViewById(R.id.img_meal);
            imageView_Dish = itemView.findViewById(R.id.tx_dish);
            toggleButton_fav = itemView.findViewById(R.id.toggle_fav);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_meal, parent, false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        FoodItem item = mData.get(position);

        holder.textView_Name.setText(item.getFoodName());
        holder.imageView_Dish.setVisibility(View.VISIBLE);

        //holder.toggleButton_fav.setVisibility(View.VISIBLE);

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }
}
