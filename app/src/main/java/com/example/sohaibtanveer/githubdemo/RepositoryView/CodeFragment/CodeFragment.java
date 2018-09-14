package com.example.sohaibtanveer.githubdemo.RepositoryView.CodeFragment;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.sohaibtanveer.githubdemo.Auth.LoginActivity;
import com.example.sohaibtanveer.githubdemo.Util.GitHubService;
import com.example.sohaibtanveer.githubdemo.Models.DirectoryPOJO;
import com.example.sohaibtanveer.githubdemo.Models.ItemPOJO;
import com.example.sohaibtanveer.githubdemo.Models.OttoDataObject;
import com.example.sohaibtanveer.githubdemo.Models.ReadmePOJO;
import com.example.sohaibtanveer.githubdemo.R;
import com.example.sohaibtanveer.githubdemo.Util.RetrofitClientHandler;
import com.google.gson.Gson;
import com.squareup.otto.Subscribe;

import java.util.ArrayList;
import java.util.List;

import br.tiagohm.markdownview.MarkdownView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class CodeFragment extends Fragment {

    private static ItemPOJO repository;
    private static View rootView;
    private String accessToken;

    private RecyclerView recyclerViewDirectory;
    private DirectoryRecyclerAdapter directoryAdapter;

    private RecyclerView recyclerViewPath;
    private PathRecyclerAdapter adapterPath;
    private RecyclerView.LayoutManager layoutManagerPath;

    private ProgressDialog progressDialog;

    public CodeFragment() {
        // Required empty public constructor
    }

    public static CodeFragment newInstance(String repoJsonString) {
        CodeFragment fragment = new CodeFragment();
        Bundle b = new Bundle();
        b.putString("repoJsonString",repoJsonString);
        fragment.setArguments(b);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initializeAccessToken();
        recyclerViewDirectory = null;
        directoryAdapter = null;
        adapterPath = null;
        recyclerViewPath = null;
        layoutManagerPath = null;
        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Loading...");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        LoginActivity.bus.register(this);
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_code, container, false);

        Toolbar toolbar = (Toolbar) rootView.findViewById(R.id.toolbar);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);

        TabLayout tabLayout = (TabLayout) rootView.findViewById(R.id.tab_layout);
        tabLayout.addTab(tabLayout.newTab().setText("README"));
        tabLayout.addTab(tabLayout.newTab().setText("FILES"));
        tabLayout.addTab(tabLayout.newTab().setText("COMMITS"));
        tabLayout.addTab(tabLayout.newTab().setText("RELEASES"));
        tabLayout.addTab(tabLayout.newTab().setText("CONTRIBUTORS"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);

        final ViewPager viewPager = (ViewPager) rootView.findViewById(R.id.pager);
        final CodePagerAdapter adapter = new CodePagerAdapter
                (getFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
                if(tab.getPosition() == 0) //Readme
                    getReadmeUrl();
                else if(tab.getPosition() == 1) {//Code files
                    SharedPreferences pref = getActivity().getSharedPreferences("repo_data",Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = pref.edit();
                    editor.putString("access_token",accessToken);
                    editor.putString("repo_name",repository.getFullName());
                    editor.commit();
                    createPathRecyclerView("master");
                    getCodeFiles(null,"master");
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        //Initialize repository static member
        Bundle b = getArguments();
        String repoJsonString = b.getString("repoJsonString");
        Gson gson = new Gson();
        repository = gson.fromJson(repoJsonString,ItemPOJO.class);
        getReadmeUrl();
        return rootView;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        //context handling
        LoginActivity.bus.post(this);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        getActivity().getMenuInflater().inflate(R.menu.menu_main, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void initializeAccessToken(){
        SharedPreferences pref = getActivity().getSharedPreferences("user_data",Context.MODE_PRIVATE);
        if(pref.contains("access_token")){
            accessToken = pref.getString("access_token",null);
        }
    }

    private void getReadmeUrl(){
        progressDialog.show();
        GitHubService serviceUser = RetrofitClientHandler.getClient("https://api.github.com").create(GitHubService.class);
        Call<ReadmePOJO> readme = serviceUser.getReadmeObject("/repos/" + repository.getFullName() + "/readme",accessToken);
        readme.enqueue(new Callback<ReadmePOJO>() {
            @Override
            public void onResponse(Call<ReadmePOJO> readme, Response<ReadmePOJO> response) {
                loadReadMe(response.body());
            }

            @Override
            public void onFailure(Call<ReadmePOJO> readme, Throwable t) {

            }
        });
    }

    private void loadReadMe(ReadmePOJO obj){
        progressDialog.dismiss();
        if(obj != null) {
            MarkdownView mdView  = (MarkdownView) rootView.findViewById(R.id.readmeMarkdown);
            mdView.loadMarkdownFromUrl(obj.getDownloadUrl());
        }
    }

    private void getCodeFiles(String dir,final String ref){
        progressDialog.show();
        GitHubService serviceUser = RetrofitClientHandler.getClient("https://api.github.com").create(GitHubService.class);
        Call<List<DirectoryPOJO>> directory;
        if(dir == null)
             directory = serviceUser.getFiles("/repos/" + repository.getFullName() + "/contents",ref,accessToken);
        else if(dir.equals("root"))
            directory = serviceUser.getFiles("/repos/" + repository.getFullName() + "/contents",ref,accessToken);
        else
            directory = serviceUser.getFiles("/repos/"+ repository.getFullName() + "/contents/" + dir ,ref,accessToken);
        directory.enqueue(new Callback<List<DirectoryPOJO>>() {
            @Override
            public void onResponse(Call<List<DirectoryPOJO>> call, Response<List<DirectoryPOJO>> response) {
                loadCodeFiles(response.body(),ref);
            }

            @Override
            public void onFailure(Call<List<DirectoryPOJO>> call, Throwable t) {

            }
        });

    }

    private void loadCodeFiles(List<DirectoryPOJO> directories, String ref){
        progressDialog.dismiss();
        if(directories != null) {
            if(directoryAdapter == null) {
                recyclerViewDirectory = (RecyclerView) getActivity().findViewById(R.id.directoryRecyclerView);
                directoryAdapter = new DirectoryRecyclerAdapter(directories, ref);
                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
                recyclerViewDirectory.setLayoutManager(layoutManager);
                recyclerViewDirectory.setAdapter(directoryAdapter);
            }
            else
                directoryAdapter.loadData(directories,ref);
        }
    }

    private void createPathRecyclerView(String ref){

        ArrayList<String> paths = new ArrayList<String>();
        paths.add("root");
        if(adapterPath == null) {
            recyclerViewPath = (RecyclerView) getActivity().findViewById(R.id.pathRecyclerView);
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

    //DirectoryPOJO
    /*@Override
    public void onclick(View v, DirectoryPOJO dir) {
        String type = dir.getType();
        if(type.equals("dir")){
            addNewPath(dir.getName());
            getCodeFiles(dir.getPath(),);
        }
        else if(type.equals("file")){
            Intent intent = new Intent(getActivity(),FileViewActivity.class);
            intent.putExtra("url",dir.getDownloadUrl());
            startActivity(intent);
        }
    }*/

    @Subscribe void changeDirectory(OttoDataObject com){
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

    /*//Path
    @Override
    public void onclick(View v, String path) {
        adapter.removeItem(path);
        getCodeFiles(path,);
    }*/

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
