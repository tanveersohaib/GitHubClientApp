package com.example.sohaibtanveer.githubdemo.repositoryView.codeFragment;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.sohaibtanveer.githubdemo.models.RepoBranchPOJO;
import com.example.sohaibtanveer.githubdemo.R;

import java.util.List;

public class BranchRecyclerAdapter extends RecyclerView.Adapter<BranchRecyclerAdapter.CustomViewHolder> {

    private List<RepoBranchPOJO> items;
    private StringClickListener clickListener;

    public BranchRecyclerAdapter(List<RepoBranchPOJO> items){
        this.items = items;
    }

    public void setClickListener(StringClickListener clickListener) {
        this.clickListener = clickListener;
    }

    class CustomViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView branchItemName;

        CustomViewHolder(final View itemView){
            super(itemView);
            branchItemName = (TextView) itemView.findViewById(R.id.branchItemName);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            clickListener.onclick(v,items.get(getAdapterPosition()).getName());
        }
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.fragement_branch_item, parent, false);
        return new BranchRecyclerAdapter.CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder customViewHolder, int i) {
        customViewHolder.branchItemName.setText(items.get(i).getName());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void loadData(List<RepoBranchPOJO> items){
        this.items = items;
        this.notifyDataSetChanged();
    }
}
