package com.test.testapp;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class MyPageAdapter extends FragmentStatePagerAdapter {

    private List<Fragment> myFragments = new ArrayList<>();

    public MyPageAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return myFragments.get(position);
    }


    public void addFragment(FragmentMain fragmentMain) {
        myFragments.add(fragmentMain);
        notifyDataSetChanged();
    }

    public void removeFragment() {
        myFragments.remove(myFragments.size() - 1);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return myFragments.size();
    }
}
