package com.example.sohaibtanveer.githubdemo;

import android.app.Application;
import android.content.Context;

public class GithubApplication extends Application {

    private static GithubApplication context;

    public static Context getAppContext(){
        return context;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        this.context = this;
    }
}
