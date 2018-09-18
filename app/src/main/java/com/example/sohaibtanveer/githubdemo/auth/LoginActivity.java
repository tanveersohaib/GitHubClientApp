package com.example.sohaibtanveer.githubdemo.auth;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.sohaibtanveer.githubdemo.R;
import com.example.sohaibtanveer.githubdemo.userHome.UserHomeActivity;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(UserHomeActivity.isUserActive()) {
            Intent intent = new Intent(this,UserHomeActivity.class);
            startActivity(intent);
        }
        else {
            setContentView(R.layout.activity_login);
            Button loginBtn = (Button) findViewById(R.id.login);
            loginBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(Intent.ACTION_VIEW);
                    i.setData(Uri.parse("https://github.com/login/oauth/authorize?client_id=25a2190a925d5982a5ae"));
                    startActivity(i);
                }
            });
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
    }


}

