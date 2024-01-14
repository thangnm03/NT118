package com.example.do_an.Home.Search;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;

import androidx.recyclerview.widget.RecyclerView;

import com.example.do_an.Home.Categorys.Category.Custom_Dish;
import com.example.do_an.Home.Categorys.Category.FoodItem;
import com.example.do_an.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class Custom_Result extends RecyclerView.Adapter<Custom_Result.ViewHolder> {

    Context context;
    private List<FoodItem> mData;

    public Custom_Result (Context context){
        this.context=context;
    }

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
            textView_Name = itemView.findViewById(R.id.name_dish);
            imageView_Dish = itemView.findViewById(R.id.img_meal);
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

        Picasso.get().load(item.getImageResource()).into(holder.imageView_Dish);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }
}
