package com.example.sohaibtanveer.githubdemo.userHome;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class NavigationPagerAdapter extends FragmentStatePagerAdapter {
    int numOfTabs;

    public NavigationPagerAdapter(FragmentManager fm, int numOfTabs){
        super(fm);
        this.numOfTabs = numOfTabs;
    }

    @Override
    public Fragment getItem(int position) {
        if(position == 0){                          //tab 1
            return UserMenuFragment.getInstance();
        }
        else if(position == 1){                     //tab 2
            return UserAccountFragment.getInstance();
        }
        else
            return null;
    }

    @Override
    public int getCount() {
        return numOfTabs;
    }
}
