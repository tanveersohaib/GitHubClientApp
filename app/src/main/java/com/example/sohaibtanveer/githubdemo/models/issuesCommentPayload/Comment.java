package com.example.sohaibtanveer.githubdemo.models.issuesCommentPayload;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
/**
 * Awesome Pojo Generator
 * */
public class Comment{
  @SerializedName("author_association")
  @Expose
  private String author_association;
  @SerializedName("issue_url")
  @Expose
  private String issue_url;
  @SerializedName("updated_at")
  @Expose
  private String updated_at;
  @SerializedName("html_url")
  @Expose
  private String html_url;
  @SerializedName("created_at")
  @Expose
  private String created_at;
  @SerializedName("id")
  @Expose
  private Integer id;
  @SerializedName("body")
  @Expose
  private String body;
  @SerializedName("user")
  @Expose
  private User user;
  @SerializedName("url")
  @Expose
  private String url;
  @SerializedName("node_id")
  @Expose
  private String node_id;
  public void setAuthor_association(String author_association){
   this.author_association=author_association;
  }
  public String getAuthor_association(){
   return author_association;
  }
  public void setIssue_url(String issue_url){
   this.issue_url=issue_url;
  }
  public String getIssue_url(){
   return issue_url;
  }
  public void setUpdated_at(String updated_at){
   this.updated_at=updated_at;
  }
  public String getUpdated_at(){
   return updated_at;
  }
  public void setHtml_url(String html_url){
   this.html_url=html_url;
  }
  public String getHtml_url(){
   return html_url;
  }
  public void setCreated_at(String created_at){
   this.created_at=created_at;
  }
  public String getCreated_at(){
   return created_at;
  }
  public void setId(Integer id){
   this.id=id;
  }
  public Integer getId(){
   return id;
  }
  public void setBody(String body){
   this.body=body;
  }
  public String getBody(){
   return body;
  }
  public void setUser(User user){
   this.user=user;
  }
  public User getUser(){
   return user;
  }
  public void setUrl(String url){
   this.url=url;
  }
  public String getUrl(){
   return url;
  }
  public void setNode_id(String node_id){
   this.node_id=node_id;
  }
  public String getNode_id(){
   return node_id;
  }
}