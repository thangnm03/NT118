package com.example.do_an.Home.Info_Dish.Step;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.do_an.R;

import java.util.ArrayList;

public class Custom_Step extends RecyclerView.Adapter<Custom_Step.ViewHolder> {

    private ArrayList<RecipeStep> mData;

    public Custom_Step(ArrayList<RecipeStep> data) {
        this.mData = data;
    }

    // ViewHolder cho item
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textView_stepNumber;
        public TextView textView_stepDescription;

        public ViewHolder(View itemView) {
            super(itemView);
            textView_stepNumber = itemView.findViewById(R.id.tv_step_number);
            textView_stepDescription = itemView.findViewById(R.id.tv_Description);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_step, parent, false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        RecipeStep item = mData.get(position);

        holder.textView_stepNumber.setText(item.getStepNumber());
        holder.textView_stepDescription.setText(item.getStepDescription());
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }
}
