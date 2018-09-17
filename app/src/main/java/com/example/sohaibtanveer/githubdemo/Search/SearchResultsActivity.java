package com.example.sohaibtanveer.githubdemo.Search;

import android.app.ProgressDialog;
import android.app.SearchManager;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.example.sohaibtanveer.githubdemo.Util.GitHubService;
import com.example.sohaibtanveer.githubdemo.RepositoryView.CodeFragment.ItemClickListener;
import com.example.sohaibtanveer.githubdemo.Models.ItemPOJO;
import com.example.sohaibtanveer.githubdemo.Models.RepoSearchResultPOJO;
import com.example.sohaibtanveer.githubdemo.R;
import com.example.sohaibtanveer.githubdemo.RepositoryView.RepositoryViewActivity;
import com.example.sohaibtanveer.githubdemo.Util.RCallback;
import com.example.sohaibtanveer.githubdemo.Util.RetrofitClient;
import com.example.sohaibtanveer.githubdemo.Util.SharedData;

import java.util.List;

import retrofit2.Call;

public class SearchResultsActivity extends AppCompatActivity implements ItemClickListener {

    private RecyclerView recyclerView;
    private RepositoryRecyclerAdapter adapter;
    private ProgressDialog pBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_results);
        handleIntent(getIntent());
        recyclerView = null;
        adapter = null;
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        handleIntent(intent);
    }

    private void handleIntent(Intent intent) {
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            pBar = new ProgressDialog(this);
            pBar.setMessage("Loading...");
            pBar.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            pBar.show();
            String query = intent.getStringExtra(SearchManager.QUERY);
            //use the query to search your data somehow
            final GitHubService service = RetrofitClient.getClient("https://api.github.com").create(GitHubService.class);
            String accessToken = SharedData.getAccessToken();
            Call<RepoSearchResultPOJO> call = service.getRepositorySearchResults(query,accessToken);
            call.enqueue(new RCallback<RepoSearchResultPOJO>() {
                @Override
                public void success(RepoSearchResultPOJO object) {
                    generateList(object.getItems());
                }

                @Override
                public void error(String error) {
                    pBar.dismiss();
                    displayError(error);
                }
            });
        }
    }

    private void displayError(String error){
        Toast.makeText(this,error,Toast.LENGTH_LONG).show();
    }

    private void generateList(List<ItemPOJO> data){
        pBar.dismiss();
        if(data != null) {
            if (adapter == null) {
                recyclerView = findViewById(R.id.resultRecyclerView);
                adapter = new RepositoryRecyclerAdapter(this, data);
                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(SearchResultsActivity.this);
                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setAdapter(adapter);
                adapter.setClickListener(this);
            }
            else
                adapter.loadData(data);
        }
    }

    @Override
    public void onClick(View v,ItemPOJO repo){
        Intent i = new Intent(this,RepositoryViewActivity.class);
        SharedData.setRepositoryName(repo.getFullName());
        startActivity(i);
    }

}
