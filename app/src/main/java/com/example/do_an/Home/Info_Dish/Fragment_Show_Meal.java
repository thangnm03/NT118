package com.example.do_an.Home.Info_Dish;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.do_an.Home.Categorys.Category.FoodItem;
import com.example.do_an.R;


public class Fragment_Show_Meal extends Fragment {

    private static final String ARG_IDFood = "ID_Food";
    private String id_meal;

    public Fragment_Show_Meal() {
        // Required empty public constructor
    }


    public static Fragment_Show_Meal newInstance(String id) {
        Fragment_Show_Meal fragment  = new Fragment_Show_Meal();
        Bundle args = new Bundle();
        args.putString(ARG_IDFood, id);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            id_meal = getArguments().getString(ARG_IDFood);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment__show__meal, container, false);
    }

    public void onCustomToggleClick(View view) {
    }
}