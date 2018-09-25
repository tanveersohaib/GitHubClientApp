package com.example.sohaibtanveer.githubdemo.models.pushPayload;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
/**
 * Awesome Pojo Generator
 * */
public class Owner{
  @SerializedName("gists_url")
  @Expose
  private String gists_url;
  @SerializedName("repos_url")
  @Expose
  private String repos_url;
  @SerializedName("following_url")
  @Expose
  private String following_url;
  @SerializedName("starred_url")
  @Expose
  private String starred_url;
  @SerializedName("login")
  @Expose
  private String login;
  @SerializedName("followers_url")
  @Expose
  private String followers_url;
  @SerializedName("type")
  @Expose
  private String type;
  @SerializedName("url")
  @Expose
  private String url;
  @SerializedName("subscriptions_url")
  @Expose
  private String subscriptions_url;
  @SerializedName("received_events_url")
  @Expose
  private String received_events_url;
  @SerializedName("avatar_url")
  @Expose
  private String avatar_url;
  @SerializedName("events_url")
  @Expose
  private String events_url;
  @SerializedName("html_url")
  @Expose
  private String html_url;
  @SerializedName("name")
  @Expose
  private String name;
  @SerializedName("site_admin")
  @Expose
  private Boolean site_admin;
  @SerializedName("id")
  @Expose
  private Integer id;
  @SerializedName("gravatar_id")
  @Expose
  private String gravatar_id;
  @SerializedName("email")
  @Expose
  private String email;
  @SerializedName("node_id")
  @Expose
  private String node_id;
  @SerializedName("organizations_url")
  @Expose
  private String organizations_url;
  public void setGists_url(String gists_url){
   this.gists_url=gists_url;
  }
  public String getGists_url(){
   return gists_url;
  }
  public void setRepos_url(String repos_url){
   this.repos_url=repos_url;
  }
  public String getRepos_url(){
   return repos_url;
  }
  public void setFollowing_url(String following_url){
   this.following_url=following_url;
  }
  public String getFollowing_url(){
   return following_url;
  }
  public void setStarred_url(String starred_url){
   this.starred_url=starred_url;
  }
  public String getStarred_url(){
   return starred_url;
  }
  public void setLogin(String login){
   this.login=login;
  }
  public String getLogin(){
   return login;
  }
  public void setFollowers_url(String followers_url){
   this.followers_url=followers_url;
  }
  public String getFollowers_url(){
   return followers_url;
  }
  public void setType(String type){
   this.type=type;
  }
  public String getType(){
   return type;
  }
  public void setUrl(String url){
   this.url=url;
  }
  public String getUrl(){
   return url;
  }
  public void setSubscriptions_url(String subscriptions_url){
   this.subscriptions_url=subscriptions_url;
  }
  public String getSubscriptions_url(){
   return subscriptions_url;
  }
  public void setReceived_events_url(String received_events_url){
   this.received_events_url=received_events_url;
  }
  public String getReceived_events_url(){
   return received_events_url;
  }
  public void setAvatar_url(String avatar_url){
   this.avatar_url=avatar_url;
  }
  public String getAvatar_url(){
   return avatar_url;
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
  public void setName(String name){
   this.name=name;
  }
  public String getName(){
   return name;
  }
  public void setSite_admin(Boolean site_admin){
   this.site_admin=site_admin;
  }
  public Boolean getSite_admin(){
   return site_admin;
  }
  public void setId(Integer id){
   this.id=id;
  }
  public Integer getId(){
   return id;
  }
  public void setGravatar_id(String gravatar_id){
   this.gravatar_id=gravatar_id;
  }
  public String getGravatar_id(){
   return gravatar_id;
  }
  public void setEmail(String email){
   this.email=email;
  }
  public String getEmail(){
   return email;
  }
  public void setNode_id(String node_id){
   this.node_id=node_id;
  }
  public String getNode_id(){
   return node_id;
  }
  public void setOrganizations_url(String organizations_url){
   this.organizations_url=organizations_url;
  }
  public String getOrganizations_url(){
   return organizations_url;
  }
}