package com.example.my_coach.sport.bodybulding.Running;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.my_coach.R;
import com.example.my_coach.sport.bodybulding.Swimming.SectionPagerAdapter1;
import com.google.android.material.tabs.TabLayout;

public class Running extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_running);


        TabLayout tabLayout = findViewById(R.id.tab_layout);
        ViewPager viewPager = findViewById(R.id.pager);

        viewPager.setAdapter(new SectionPagerAdapter2(getSupportFragmentManager(), 0));
        tabLayout.setupWithViewPager(viewPager);
    }
}