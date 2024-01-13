package com.example.do_an.Home.Categorys;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.AsyncListUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.do_an.Home.Categorys.Category.FoodItem;
import com.example.do_an.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;


public class Fragment_Categorys extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private RecyclerView recyclerView_category;
    private Custom_Categorys customCategorys;

    private MutableLiveData<List<MenuCategory>> liveData = new MutableLiveData<>();

    private String mParam1;
    private String mParam2;

    public Fragment_Categorys() {
        // Required empty public constructor
    }

    public static Fragment_Categorys newInstance(String param1, String param2) {
        Fragment_Categorys fragment = new Fragment_Categorys();
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
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment__categorys, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView_category = view.findViewById(R.id.recy_home);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView_category.setLayoutManager(layoutManager);
        recyclerView_category.setHasFixedSize(true);

        customCategorys = new Custom_Categorys(getContext());
        liveData.observe(getViewLifecycleOwner(), new Observer<List<MenuCategory>>() {
            @Override
            public void onChanged(List<MenuCategory> data) {
                customCategorys.setmData(data);
                recyclerView_category.setAdapter(customCategorys);
            }
        });

        getSelectedData();
    }

    private List<MenuCategory> getSelectedData() {
        List<MenuCategory> selectedData = new ArrayList<>();

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("meals");

        Query query_Beef = databaseReference.orderByChild("strCategory").equalTo("Beef");
        query_Beef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                List<FoodItem> beefMeals = new ArrayList<>();
                int count = 0;

                for (DataSnapshot mealSnapshot : dataSnapshot.getChildren()) {
                    FoodItem foodItem = parseMeal(mealSnapshot);
                    beefMeals.add(foodItem);
                    if (++count>=10) break;
                }


                selectedData.add(new MenuCategory ("Beef", beefMeals));
                liveData.setValue(selectedData);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.err.println("Error: " + databaseError.getMessage());
            }
        });

        Query query_Chicken = databaseReference.orderByChild("strCategory").equalTo("Chicken");
        query_Chicken.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                List<FoodItem> beefMeals = new ArrayList<>();
                int count = 0;
                for (DataSnapshot mealSnapshot : dataSnapshot.getChildren()) {

                    FoodItem foodItem = parseMeal(mealSnapshot);
                    beefMeals.add(foodItem);

                    if (++count>=10) break;
                }
                selectedData.add(new MenuCategory ("Chicken", beefMeals));
                liveData.setValue(selectedData);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.err.println("Error: " + databaseError.getMessage());
            }
        });

        Query query_Vegetarian = databaseReference.orderByChild("strCategory").equalTo("Vegetarian");
        query_Vegetarian.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                List<FoodItem> beefMeals = new ArrayList<>();
                int count = 0;

                for (DataSnapshot mealSnapshot : dataSnapshot.getChildren()) {
                    FoodItem foodItem = parseMeal(mealSnapshot);
                    beefMeals.add(foodItem);

                    if (++count>=10) break;
                }
                selectedData.add(new MenuCategory ("Vegetarian", beefMeals));
                liveData.setValue(selectedData);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.err.println("Error: " + databaseError.getMessage());
            }
        });

        Query query_Thai = databaseReference.orderByChild("strArea").equalTo("French");
        query_Thai.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                List<FoodItem> beefMeals = new ArrayList<>();
                int count = 0;

                for (DataSnapshot mealSnapshot : dataSnapshot.getChildren()) {
                    FoodItem foodItem = parseMeal(mealSnapshot);
                    beefMeals.add(foodItem);
                    if (++count>=10) break;
                }
                selectedData.add(new MenuCategory ("French", beefMeals));
                liveData.setValue(selectedData);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.err.println("Error: " + databaseError.getMessage());
            }
        });

        Query query_Italian = databaseReference.orderByChild("strArea").equalTo("Italian");
        query_Italian.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                List<FoodItem> beefMeals = new ArrayList<>();
                int count =0;

                for (DataSnapshot mealSnapshot : dataSnapshot.getChildren()) {
                    FoodItem foodItem = parseMeal(mealSnapshot);
                    beefMeals.add(foodItem);

                    if (++count>=10) break;
                }
                selectedData.add(new MenuCategory ("Italian", beefMeals));
                liveData.setValue(selectedData);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.err.println("Error: " + databaseError.getMessage());
            }
        });

        Query query_Chinese = databaseReference.orderByChild("strArea").equalTo("Chinese");
        query_Chinese.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                List<FoodItem> beefMeals = new ArrayList<>();
                int count =0;

                for (DataSnapshot mealSnapshot : dataSnapshot.getChildren()) {
                    FoodItem foodItem = parseMeal(mealSnapshot);
                    beefMeals.add(foodItem);

                    if (++count>=10) break;
                }
                selectedData.add(new MenuCategory ("Chinese", beefMeals));
                liveData.setValue(selectedData);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.err.println("Error: " + databaseError.getMessage());
            }
        });


        return selectedData;
    }
    private static FoodItem parseMeal(DataSnapshot dataSnapshot) {

        String idMeal = dataSnapshot.child("idMeal").getValue(String.class);
        int likeCount = dataSnapshot.child("like_count").getValue(Integer.class);
        String strMeal = dataSnapshot.child("strMeal").getValue(String.class);
        String strMealThumb = dataSnapshot.child("strMealThumb").getValue(String.class);

        return new FoodItem(idMeal, strMeal, strMealThumb,likeCount);
    }

    private List<MenuCategory> exdata(){
        List<MenuCategory> selectedData = new ArrayList<>();

        List<FoodItem> beefMeals = new ArrayList<>();
        beefMeals.add(new FoodItem("1","1","https://www.themealdb.com/images/media/meals/wxywrq1468235067.jpg",0));
        beefMeals.add(new FoodItem("1","1","https://www.themealdb.com/images/media/meals/wxywrq1468235067.jpg",0));
        beefMeals.add(new FoodItem("1","1","https://www.themealdb.com/images/media/meals/wxywrq1468235067.jpg",0));
        beefMeals.add(new FoodItem("1","1","https://www.themealdb.com/images/media/meals/wxywrq1468235067.jpg",0));
        beefMeals.add(new FoodItem("1","1","https://www.themealdb.com/images/media/meals/wxywrq1468235067.jpg",0));
        beefMeals.add(new FoodItem("1","1","https://www.themealdb.com/images/media/meals/wxywrq1468235067.jpg",0));

        selectedData.add(new MenuCategory("1",beefMeals));
        selectedData.add(new MenuCategory("2",beefMeals));
        selectedData.add(new MenuCategory("3",beefMeals));
        selectedData.add(new MenuCategory("4",beefMeals));




        return selectedData;

    }
}
