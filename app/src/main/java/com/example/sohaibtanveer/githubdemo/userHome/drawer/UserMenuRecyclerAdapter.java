package com.example.sohaibtanveer.githubdemo.userHome.drawer;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sohaibtanveer.githubdemo.R;
import com.example.sohaibtanveer.githubdemo.models.DrawerMenuItem;
import com.example.sohaibtanveer.githubdemo.models.OttoDataObject;

import java.util.ArrayList;

import static com.example.sohaibtanveer.githubdemo.GithubApplication.bus;


public class UserMenuRecyclerAdapter extends RecyclerView.Adapter<UserMenuRecyclerAdapter.CustomViewHolder> {

    private ArrayList<DrawerMenuItem> items;

    public UserMenuRecyclerAdapter(ArrayList<DrawerMenuItem> items){
        this.items = items;
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.drawer_menu_item, parent, false);
        return new UserMenuRecyclerAdapter.CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder customViewHolder, int i) {
        customViewHolder.icon.setImageResource(items.get(i).getIcon());
        customViewHolder.name.setText(items.get(i).getName());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    class CustomViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        ImageView icon;
        TextView name;

        CustomViewHolder(final View itemView){
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.drawerMenuName);
            icon = (ImageView) itemView.findViewById(R.id.drawerItemIcon);
            itemView.setOnClickListener(this);
        }
        @Override
        public void onClick(View v) {
            OttoDataObject obj = new OttoDataObject();
            obj.setTypeOfData("drawer_item_click");
            obj.setObj(items.get(getAdapterPosition()));
            bus.post(obj);
        }
    }
}
