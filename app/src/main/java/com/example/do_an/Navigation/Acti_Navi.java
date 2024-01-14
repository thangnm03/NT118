package com.example.do_an.Navigation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.do_an.Home.Categorys.Category.Custom_Dish;
import com.example.do_an.Home.Categorys.Category.FoodItem;
import com.example.do_an.Home.Fragment_Home;
import com.example.do_an.Home.Info_Dish.Fragment_Show_Meal;
import com.example.do_an.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;


public class Acti_Navi extends AppCompatActivity {

    private ViewPager mViewPager;
    private BottomNavigationView mBottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mViewPager = findViewById(R.id.view_pager);
        mBottomNavigationView = findViewById(R.id.bottom_menu);
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        mViewPager.setAdapter(adapter);

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                MenuItem menuItem;
                switch (position) {
                    case 0:
                        menuItem = mBottomNavigationView.getMenu().findItem(R.id.home);
                        break;
                    case 1:
                        menuItem = mBottomNavigationView.getMenu().findItem(R.id.meal_plan);
                        break;
                    case 2:
                        menuItem = mBottomNavigationView.getMenu().findItem(R.id.profile);
                        break;
                    default:
                        return;
                }

                if (!menuItem.isChecked()) {
                    menuItem.setChecked(true);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        mBottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if(item.getItemId() == R.id.home)
                    mViewPager.setCurrentItem(0);
                else if(item.getItemId() == R.id.meal_plan)
                    mViewPager.setCurrentItem(1);
                else if(item.getItemId() == R.id.profile)
                    mViewPager.setCurrentItem(2);
                else mViewPager.setCurrentItem(0);
                return true;
            }
        });

    }
}