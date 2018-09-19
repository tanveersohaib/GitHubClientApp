package com.example.sohaibtanveer.githubdemo.userHome.issues;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class IssuesPagerAdapter extends FragmentStatePagerAdapter {
    int numOfTabs;

    public IssuesPagerAdapter(FragmentManager fm,int numOfTabs){
        super(fm);
        this.numOfTabs = numOfTabs;
    }

    @Override
    public Fragment getItem(int position) {
        switch(position){
            case 0: //Tab 1
                return IssuesViewFragment.newInstance();
            case 1:
                return  IssuesViewFragment.newInstance();
            case 2:
                return  IssuesViewFragment.newInstance();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return numOfTabs;
    }
}
