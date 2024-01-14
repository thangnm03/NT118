package com.example.do_an.Home.Search;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.do_an.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Fragment_Search_History#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Fragment_Search_History extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private RecyclerView recyclerView;
    private Custom_History historyAdapter;
    private List<String> searchHistoryList = new ArrayList<>();

    public Fragment_Search_History() {
        // Required empty public constructor
    }

    public static Fragment_Search_History newInstance(String param1, String param2) {
        Fragment_Search_History fragment = new Fragment_Search_History();
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

        recyclerView = view.findViewById(R.id.recy_home);
        loadSearchHistoryFromSQLite();
        historyAdapter = new Custom_History(getContext(), searchHistoryList);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());

        layoutManager.setReverseLayout(true);
        layoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(layoutManager);
        layoutManager.setStackFromEnd(true);

        recyclerView.setAdapter(historyAdapter);
    }

    private void loadSearchHistoryFromSQLite() {

        SearchHistoryDbHelper dbHelper = new SearchHistoryDbHelper(requireContext());
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String[] projection = {SearchHistoryDbHelper.COLUMN_SEARCH_TERM};
        Cursor cursor = db.query(
                SearchHistoryDbHelper.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                null
        );

        if (cursor != null) {
            while (cursor.moveToNext()) {
                String searchTerm = cursor.getString(cursor.getColumnIndexOrThrow(SearchHistoryDbHelper.COLUMN_SEARCH_TERM));
                searchHistoryList.add(searchTerm);
            }
            cursor.close();
        }

        db.close();
        dbHelper.close();
    }
}