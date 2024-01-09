package com.example.do_an.MealPlan;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.do_an.R;

import java.util.ArrayList;


public class Fragment_meal_plan extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private RecyclerView recyclerViewBreakfast;
    private RecyclerView recyclerViewLunch;
    private RecyclerView recyclerViewDinner;

    public Fragment_meal_plan() {
        // Required empty public constructor
    }

    public static Fragment_meal_plan newInstance(String param1, String param2) {
        Fragment_meal_plan fragment = new Fragment_meal_plan();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_meal_plan, container, false);

        recyclerViewBreakfast = rootView.findViewById(R.id.recy_break);
        recyclerViewLunch = rootView.findViewById(R.id.recy_lunch);
        recyclerViewDinner = rootView.findViewById(R.id.recy_dinner);



        return rootView;
    }

}