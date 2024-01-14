package com.example.do_an.Profile;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.do_an.R;

import java.util.List;

public class saved_recipesAdapter extends RecyclerView.Adapter<saved_recipesAdapter.saved_recipesViewHolder> {

    private Fragment_saved_recipes mContext;
    private List<saved_recipes> mListSavedRecipes;

    public saved_recipesAdapter(Fragment_saved_recipes mContext) {
        this.mContext = mContext;
    }

    public void setData(List<saved_recipes> list){
        this.mListSavedRecipes=list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public saved_recipesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recipes, parent, false);
        return new saved_recipesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull saved_recipesViewHolder holder, int position) {
        saved_recipes save_recipes = mListSavedRecipes.get(position);
        if (save_recipes == null){
            return;
        }
        holder.imgMeal.setImageResource(save_recipes.getResourceImage());
        holder.tvName.setText(save_recipes.getName());
    }

    @Override
    public int getItemCount() {
        if (mListSavedRecipes != null){
            return mListSavedRecipes.size();
        }
        return 0;
    }

    public class saved_recipesViewHolder extends RecyclerView.ViewHolder{

        private ImageView imgMeal;
        private TextView tvName;
        public saved_recipesViewHolder(@NonNull View itemView) {
            super(itemView);

            imgMeal = itemView.findViewById(R.id.img_meal);
            tvName = itemView.findViewById(R.id.tv_name);

        }
    }
}
