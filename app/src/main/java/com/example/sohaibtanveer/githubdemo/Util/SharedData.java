package com.example.sohaibtanveer.githubdemo.Util;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.sohaibtanveer.githubdemo.GithubApplication;

public class SharedData {
    private static String fileName = "user_data";
    private static String accessTokenName = "access_token";

    private static SharedPreferences pref = GithubApplication.getAppContext().getSharedPreferences(fileName, Context.MODE_PRIVATE);
    private static SharedPreferences.Editor editor;

    public static String getAccessToken(){
        return pref.getString(accessTokenName,null);
    }

    public static void setAccessToken(String accessToken){
        editor = pref.edit();
        editor.putString(accessTokenName,accessToken);
        editor.apply();
    }

    public static boolean hasAccessToken(){
        return pref.contains("access_token");
    }

    public static void removeAccessToken(){
        if(hasAccessToken()){
            editor = pref.edit();
            editor.remove("access_token");
            editor.apply();
        }
    }

    public static void setRepositoryName(String name){
        if(name != null) {
            editor = pref.edit();
            editor.putString("repo_name", name);
            editor.apply();
        }
    }

    public static boolean hasRepositoryName(){
        return pref.contains("repo_name");
    }

    public static String getRepositoryName(){
        return pref.getString("repo_name",null);
    }

}
