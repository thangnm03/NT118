package com.example.do_an.Home.Categorys.Category;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.do_an.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class Custom_Top extends RecyclerView.Adapter<Custom_Top.TopViewHolder> {

    private List<FoodItem> mData;

    public Custom_Top (List<FoodItem> mData){
        this.mData = mData;
    }

    @NonNull
    @Override
    public TopViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_img_top,parent,false);
            return new TopViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TopViewHolder holder, int position) {
        FoodItem item = mData.get(position);


        Picasso.get().load(item.getImageResource()+"/preview").into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        if(mData!=null)
            return mData.size();
        return 0;
    }

    public class TopViewHolder extends RecyclerView.ViewHolder{

        private ImageView imageView;
        public TopViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView =imageView.findViewById(R.id.img_top);
        }
    }
}
