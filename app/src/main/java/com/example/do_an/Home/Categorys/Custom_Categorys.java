    package com.example.do_an.Home.Categorys;

    import android.content.Context;
    import android.view.LayoutInflater;
    import android.view.View;
    import android.view.ViewGroup;
    import android.widget.TextView;

    import androidx.recyclerview.widget.LinearLayoutManager;
    import androidx.recyclerview.widget.RecyclerView;

    import com.example.do_an.Home.Categorys.Category.Custom_Dish;
    import com.example.do_an.R;

    import java.util.ArrayList;
    import java.util.List;

    public class Custom_Categorys extends RecyclerView.Adapter<Custom_Categorys.ViewHolder> {

        Context context;
        private List<MenuCategory> mData;

        public Custom_Categorys (Context context){
            this.context=context;
        }
        public void setmData(List<MenuCategory> mData) {
            this.mData = mData;
            notifyDataSetChanged();
        }

        public static class ViewHolder extends RecyclerView.ViewHolder {
            public TextView textView_Category;
            public TextView textView_See_All;
            public RecyclerView list_20;

            public ViewHolder(View itemView) {
                super(itemView);
                textView_Category = itemView.findViewById(R.id.tv_category);
                textView_See_All = itemView.findViewById(R.id.tv_see_all);
                list_20 = itemView.findViewById(R.id.list_dish_20);
            }
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_category, parent, false);
            return new ViewHolder(view);
        }


        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            MenuCategory item = mData.get(position);

            holder.textView_Category.setText(item.getCategoryName());

            LinearLayoutManager layoutManager = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
            holder.list_20.setLayoutManager(layoutManager);

            Custom_Dish customDishAdapter = new Custom_Dish();
            customDishAdapter.setmData(item.getFoodItems());
            holder.list_20.setAdapter(customDishAdapter);

        }

        @Override
        public int getItemCount() {
            return mData.size();
        }
    }
