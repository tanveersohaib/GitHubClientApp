package com.example.sohaibtanveer.githubdemo.models;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
/**
 * Awesome Pojo Generator
 * */
public class Actor{
  @SerializedName("avatar_url")
  @Expose
  private String avatar_url;
  @SerializedName("id")
  @Expose
  private Integer id;
  @SerializedName("login")
  @Expose
  private String login;
  @SerializedName("gravatar_id")
  @Expose
  private String gravatar_id;
  @SerializedName("url")
  @Expose
  private String url;
  public void setAvatar_url(String avatar_url){
   this.avatar_url=avatar_url;
  }
  public String getAvatar_url(){
   return avatar_url;
  }
  public void setId(Integer id){
   this.id=id;
  }
  public Integer getId(){
   return id;
  }
  public void setLogin(String login){
   this.login=login;
  }
  public String getLogin(){
   return login;
  }
  public void setGravatar_id(String gravatar_id){
   this.gravatar_id=gravatar_id;
  }
  public String getGravatar_id(){
   return gravatar_id;
  }
  public void setUrl(String url){
   this.url=url;
  }
  public String getUrl(){
   return url;
  }
}