package com.example.sohaibtanveer.githubdemo.models.issuesCommentPayload;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
/**
 * Awesome Pojo Generator
 * */
public class IssuesComment {
  @SerializedName("issue")
  @Expose
  private Issue issue;
  @SerializedName("sender")
  @Expose
  private Sender sender;
  @SerializedName("action")
  @Expose
  private String action;
  @SerializedName("comment")
  @Expose
  private Comment comment;
  @SerializedName("repository")
  @Expose
  private Repository repository;
  public void setIssue(Issue issue){
   this.issue=issue;
  }
  public Issue getIssue(){
   return issue;
  }
  public void setSender(Sender sender){
   this.sender=sender;
  }
  public Sender getSender(){
   return sender;
  }
  public void setAction(String action){
   this.action=action;
  }
  public String getAction(){
   return action;
  }
  public void setComment(Comment comment){
   this.comment=comment;
  }
  public Comment getComment(){
   return comment;
  }
  public void setRepository(Repository repository){
   this.repository=repository;
  }
  public Repository getRepository(){
   return repository;
  }
}