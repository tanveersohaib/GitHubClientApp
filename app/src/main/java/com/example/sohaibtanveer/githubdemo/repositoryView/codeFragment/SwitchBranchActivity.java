package com.example.sohaibtanveer.githubdemo.repositoryView.codeFragment;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.sohaibtanveer.githubdemo.util.GitHubService;
import com.example.sohaibtanveer.githubdemo.models.OttoDataObject;
import com.example.sohaibtanveer.githubdemo.models.RepoBranchPOJO;
import com.example.sohaibtanveer.githubdemo.models.TagsPOJO;
import com.example.sohaibtanveer.githubdemo.R;
import com.example.sohaibtanveer.githubdemo.util.RetrofitClient;
import com.example.sohaibtanveer.githubdemo.util.SharedData;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.sohaibtanveer.githubdemo.GithubApplication.bus;

public class SwitchBranchActivity extends AppCompatActivity implements StringClickListener{

    private String repoName;
    private String accessToken;
    private RecyclerView recyclerViewBranch;
    private BranchRecyclerAdapter branchAdapter;
    private RecyclerView recyclerViewTags;
    private TagsRecyclerAdapter tagsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_switch_branch);

        bus.register(this);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.switch_tab_layout);
        tabLayout.addTab(tabLayout.newTab().setText("BRANCHES"));
        tabLayout.addTab(tabLayout.newTab().setText("TAGS"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        recyclerViewBranch = null;
        branchAdapter = null;
        recyclerViewTags = null;
        tagsAdapter = null;

        final ViewPager viewPager = (ViewPager) findViewById(R.id.switchPager);
        final BranchPagerAdapter adapter = new BranchPagerAdapter
                (getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
                if(tab.getPosition() == 0){
                    loadBranches();
                }
                else if(tab.getPosition() == 1){
                    loadTags();
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        repoName = SharedData.getRepositoryName();
        accessToken = SharedData.getAccessToken();

        loadBranches();
    }

    private void loadBranches(){
        if(repoName != null || accessToken != null) {

            GitHubService serviceUser = RetrofitClient.getClient("https://api.github.com").create(GitHubService.class);
            Call<List<RepoBranchPOJO>> branches = serviceUser.getBranches("/repos/" + repoName + "/branches", accessToken);
            branches.enqueue(new Callback<List<RepoBranchPOJO>>() {
                @Override
                public void onResponse(Call<List<RepoBranchPOJO>> call, Response<List<RepoBranchPOJO>> response) {
                    loadBranchRecyclerView(response.body());
                }

                @Override
                public void onFailure(Call<List<RepoBranchPOJO>> call, Throwable t) {

                }
            });
        }
    }

    private void loadBranchRecyclerView(List<RepoBranchPOJO> branches){
        if(branches!=null) {
            if(branchAdapter == null) {
                recyclerViewBranch = (RecyclerView) findViewById(R.id.branchRecyclerView);
                branchAdapter = new BranchRecyclerAdapter(branches);
                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
                recyclerViewBranch.setLayoutManager(layoutManager);
                recyclerViewBranch.setAdapter(branchAdapter);
                branchAdapter.setClickListener(this);
            }
            else
                branchAdapter.loadData(branches);
        }
    }

    private void loadTags(){
        if(repoName != null || accessToken != null) {

            GitHubService serviceUser = RetrofitClient.getClient("https://api.github.com").create(GitHubService.class);
            Call<List<TagsPOJO>> tags = serviceUser.getTags("/repos/" + repoName + "/tags", accessToken);
            tags.enqueue(new Callback<List<TagsPOJO>>() {
                @Override
                public void onResponse(Call<List<TagsPOJO>> call, Response<List<TagsPOJO>> response) {
                    loadTagsRecyclerView(response.body());
                }

                @Override
                public void onFailure(Call<List<TagsPOJO>> call, Throwable t) {

                }
            });
        }
    }

    private void loadTagsRecyclerView(List<TagsPOJO> tags){
        if(tags != null){
            if(tagsAdapter == null) {
                recyclerViewTags = (RecyclerView) findViewById(R.id.tagsRecyclerView);
                tagsAdapter = new TagsRecyclerAdapter(tags);
                RecyclerView.LayoutManager layoutManager2 = new LinearLayoutManager(this);
                recyclerViewTags.setLayoutManager(layoutManager2);
                recyclerViewTags.setAdapter(tagsAdapter);
                tagsAdapter.setClickListener(this);
            }
            else
                tagsAdapter.loadData(tags);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onclick(View v, String branch) {
        OttoDataObject com = new OttoDataObject();
        com.setTypeOfData("branch_reference");
        com.setObj(branch);
        bus.post(com);
        this.finish();
    }
}

