package com.example.sohaibtanveer.githubdemo.userHome;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.sohaibtanveer.githubdemo.R;
import com.example.sohaibtanveer.githubdemo.models.Event;
import com.example.sohaibtanveer.githubdemo.models.ReadmePOJO;
import com.example.sohaibtanveer.githubdemo.util.GitHubService;
import com.example.sohaibtanveer.githubdemo.util.RCallback;
import com.example.sohaibtanveer.githubdemo.util.RetrofitClient;
import com.example.sohaibtanveer.githubdemo.util.SharedData;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;


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
        getFeeds();
        return rootView;
    }

    private void getFeeds() {
        GitHubService serviceUser = RetrofitClient.getClient("https://api.github.com").create(GitHubService.class);
        Call<List<Event>> feedme = serviceUser.getFeeds("/users/" + SharedData.getUserName() + "/received_events",SharedData.getAccessToken());
        feedme.enqueue(new RCallback<List<Event>>() {
            @Override
            public void success(List<Event> object) {
                loadFeeds(object);
            }

            @Override
            public void error(String error) {
                showError(error);
            }
        });
    }

    private void showError(String error) {
        Toast.makeText(getActivity(),error,Toast.LENGTH_LONG).show();
    }

    private void loadFeeds(List<Event> object) {
        RecyclerView rv = (RecyclerView) rootView.findViewById(R.id.feedsRecyclerView);
        FeedsRecyclerAdapter adapter = new FeedsRecyclerAdapter(getActivity(),object);
        RecyclerView.LayoutManager layoutManager = new  LinearLayoutManager(getActivity());
        rv.setLayoutManager(layoutManager);
        rv.setAdapter(adapter);
    }


}
