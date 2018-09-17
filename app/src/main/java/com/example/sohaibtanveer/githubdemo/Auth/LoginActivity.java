package com.example.sohaibtanveer.githubdemo.Auth;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.sohaibtanveer.githubdemo.R;
import com.example.sohaibtanveer.githubdemo.UserHome.UserHomeActivity;
import com.example.sohaibtanveer.githubdemo.Util.SharedData;
import com.google.firebase.auth.FirebaseAuth;
import com.squareup.otto.Bus;
import com.squareup.otto.ThreadEnforcer;

public class LoginActivity extends AppCompatActivity {

    public static Bus bus = new Bus(ThreadEnforcer.ANY);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(isUserActive()==true) {
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

    private boolean isUserActive(){
        return SharedData.getAccessToken()==null? false: true;
    }
}

