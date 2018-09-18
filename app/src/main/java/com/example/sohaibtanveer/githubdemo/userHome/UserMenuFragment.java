package com.example.sohaibtanveer.githubdemo.userHome;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.sohaibtanveer.githubdemo.GithubApplication;
import com.example.sohaibtanveer.githubdemo.R;
import com.example.sohaibtanveer.githubdemo.models.DrawerMenuItem;

import java.net.UnknownServiceException;
import java.util.ArrayList;
import java.util.List;

public class UserMenuFragment extends Fragment{

    private View rootView;

    public UserMenuFragment(){ }

    public static UserMenuFragment getInstance(){
        return new UserMenuFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_user_menu,container,false);
        loadList();
        return rootView;
    }

    private void loadList() {
        ArrayList<DrawerMenuItem> items = createItems();
        RecyclerView recyclerView = (RecyclerView) rootView.findViewById(R.id.userMenuListView);
        UserMenuRecyclerAdapter adapter = new UserMenuRecyclerAdapter(items);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    private ArrayList<DrawerMenuItem> createItems(){
        ArrayList<DrawerMenuItem> list = new ArrayList<DrawerMenuItem>();
        list.add(new DrawerMenuItem("Home",getResources().getIdentifier("ic_home","drawable", getActivity().getPackageName())));
        list.add(new DrawerMenuItem("Profile",getResources().getIdentifier("ic_person","drawable", getActivity().getPackageName())));
        list.add(new DrawerMenuItem("Organization",getResources().getIdentifier("ic_organization","drawable", getActivity().getPackageName())));
        list.add(new DrawerMenuItem("Notifications",getResources().getIdentifier("ic_bell","drawable", getActivity().getPackageName())));
        list.add(new DrawerMenuItem("Pinned",getResources().getIdentifier("ic_pin","drawable", getActivity().getPackageName())));
        list.add(new DrawerMenuItem("Trending",getResources().getIdentifier("ic_flame","drawable", getActivity().getPackageName())));
        list.add(new DrawerMenuItem("Gists",getResources().getIdentifier("ic_gist","drawable", getActivity().getPackageName())));
        list.add(new DrawerMenuItem("Settings",getResources().getIdentifier("ic_settings","drawable", getActivity().getPackageName())));
        return list;
    }
}
