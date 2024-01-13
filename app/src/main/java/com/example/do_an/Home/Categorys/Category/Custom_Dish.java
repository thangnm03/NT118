package com.example.do_an.Home.Categorys.Category;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;

import androidx.recyclerview.widget.RecyclerView;

import com.example.do_an.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class Custom_Dish extends RecyclerView.Adapter<Custom_Dish.ViewHolder> {

    private List<FoodItem> mData;

    public void setmData(List<FoodItem> mData) {
        this.mData = mData;
        notifyDataSetChanged();
    }

    // ViewHolder cho item
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textView_Name;
        public ImageView imageView_Dish;
        public ToggleButton toggleButton_fav;

        public ViewHolder(View itemView) {
            super(itemView);
            textView_Name = itemView.findViewById(R.id.name_dish_home);
            imageView_Dish = itemView.findViewById(R.id.img_meal_home);
            toggleButton_fav = itemView.findViewById(R.id.toggle_fav_home);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_meal_home, parent, false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        FoodItem item = mData.get(position);

        holder.textView_Name.setText(item.getFoodName());

        Picasso.get().load(item.getImageResource()+"/preview").into(holder.imageView_Dish);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }
}
