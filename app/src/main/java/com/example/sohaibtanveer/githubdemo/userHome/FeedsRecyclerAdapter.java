package com.example.sohaibtanveer.githubdemo.userHome;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.view.menu.MenuView;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sohaibtanveer.githubdemo.R;
import com.example.sohaibtanveer.githubdemo.models.Event;
import com.example.sohaibtanveer.githubdemo.models.issuesCommentPayload.IssuesComment;
import com.example.sohaibtanveer.githubdemo.search.RepositoryRecyclerAdapter;
import com.example.sohaibtanveer.githubdemo.util.DateTimeHelper;
import com.example.sohaibtanveer.githubdemo.util.SharedData;
import com.google.gson.Gson;
import com.prof.rssparser.Article;
import com.squareup.picasso.Picasso;

import net.dgardiner.markdown.MarkdownProcessor;

import org.jsoup.Jsoup;
import org.jsoup.examples.HtmlToPlainText;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.tiagohm.markdownview.MarkdownView;

public class FeedsRecyclerAdapter extends RecyclerView.Adapter<FeedsRecyclerAdapter.CustomViewHolder> {

    List<Event> feeds;

    public FeedsRecyclerAdapter(Context context,List<Event> feeds){
        this.feeds = feeds;
    }

    class CustomViewHolder extends RecyclerView.ViewHolder{

        ImageView avatar;
        ImageView icon;
        TextView feedUserName;
        TextView feedName;
        TextView feedContent;
        TextView feedDescription;
        TextView feedTime;

        CustomViewHolder(View itemView) {
            super(itemView);
            avatar = (ImageView) itemView.findViewById(R.id.feedAvatar);
            icon = (ImageView) itemView.findViewById(R.id.feedIcon);
            feedUserName = (TextView) itemView.findViewById(R.id.feedUserName);
            feedName = (TextView) itemView.findViewById(R.id.feedName);
            feedDescription = (TextView) itemView.findViewById(R.id.feedDescription);
            feedContent = (TextView) itemView.findViewById(R.id.feedContent);
            feedTime = (TextView) itemView.findViewById(R.id.feedTime);
        }
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int position) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.feeds_item, parent, false);
        return new FeedsRecyclerAdapter.CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder customViewHolder, int i) {
        if(feeds.get(i).getActor().getAvatar_url() != null)
            Picasso.get().load(feeds.get(i).getActor().getAvatar_url()).into(customViewHolder.avatar);

        if(feeds.get(i).getRepo().getName() != null)
            customViewHolder.feedName.setText(feeds.get(i).getRepo().getName());

        if(feeds.get(i).getActor().getLogin() != null)
            customViewHolder.feedUserName.setText(feeds.get(i).getActor().getLogin());

        Long due = DateTimeHelper.getDateInMillis(feeds.get(i).getCreated_at());
        Long now = DateTimeHelper.getDateInMillis(DateTimeHelper.localTimeToISO8601());

        String timeAgo = DateUtils.getRelativeTimeSpanString(due,now,DateUtils.MINUTE_IN_MILLIS).toString();
        customViewHolder.feedTime.setText(timeAgo);

        if(feeds.get(i).getType().equals("IssueCommentEvent")){
            customViewHolder.feedDescription.setText("commented on issue");
            Gson gson = new Gson();
            String payload = gson.toJson(feeds.get(i).getPayload());
            IssuesComment comment = gson.fromJson(payload,IssuesComment.class);
            if(comment.getComment().getBody() != null){
                MarkdownProcessor processor = new MarkdownProcessor();
                try {
                    String html = processor.process(comment.getComment().getBody());
                    HtmlToPlainText formatter = new HtmlToPlainText();
                    String content = formatter.getPlainText(Jsoup.parse(html));
                    content = content.replace("\n"," ").replace("\r"," ");
                    customViewHolder.feedContent.setText(content);
                }catch (Exception e){
                    Log.d("ioException",e.getMessage());
                }

            }
        }

    }

    @Override
    public int getItemCount() {
        return feeds.size();
    }

}
