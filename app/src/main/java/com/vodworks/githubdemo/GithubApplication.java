package com.vodworks.githubdemo;

import android.app.Application;
import android.content.Context;

import com.squareup.otto.Bus;
import com.squareup.otto.ThreadEnforcer;

public class GithubApplication extends Application {

    private static GithubApplication context;
    public static Bus bus = new Bus(ThreadEnforcer.ANY);

    public static Context getAppContext(){
        return context;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        this.context = this;
    }
}
