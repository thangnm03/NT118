package com.example.do_an.Home;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.do_an.Home.Categorys.Fragment_Categorys;
import com.example.do_an.R;

public class Fragment_Home extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private ImageButton imgExit, imgFilter, imgClear;
    private EditText searchBar;

    private CardView cardView;
    private FrameLayout frameHome;

    //final InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);

    private String mParam1;
    private String mParam2;

    public Fragment_Home() {
        // Required empty public constructor
    }

    public static Fragment_Home newInstance(String param1, String param2) {
        Fragment_Home fragment = new Fragment_Home();
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
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);

        imgExit = rootView.findViewById(R.id.img_exit);
        imgFilter = rootView.findViewById(R.id.img_filter);
        imgClear = rootView.findViewById(R.id.img_clear);
        searchBar = rootView.findViewById(R.id.search_bar);
        frameHome = rootView.findViewById(R.id.frame_home);
        cardView = rootView.findViewById(R.id.card_search);

        replaceFragment(new Fragment_Categorys());
        searchBar.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (!hasFocus) {
                    imgExit.setVisibility(View.GONE);
                    imgFilter.setVisibility(View.GONE);
                    ViewGroup.MarginLayoutParams cardViewParams = (ViewGroup.MarginLayoutParams) cardView.getLayoutParams();
                    cardViewParams.leftMargin = 50;
                    cardViewParams.rightMargin = 50;
                    cardView.setLayoutParams(cardViewParams);



                }else{
                    imgExit.setVisibility(View.VISIBLE);
                    imgFilter.setVisibility(View.VISIBLE);
                    ViewGroup.MarginLayoutParams cardViewParams = (ViewGroup.MarginLayoutParams) cardView.getLayoutParams();
                    cardViewParams.leftMargin = 0;
                    cardViewParams.rightMargin = 10;
                    cardView.setLayoutParams(cardViewParams);
                }

            }
        });
        searchBar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int before, int count) {
                imgClear.setVisibility(charSequence.length() > 0 ? View.VISIBLE : View.GONE);
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                imgClear.setVisibility(charSequence.length() > 0 ? View.VISIBLE : View.GONE);

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }

        });

        // Gắn sự kiện cho nút xóa
        imgClear.setOnClickListener(v -> {
            searchBar.setText("");
            imgClear.setVisibility(View.GONE);
        });

        imgExit.setOnClickListener(v -> {
            searchBar.clearFocus();
            imgClear.callOnClick();
            hideKeyboard();



            replaceFragment(new Fragment_Categorys());
        });

        // Gắn sự kiện cho nút lọc
        imgFilter.setOnClickListener(v -> {
            // Hiển thị fragment mới (thay thế bằng fragment khác nếu cần)
            //replaceFragment(new YourFilterFragment());
            // Ẩn/hiển thị các nút tùy thuộc vào logic của bạn
            imgExit.setVisibility(View.VISIBLE);
            imgFilter.setVisibility(View.VISIBLE);
            imgClear.setVisibility(View.VISIBLE);
        });

        return rootView;
    }

    private void hideKeyboard() {
        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null) {
            imm.hideSoftInputFromWindow(searchBar.getWindowToken(), 0);
        }
    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getChildFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_home, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

}