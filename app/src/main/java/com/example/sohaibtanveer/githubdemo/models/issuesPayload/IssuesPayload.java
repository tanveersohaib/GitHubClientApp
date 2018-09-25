package com.example.sohaibtanveer.githubdemo.models.issuesPayload;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
/**
 * Awesome Pojo Generator
 * */
public class IssuesPayload{
  @SerializedName("issue")
  @Expose
  private Issue issue;
  @SerializedName("sender")
  @Expose
  private Sender sender;
  @SerializedName("changes")
  @Expose
  private Changes changes;
  @SerializedName("action")
  @Expose
  private String action;
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
  public void setChanges(Changes changes){
   this.changes=changes;
  }
  public Changes getChanges(){
   return changes;
  }
  public void setAction(String action){
   this.action=action;
  }
  public String getAction(){
   return action;
  }
  public void setRepository(Repository repository){
   this.repository=repository;
  }
  public Repository getRepository(){
   return repository;
  }
}