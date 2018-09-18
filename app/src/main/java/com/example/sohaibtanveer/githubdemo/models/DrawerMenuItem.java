package com.example.sohaibtanveer.githubdemo.models;

import android.graphics.Bitmap;

public class DrawerMenuItem {
    private String name;
    private int icon;

    public DrawerMenuItem(String name, int icon){
        this.name = name;
        this.icon = icon;
    }

    public int getIcon() {
        return icon;
    }

    public String getName() {
        return name;
    }
}
