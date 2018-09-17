package com.example.sohaibtanveer.githubdemo.RepositoryView.IssuesFragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.sohaibtanveer.githubdemo.Auth.LoginActivity;
import com.example.sohaibtanveer.githubdemo.R;

import static com.example.sohaibtanveer.githubdemo.GithubApplication.bus;

public class IssuesFragment extends Fragment {

    public IssuesFragment() {
        // Required empty public constructor
    }

    public static IssuesFragment newInstance() {
        IssuesFragment fragment = new IssuesFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        bus.register(this);

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_issues, container, false);
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
