package com.example.sohaibtanveer.githubdemo.repositoryView.codeFragment;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.sohaibtanveer.githubdemo.models.DirectoryPOJO;
import com.example.sohaibtanveer.githubdemo.models.OttoDataObject;
import com.example.sohaibtanveer.githubdemo.R;
import com.example.sohaibtanveer.githubdemo.util.GitHubService;
import com.example.sohaibtanveer.githubdemo.util.RCallback;
import com.example.sohaibtanveer.githubdemo.util.RetrofitClient;
import com.example.sohaibtanveer.githubdemo.util.SharedData;
import com.google.gson.Gson;
import com.squareup.otto.Subscribe;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;

import static com.example.sohaibtanveer.githubdemo.GithubApplication.bus;


public class CodeFilesFragment extends Fragment {

    private View rootView;

    private RecyclerView recyclerViewDirectory;
    private DirectoryRecyclerAdapter directoryAdapter;

    private RecyclerView recyclerViewPath;
    private PathRecyclerAdapter adapterPath;
    private RecyclerView.LayoutManager layoutManagerPath;

    private ProgressDialog pBar;

    public CodeFilesFragment() {
        // Required empty public constructor
    }

    public static CodeFilesFragment newInstance() {
        CodeFilesFragment fragment = new CodeFilesFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        recyclerViewDirectory = null;
        directoryAdapter = null;

        adapterPath = null;
        recyclerViewPath = null;
        layoutManagerPath = null;
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_codefiles, container, false);
        bus.register(this);
        pBar = new ProgressDialog(getActivity());
        setupUI();
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    private void setupUI() {
        createPathRecyclerView("master");
        getCodeFiles(null,"master");

        ImageButton switchBranchBtn = (ImageButton) rootView.findViewById(R.id.changeBranch);
        switchBranchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(),SwitchBranchActivity.class);
                startActivity(i);
            }
        });
    }

    private void getCodeFiles(String dir, final String ref){
        pBar.show();
        GitHubService serviceUser = RetrofitClient.getClient("https://api.github.com").create(GitHubService.class);
        Call<List<DirectoryPOJO>> directory;
        if(dir == null)
            directory = serviceUser.getFiles("/repos/" + SharedData.getRepositoryName()+ "/contents",ref,SharedData.getAccessToken());
        else if(dir.equals("root"))
            directory = serviceUser.getFiles("/repos/" + SharedData.getRepositoryName() + "/contents",ref,SharedData.getAccessToken());
        else
            directory = serviceUser.getFiles("/repos/"+ SharedData.getRepositoryName() + "/contents/" + dir ,ref,SharedData.getAccessToken());
        directory.enqueue(new RCallback<List<DirectoryPOJO>>() {
            @Override
            public void success(List<DirectoryPOJO> object) {
                loadCodeFiles(object,ref);
            }

            @Override
            public void error(String error) {
                pBar.dismiss();
                showError(error);
            }
        });

    }

    private void loadCodeFiles(List<DirectoryPOJO> directories, String ref){
        pBar.dismiss();
        if(directories != null) {
            if(directoryAdapter == null) {
                recyclerViewDirectory = (RecyclerView) rootView.findViewById(R.id.directoryRecyclerView);
                directoryAdapter = new DirectoryRecyclerAdapter(directories, ref);
                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
                recyclerViewDirectory.setLayoutManager(layoutManager);
                recyclerViewDirectory.setAdapter(directoryAdapter);
            }
            else
                directoryAdapter.loadData(directories,ref);
        }
    }

    private void showError(String error){
        Toast.makeText(getContext(),error,Toast.LENGTH_LONG).show();
    }

    private void createPathRecyclerView(String ref){

        ArrayList<String> paths = new ArrayList<String>();
        paths.add("root");
        if(adapterPath == null) {
            recyclerViewPath = (RecyclerView) rootView.findViewById(R.id.pathRecyclerView);
            adapterPath = new PathRecyclerAdapter(paths, ref);
            layoutManagerPath = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
            recyclerViewPath.setLayoutManager(layoutManagerPath);
            recyclerViewPath.setAdapter(adapterPath);
        }
        else
            adapterPath.reset(paths);

        layoutManagerPath.scrollToPosition(adapterPath.getItemCount()-1);
    }

    private void addNewPath(String newPath){
        adapterPath.addItem(newPath);
    }

    @Subscribe
    void changeDirectory(OttoDataObject com){
        if(com.getTypeOfData().equals("directory_data")){
            ArrayList<String> data = (ArrayList<String>) com.getObj();
            Gson gson = new Gson();
            DirectoryPOJO dir = gson.fromJson(data.get(0),DirectoryPOJO.class);
            String ref = data.get(1);
            String type = dir.getType();
            if(type.equals("dir")){
                addNewPath(dir.getName());
                getCodeFiles(dir.getPath(),ref);
            }
            else if(type.equals("file")){
                Intent intent = new Intent(getActivity(),FileViewActivity.class);
                intent.putExtra("url",dir.getDownloadUrl());
                startActivity(intent);
            }
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        bus.unregister(this);
    }

    @Subscribe
    public void changePath(OttoDataObject com){
        if(com.getTypeOfData().equals("path_information")){
            ArrayList<String> data = (ArrayList<String>) com.getObj();
            getCodeFiles(data.get(0),data.get(1));
        }
    }

    //Branch handler
    @Subscribe
    public void changeBranch(OttoDataObject com){
        if(com.getTypeOfData().equals("branch_reference")){
            String ref = (String) com.getObj();
            createPathRecyclerView(ref);
            getCodeFiles(null,ref);
        }
    }
}
