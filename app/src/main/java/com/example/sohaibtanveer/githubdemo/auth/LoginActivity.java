package com.example.sohaibtanveer.githubdemo.auth;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.sohaibtanveer.githubdemo.R;
import com.example.sohaibtanveer.githubdemo.userHome.UserHomeActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(UserHomeActivity.isUserActive()) {
            redirectToHome();
        }
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
    }

    private void redirectToHome() {
        Intent intent = new Intent(this,UserHomeActivity.class);
        startActivity(intent);
        this.finish();
    }

    @OnClick(R.id.login)
    public void redirectToLogin() {
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse("https://github.com/login/oauth/authorize?client_id=25a2190a925d5982a5ae"));
        startActivity(i);
        this.finish();
    }

}

