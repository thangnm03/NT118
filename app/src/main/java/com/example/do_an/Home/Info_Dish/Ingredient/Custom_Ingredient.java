package com.example.do_an.Home.Info_Dish.Ingredient;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.do_an.R;

import java.util.ArrayList;

public class Custom_Ingredient extends RecyclerView.Adapter<Custom_Ingredient.ViewHolder> {

    private ArrayList<RecipeIngredient> mData;

    public Custom_Ingredient(ArrayList<RecipeIngredient> data) {
        this.mData = data;
    }

    // ViewHolder cho item
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textView_ingredientName;
        public TextView textView_quantity;

        public ViewHolder(View itemView) {
            super(itemView);
            textView_ingredientName = itemView.findViewById(R.id.tv_ingredient);
            textView_quantity = itemView.findViewById(R.id.tv_amount);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_ingredient, parent, false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        RecipeIngredient item = mData.get(position);

        holder.textView_ingredientName.setText(item.getIngredientName());
        holder.textView_quantity.setText(item.getQuantity());
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }
}
