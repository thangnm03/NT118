package com.example.do_an.Profile;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.do_an.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Fragment_saved_recipes#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Fragment_saved_recipes extends Fragment {


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    public Fragment_saved_recipes() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Fragment_saved_recipes.
     */
    // TODO: Rename and change types and number of parameters
    public static Fragment_saved_recipes newInstance(String param1, String param2) {
        Fragment_saved_recipes fragment = new Fragment_saved_recipes();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            // TODO: Rename and change types of parameters
            String mParam1 = getArguments().getString(ARG_PARAM1);
            String mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
//         Inflate the layout for this fragment
//        RecyclerView rcvSavedRecipes = getView().findViewById(R.id.rcv_saved_recipes);
//        saved_recipesAdapter msaved_recipesAdapter = new saved_recipesAdapter(this);
//
//        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);
//        rcvSavedRecipes.setLayoutManager(gridLayoutManager);
//
//        msaved_recipesAdapter.setData(getListSavedRecipes());
//        rcvSavedRecipes.setAdapter(msaved_recipesAdapter);

        return inflater.inflate(R.layout.fragment_saved_recipes, container, false);
    }
//    private List<saved_recipes> getListSavedRecipes() {
//        List<saved_recipes> list = new ArrayList<>();
//        list.add(new saved_recipes(R.drawable.sample_dish, "sample"));
//        list.add(new saved_recipes(R.drawable.sample_dish, "sample"));
//        return list;
//    }

}