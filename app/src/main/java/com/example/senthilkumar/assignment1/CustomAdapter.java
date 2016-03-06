package com.example.senthilkumar.assignment1;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

/**
 * Created by Senthil Kumar on 2/20/2016.
 */
public class CustomAdapter extends FragmentPagerAdapter {

    String[] fragments = {"ToDo List", "Tic Tac Toe", "Reminder"};

    public CustomAdapter(android.support.v4.app.FragmentManager fm, Context context) {
        super(fm);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return fragments[position];
    }

    @Override
    public Fragment getItem(int position) {
        switch(position) {
            case 0:
                return new Fragment1();
            case 1:
                return new Fragment2();
            case 2:
                return new Fragment3();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return fragments.length;
    }
}
