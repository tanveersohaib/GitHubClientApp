package com.vodworks.githubdemo.repositoryView.codeFragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vodworks.githubdemo.R;

import static com.vodworks.githubdemo.GithubApplication.bus;


public class CommitsFragment extends Fragment {

    public CommitsFragment() {
        // Required empty public constructor
    }

    public static CommitsFragment newInstance() {
        CommitsFragment fragment = new CommitsFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_commits, container, false);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        bus.post(this);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

}
