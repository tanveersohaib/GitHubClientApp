package com.example.sohaibtanveer.githubdemo.userHome;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.sohaibtanveer.githubdemo.R;
import com.example.sohaibtanveer.githubdemo.auth.LoginActivity;
import com.example.sohaibtanveer.githubdemo.models.DrawerMenuItem;
import com.example.sohaibtanveer.githubdemo.models.OttoDataObject;
import com.example.sohaibtanveer.githubdemo.util.SharedData;
import com.squareup.otto.Subscribe;

import java.util.ArrayList;

import static android.content.Context.POWER_SERVICE;
import static com.example.sohaibtanveer.githubdemo.GithubApplication.bus;

public class UserAccountFragment extends Fragment {

    private View rootView;

    public UserAccountFragment(){

    }

    public static UserAccountFragment getInstance(){
        return new UserAccountFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_user_account,container,false);
        loadList();
        bus.register(this);
        return rootView;
    }

    private void loadList() {
        ArrayList<DrawerMenuItem> items = createItems();
        RecyclerView recyclerView = (RecyclerView) rootView.findViewById(R.id.userAccountListView);
        UserMenuRecyclerAdapter adapter = new UserMenuRecyclerAdapter(items);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    private ArrayList<DrawerMenuItem> createItems(){
        ArrayList<DrawerMenuItem> list = new ArrayList<DrawerMenuItem>();
        list.add(new DrawerMenuItem("Logout",getResources().getIdentifier("ic_sign_out","drawable", getActivity().getPackageName())));
        list.add(new DrawerMenuItem("Add Account",getResources().getIdentifier("ic_plus","drawable", getActivity().getPackageName())));
        list.add(new DrawerMenuItem("Repositories",getResources().getIdentifier("ic_repo","drawable", getActivity().getPackageName())));
        list.add(new DrawerMenuItem("Starred",getResources().getIdentifier("ic_star","drawable", getActivity().getPackageName())));
        list.add(new DrawerMenuItem("Pinned",getResources().getIdentifier("ic_pin","drawable", getActivity().getPackageName())));
        return list;
    }


    @Subscribe
    public void itemClickListener(OttoDataObject obj){
        if(obj.getTypeOfData().equals("drawer_item_click")){
            String buttonPressed  = ((DrawerMenuItem) obj.getObj()).getName();
            switch(buttonPressed){
                case "Logout":
                    logout();
                    break;
            }
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        bus.unregister(this);
    }

    private void logout(){
        SharedData.removeAccessToken();
        //Redirect to LoginScreen
        Intent intent = new Intent(getActivity(), LoginActivity.class);
        startActivity(intent);
        getActivity().finish();
    }

}
