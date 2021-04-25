package com.example.my_coach.sport.bodybulding.WigehtLifeThing;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.my_coach.sport.bodybulding.Swimming.AboutSports;
import com.example.my_coach.sport.bodybulding.Swimming.Coaches;
import com.example.my_coach.sport.bodybulding.Swimming.Trainning;

public  class SectionPagerAdapter3 extends FragmentPagerAdapter {
    public SectionPagerAdapter3(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }
    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new AboutSports();
            case 1:

                return new Coaches();
            case 2:
            default:
                return new Trainning();
        }
    }
    @Override
    public int getCount() {
        return 3;
    }
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "AboutSports";
            case 1:

                return "Coaches";
            case 2:
            default:
                return "Trainning";
        }
    }
}