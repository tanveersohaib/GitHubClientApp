package com.example.sohaibtanveer.githubdemo.models.issuesPayload;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;
/**
 * Awesome Pojo Generator
 * */
public class Issue{
  @SerializedName("comments")
  @Expose
  private Integer comments;
  @SerializedName("closed_at")
  @Expose
  private Object closed_at;
  @SerializedName("assignees")
  @Expose
  private Object assignees;
  @SerializedName("created_at")
  @Expose
  private String created_at;
  @SerializedName("title")
  @Expose
  private String title;
  @SerializedName("body")
  @Expose
  private String body;
  @SerializedName("url")
  @Expose
  private String url;
  @SerializedName("labels")
  @Expose
  private List<Labels> labels;
  @SerializedName("labels_url")
  @Expose
  private String labels_url;
  @SerializedName("author_association")
  @Expose
  private String author_association;
  @SerializedName("number")
  @Expose
  private Integer number;
  @SerializedName("milestone")
  @Expose
  private Object milestone;
  @SerializedName("updated_at")
  @Expose
  private String updated_at;
  @SerializedName("events_url")
  @Expose
  private String events_url;
  @SerializedName("html_url")
  @Expose
  private String html_url;
  @SerializedName("comments_url")
  @Expose
  private String comments_url;
  @SerializedName("repository_url")
  @Expose
  private String repository_url;
  @SerializedName("id")
  @Expose
  private Integer id;
  @SerializedName("state")
  @Expose
  private String state;
  @SerializedName("assignee")
  @Expose
  private Object assignee;
  @SerializedName("locked")
  @Expose
  private Boolean locked;
  @SerializedName("user")
  @Expose
  private User user;
  @SerializedName("node_id")
  @Expose
  private String node_id;
  public void setComments(Integer comments){
   this.comments=comments;
  }
  public Integer getComments(){
   return comments;
  }
  public void setClosed_at(Object closed_at){
   this.closed_at=closed_at;
  }
  public Object getClosed_at(){
   return closed_at;
  }
  public void setAssignees(Object assignees){
   this.assignees=assignees;
  }
  public Object getAssignees(){
   return assignees;
  }
  public void setCreated_at(String created_at){
   this.created_at=created_at;
  }
  public String getCreated_at(){
   return created_at;
  }
  public void setTitle(String title){
   this.title=title;
  }
  public String getTitle(){
   return title;
  }
  public void setBody(String body){
   this.body=body;
  }
  public String getBody(){
   return body;
  }
  public void setUrl(String url){
   this.url=url;
  }
  public String getUrl(){
   return url;
  }
  public void setLabels(List<Labels> labels){
   this.labels=labels;
  }
  public List<Labels> getLabels(){
   return labels;
  }
  public void setLabels_url(String labels_url){
   this.labels_url=labels_url;
  }
  public String getLabels_url(){
   return labels_url;
  }
  public void setAuthor_association(String author_association){
   this.author_association=author_association;
  }
  public String getAuthor_association(){
   return author_association;
  }
  public void setNumber(Integer number){
   this.number=number;
  }
  public Integer getNumber(){
   return number;
  }
  public void setMilestone(Object milestone){
   this.milestone=milestone;
  }
  public Object getMilestone(){
   return milestone;
  }
  public void setUpdated_at(String updated_at){
   this.updated_at=updated_at;
  }
  public String getUpdated_at(){
   return updated_at;
  }
  public void setEvents_url(String events_url){
   this.events_url=events_url;
  }
  public String getEvents_url(){
   return events_url;
  }
  public void setHtml_url(String html_url){
   this.html_url=html_url;
  }
  public String getHtml_url(){
   return html_url;
  }
  public void setComments_url(String comments_url){
   this.comments_url=comments_url;
  }
  public String getComments_url(){
   return comments_url;
  }
  public void setRepository_url(String repository_url){
   this.repository_url=repository_url;
  }
  public String getRepository_url(){
   return repository_url;
  }
  public void setId(Integer id){
   this.id=id;
  }
  public Integer getId(){
   return id;
  }
  public void setState(String state){
   this.state=state;
  }
  public String getState(){
   return state;
  }
  public void setAssignee(Object assignee){
   this.assignee=assignee;
  }
  public Object getAssignee(){
   return assignee;
  }
  public void setLocked(Boolean locked){
   this.locked=locked;
  }
  public Boolean getLocked(){
   return locked;
  }
  public void setUser(User user){
   this.user=user;
  }
  public User getUser(){
   return user;
  }
  public void setNode_id(String node_id){
   this.node_id=node_id;
  }
  public String getNode_id(){
   return node_id;
  }
}