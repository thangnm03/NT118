package com.example.do_an.Navigation;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.do_an.Home.Fragment_Home;
import com.example.do_an.MealPlan.Fragment_meal_plan;
import com.example.do_an.Profile.Fragment_profile;

public class ViewPagerAdapter extends FragmentStatePagerAdapter {

    private String id_meal;
    private OnItemClickListener clickListener;


    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public ViewPagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new Fragment_Home();
            case 1:
                return new Fragment_meal_plan();
            case 2:
                return new Fragment_profile();
            default:
                return new Fragment_Home();
        }
    }

    @Override
    public int getCount() {
        return 3;
    }
}
