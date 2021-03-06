package com.example.sohaibtanveer.githubdemo.repositoryView.codeFragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;


public class CodePagerAdapter extends FragmentPagerAdapter {
    int mNumOfTabs;

    public CodePagerAdapter(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                ReadmeFragment tab1 = new ReadmeFragment();
                return tab1;
            case 1:
                CodeFilesFragment tab2 = new CodeFilesFragment();
                return tab2;
            case 2:
                CommitsFragment tab3 = new CommitsFragment();
                return tab3;
            case 3:
                ReleasesFragment tab4 = new ReleasesFragment();
                return tab4;
            case 4:
                ContributorsFragment tab5 = new ContributorsFragment();
                return tab5;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}
