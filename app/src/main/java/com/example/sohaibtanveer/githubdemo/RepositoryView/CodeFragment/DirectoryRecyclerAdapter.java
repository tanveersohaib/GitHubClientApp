package com.example.sohaibtanveer.githubdemo.RepositoryView.CodeFragment;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sohaibtanveer.githubdemo.Auth.LoginActivity;
import com.example.sohaibtanveer.githubdemo.Models.DirectoryPOJO;
import com.example.sohaibtanveer.githubdemo.Models.OttoDataObject;
import com.example.sohaibtanveer.githubdemo.R;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class DirectoryRecyclerAdapter extends RecyclerView.Adapter<DirectoryRecyclerAdapter.CustomViewHolder> {

    private List<DirectoryPOJO> itemList;
    //private DirectoryClickListener dirClickListener;
    private String ref;

    public DirectoryRecyclerAdapter(List<DirectoryPOJO> dir, String ref){
        this.ref = ref;
        itemList = dir;
    }

    //public void setClickListener(DirectoryClickListener dirClickListener){
    //    this.dirClickListener = dirClickListener;
    //}

    class CustomViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView directoryName;
        ImageView directoryIcon;

        CustomViewHolder(final View itemView){
            super(itemView);
            itemView.setOnClickListener(this);

            directoryName = (TextView) itemView.findViewById(R.id.directoryName);
            directoryIcon = (ImageView) itemView.findViewById(R.id.directoryIcon);
        }

        @Override
        public void onClick(View v) {
            //dirClickListener.onclick(v,itemList.get(getAdapterPosition()));
            Gson gson = new Gson();
            ArrayList<String> data = new ArrayList<String>();
            data.add(gson.toJson(itemList.get(getAdapterPosition())));
            data.add(ref);
            OttoDataObject com = new OttoDataObject();
            com.setTypeOfData("directory_data");
            com.setObj(data);
            LoginActivity.bus.register(this);
            LoginActivity.bus.post(com);
        }
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int i) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.directory_item, parent, false);
        return new CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CustomViewHolder customViewHolder, int position) {
        customViewHolder.directoryName.setText(itemList.get(position).getName());
        String type = itemList.get(position).getType();
        if(type.equals("dir"))
            customViewHolder.directoryIcon.setImageResource(R.drawable.ic_folder_black_24dp);
        else if(type.equals("file"))
            customViewHolder.directoryIcon.setImageResource(R.drawable.ic_insert_drive_file_black_24dp);
    }

    @Override
    public int getItemCount() {
        return itemList==null? 0 : itemList.size();
    }

    public void loadData(List<DirectoryPOJO> items, String ref){
        this.itemList = items;
        this.ref = ref;
        this.notifyDataSetChanged();
    }
}
