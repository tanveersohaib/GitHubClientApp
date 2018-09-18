package com.example.sohaibtanveer.githubdemo.repositoryView.codeFragment;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.sohaibtanveer.githubdemo.models.ReadmePOJO;
import com.example.sohaibtanveer.githubdemo.R;
import com.example.sohaibtanveer.githubdemo.util.GitHubService;
import com.example.sohaibtanveer.githubdemo.util.RCallback;
import com.example.sohaibtanveer.githubdemo.util.RetrofitClient;
import com.example.sohaibtanveer.githubdemo.util.SharedData;

import br.tiagohm.markdownview.MarkdownView;
import retrofit2.Call;

public class ReadmeFragment extends Fragment {

    private View rootView;
    private ProgressDialog pBar;

    public ReadmeFragment() {
        // Required empty public constructor
    }

    public static ReadmeFragment newInstance() {
        ReadmeFragment fragment = new ReadmeFragment();
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
        rootView =  inflater.inflate(R.layout.fragment_readme, container, false);
        pBar = new ProgressDialog(getActivity());
        getReadmeUrl();
        return rootView;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    private void getReadmeUrl(){
        pBar.show();
        GitHubService serviceUser = RetrofitClient.getClient("https://api.github.com").create(GitHubService.class);
        Call<ReadmePOJO> readme = serviceUser.getReadmeObject("/repos/" + SharedData.getRepositoryName() + "/readme",SharedData.getAccessToken());
        readme.enqueue(new RCallback<ReadmePOJO>() {

            @Override
            public void success(ReadmePOJO object) {
                loadReadMe(object);
            }

            @Override
            public void error(String error) {
                pBar.dismiss();
                showError(error);
            }
        });
    }

    private void loadReadMe(ReadmePOJO obj){
        pBar.dismiss();
        if(obj != null) {
            MarkdownView mdView  = (MarkdownView) getActivity().findViewById(R.id.readmeMarkdown);
            mdView.loadMarkdownFromUrl(obj.getDownloadUrl());
        }
    }

    private void showError(String error){
        Toast.makeText(getContext(),error,Toast.LENGTH_LONG).show();
    }

}
