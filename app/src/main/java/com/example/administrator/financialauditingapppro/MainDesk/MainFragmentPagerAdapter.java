package com.example.administrator.financialauditingapppro.MainDesk;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * Created by Administrator on 6/9/2017.
 */

public class MainFragmentPagerAdapter extends FragmentPagerAdapter {

    ArrayList<Fragment> fragments;

    public MainFragmentPagerAdapter(FragmentManager fm, ArrayList fragments) {
        super(fm);
        this.fragments = fragments;
        notifyDataSetChanged();
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }
}
