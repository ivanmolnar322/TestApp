package com.test.testapp;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class MyPageAdapter extends FragmentStatePagerAdapter {

    private  List<Fragment> myFragments = new ArrayList<>();

    public MyPageAdapter(FragmentManager fm) {
        super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return myFragments.get(position);
    }


    public void addFragment(FragmentMain fragmentMain) {
        myFragments.add(fragmentMain);
        notifyDataSetChanged();
    }

    public void removeFragment() {
        myFragments.remove(myFragments.size()-1);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return myFragments.size();
    }
}
