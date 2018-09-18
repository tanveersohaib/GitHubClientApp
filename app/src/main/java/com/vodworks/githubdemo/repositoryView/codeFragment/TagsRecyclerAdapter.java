package com.vodworks.githubdemo.repositoryView.codeFragment;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.vodworks.githubdemo.models.TagsPOJO;
import com.vodworks.githubdemo.R;

import java.util.List;

public class TagsRecyclerAdapter extends RecyclerView.Adapter<TagsRecyclerAdapter.CustomViewHolder>{

    private List<TagsPOJO> items;
    private StringClickListener clickListener;

    public TagsRecyclerAdapter(List<TagsPOJO> items){
        this.items = items;
    }

    public void setClickListener(StringClickListener clickListener) {
        this.clickListener = clickListener;
    }

    class CustomViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView branchItemName;

        CustomViewHolder(final View itemView){
            super(itemView);
            itemView.setOnClickListener(this);
            branchItemName = (TextView) itemView.findViewById(R.id.branchItemName);
        }

        @Override
        public void onClick(View v) {
            clickListener.onclick(v,items.get(getAdapterPosition()).getName());
        }
    }

    @NonNull
    @Override
    public TagsRecyclerAdapter.CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.fragement_branch_item, parent, false);
        return new TagsRecyclerAdapter.CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder customViewHolder, int i) {
        customViewHolder.branchItemName.setText(items.get(i).getName());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void loadData(List<TagsPOJO> items){
        this.items = items;
        this.notifyDataSetChanged();
    }
}
