package com.example.my_coach.sport.MartialArts.Boxing;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.my_coach.R;
import com.google.android.material.tabs.TabLayout;

public class Boxing1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_boxing);

        TabLayout tabLayout = findViewById(R.id.tab_layout);
        ViewPager viewPager = findViewById(R.id.pager);

        viewPager.setAdapter(new SectionPagerAdapter6(getSupportFragmentManager(), 0));
        tabLayout.setupWithViewPager(viewPager);
    }
}