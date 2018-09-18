package com.vodworks.githubdemo.repositoryView.codeFragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class BranchPagerAdapter extends FragmentPagerAdapter {
    int mNumOfTabs;

    public BranchPagerAdapter(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                BranchesFragment tab1 = new BranchesFragment();
                return tab1;
            case 1:
                TagsFragment tab2 = new TagsFragment();
                return tab2;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}
