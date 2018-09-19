package com.example.sohaibtanveer.githubdemo.userHome.issuesAndPullRequests;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.sohaibtanveer.githubdemo.R;

public class ViewFragment extends Fragment {

    public ViewFragment(){}

    public static ViewFragment newInstance(){
        return new ViewFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_issues_view,container,false);
    }
}
