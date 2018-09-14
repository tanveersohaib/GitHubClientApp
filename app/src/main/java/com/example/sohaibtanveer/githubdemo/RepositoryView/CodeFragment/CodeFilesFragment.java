package com.example.sohaibtanveer.githubdemo.RepositoryView.CodeFragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.example.sohaibtanveer.githubdemo.R;


public class CodeFilesFragment extends Fragment {

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
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View rootView = inflater.inflate(R.layout.fragment_codefiles, container, false);
        final SharedPreferences pref = getActivity().getSharedPreferences("repo_data",Context.MODE_PRIVATE);

        ImageButton switchBranchBtn = (ImageButton) rootView.findViewById(R.id.changeBranch);
        switchBranchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(),SwitchBranchActivity.class);
                String a = pref.getString("repo_name",null);
                String b = pref.getString("access_token",null);
                i.putExtra("repo_name",pref.getString("repo_name",null));
                i.putExtra("access_token",pref.getString("access_token",null));
                startActivity(i);
            }
        });
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

}
