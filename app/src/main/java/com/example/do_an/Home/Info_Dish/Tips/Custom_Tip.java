package com.example.do_an.Home.Info_Dish.Tips;

import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.do_an.Home.Categorys.Category.Custom_Dish;
import com.example.do_an.R;

import java.util.ArrayList;

public class Custom_Tip extends RecyclerView.Adapter<Custom_Tip.ViewHolder> {

    private ArrayList<RecipeTip> mData;

    public Custom_Tip(ArrayList<RecipeTip> data) {
        this.mData = data;
    }

    // ViewHolder cho item
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textView_Name;
        public TextView textView_Tip;
        public ImageView imageView_avt;
        public ToggleButton toggleButton_fav;
        public TextView textView_favNumber;

        public ViewHolder(View itemView) {
            super(itemView);
            textView_Name = itemView.findViewById(R.id.tv_name_tip);
            textView_Tip = itemView.findViewById(R.id.tv_tip);
            imageView_avt = itemView.findViewById(R.id.im_avt_tip);
            toggleButton_fav = itemView.findViewById(R.id.tb_fav_tip);
            textView_favNumber = itemView.findViewById(R.id.tv_number_fav_tip);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_tip_activity, parent, false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        RecipeTip item = mData.get(position);

        holder.textView_Name.setText(item.getName());
        holder.textView_Tip.setText(item.getTip());
        holder.textView_favNumber.setText(item.getLikes());

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }
}
