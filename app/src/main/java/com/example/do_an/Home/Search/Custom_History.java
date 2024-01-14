package com.example.do_an.Home.Search;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;

import androidx.recyclerview.widget.RecyclerView;

import com.example.do_an.R;

import java.util.List;

public class Custom_History extends RecyclerView.Adapter<Custom_History.ViewHolder> {

    private List<String> mData;
    private Context context;

    public Custom_History(Context context,List<String> mData){
        this.mData = mData;
        this.context = context;
    }

    // ViewHolder cho item
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textView_History;
        public ImageView imageView_History;

        public ViewHolder(View itemView) {
            super(itemView);
            textView_History = itemView.findViewById(R.id.tv_search_history);
            imageView_History = itemView.findViewById(R.id.img_search_history);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_search_history, parent, false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        String item = mData.get(position);

        holder.textView_History.setText(item);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }
}
