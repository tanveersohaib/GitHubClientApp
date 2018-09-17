package com.example.sohaibtanveer.githubdemo.Util;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.sohaibtanveer.githubdemo.GithubApplication;

public class SharedData {
    private static String fileName = "user_data";
    private static String accessTokenName = "access_token";

    private static SharedPreferences pref;
    private static SharedPreferences.Editor editor;

    public static String getAccessToken(){
        pref = GithubApplication.getAppContext().getSharedPreferences(fileName, Context.MODE_PRIVATE);
        return pref.getString(accessTokenName,null);
    }

    public static void setAccessToken(String accessToken){
        pref = GithubApplication.getAppContext().getSharedPreferences(fileName,Context.MODE_PRIVATE);
        editor = pref.edit();
        editor.putString(accessTokenName,accessToken);
        editor.commit();
    }
}
