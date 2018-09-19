package com.example.sohaibtanveer.githubdemo.userHome.issuesAndPullRequests;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.sohaibtanveer.githubdemo.R;

public class TabbedFragment extends Fragment {

    private View rootView;

    public TabbedFragment(){}

    public static TabbedFragment newInstance(){
        return new TabbedFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_user_issues,container,false);
        setTabbedView();
        return rootView;
    }

    private void setTabbedView() {
        TabLayout tabLayout = (TabLayout) rootView.findViewById(R.id.userIssuesTabLayout);
        tabLayout.addTab(tabLayout.newTab().setText("CREATED"));
        tabLayout.addTab(tabLayout.newTab().setText("ASSIGNED"));
        tabLayout.addTab(tabLayout.newTab().setText("MENTIONED"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        final ViewPager viewPager = (ViewPager) rootView.findViewById(R.id.userIssuesPager);
        final PagerAdapter adapter = new PagerAdapter(getActivity().getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }


}
