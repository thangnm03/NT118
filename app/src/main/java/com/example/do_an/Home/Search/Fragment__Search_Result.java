package com.example.do_an.Home.Search;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.do_an.Home.Categorys.Category.FoodItem;
import com.example.do_an.Home.Categorys.Custom_Categorys;
import com.example.do_an.Home.Categorys.MenuCategory;
import com.example.do_an.Home.DataParser;
import com.example.do_an.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class Fragment__Search_Result extends Fragment {

    private static final String ARG_SEARCH_TEXT = "searchText";
    private String searchQuery;
    private RecyclerView recyclerView;
    private Custom_Result resultAdapter;
    private List<FoodItem> foodItems ;

    private MutableLiveData<List<FoodItem>> liveData ;

    public Fragment__Search_Result() {

    }


    public static Fragment__Search_Result newInstance(String searchText) {
        Fragment__Search_Result fragment = new Fragment__Search_Result();
        Bundle args = new Bundle();
        args.putString(ARG_SEARCH_TEXT,searchText);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            searchQuery = getArguments().getString(ARG_SEARCH_TEXT);
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

        foodItems = new ArrayList<FoodItem>();
        liveData = new MutableLiveData<>();
        recyclerView = view.findViewById(R.id.recy_home);

        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 2);
        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setHasFixedSize(true);

        resultAdapter = new Custom_Result(getContext());

        resultAdapter.setmData(foodItems);
        recyclerView.setAdapter(resultAdapter);
        liveData.observe(getViewLifecycleOwner(), new Observer<List<FoodItem>>() {
            @Override
            public void onChanged(List<FoodItem> data) {
                resultAdapter.setmData(data);
            }
        });

        getSelectedData();
        //exdata();


    }

    private void exdata() {
        foodItems.add(new FoodItem("1","1","https://www.themealdb.com/images/media/meals/wxywrq1468235067.jpg",0));
        foodItems.add(new FoodItem("1","1","https://www.themealdb.com/images/media/meals/wxywrq1468235067.jpg",0));

        foodItems.add(new FoodItem("1","1","https://www.themealdb.com/images/media/meals/wxywrq1468235067.jpg",0));

        foodItems.add(new FoodItem("1","1","https://www.themealdb.com/images/media/meals/wxywrq1468235067.jpg",0));

        foodItems.add(new FoodItem("1","1","https://www.themealdb.com/images/media/meals/wxywrq1468235067.jpg",0));

        foodItems.add(new FoodItem("1","1","https://www.themealdb.com/images/media/meals/wxywrq1468235067.jpg",0));
        liveData.setValue(foodItems);

    }

    private void getSelectedData() {
        DatabaseReference foodsRef = FirebaseDatabase.getInstance().getReference("meals");

        Query query = foodsRef.orderByChild("strMeal").startAt(searchQuery).endAt(searchQuery + "\uf8ff");

        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                int count = 0;
                for (DataSnapshot mealSnapshot : dataSnapshot.getChildren()) {
                    FoodItem foodItem = DataParser.parseMeal(mealSnapshot);
                    foodItems.add(foodItem);
                    if (count %10 ==0) liveData.setValue(foodItems);
                    count++;
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                System.err.println("Error: " + databaseError.getMessage());
            }
        });
    }

}