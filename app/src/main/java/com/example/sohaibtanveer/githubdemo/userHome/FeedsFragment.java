package com.example.sohaibtanveer.githubdemo.userHome;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.sohaibtanveer.githubdemo.R;


public class FeedsFragment extends Fragment {

    private View rootView;

    public FeedsFragment(){}

    public static FeedsFragment newInstance(){
        return new FeedsFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_feeds,container,false);
        return rootView;
    }


}
