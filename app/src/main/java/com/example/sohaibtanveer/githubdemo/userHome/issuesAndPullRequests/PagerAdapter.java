package com.example.sohaibtanveer.githubdemo.userHome.issuesAndPullRequests;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class PagerAdapter extends FragmentStatePagerAdapter {
    int numOfTabs;

    public PagerAdapter(FragmentManager fm, int numOfTabs){
        super(fm);
        this.numOfTabs = numOfTabs;
    }

    @Override
    public Fragment getItem(int position) {
        switch(position){
            case 0: //Tab 1
                return ViewFragment.newInstance();
            case 1:
                return  ViewFragment.newInstance();
            case 2:
                return  ViewFragment.newInstance();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return numOfTabs;
    }
}
