package com.example.sohaibtanveer.githubdemo.models.pushPayload;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
/**
 * Awesome Pojo Generator
 * */
public class Pusher{
  @SerializedName("name")
  @Expose
  private String name;
  @SerializedName("email")
  @Expose
  private String email;
  public void setName(String name){
   this.name=name;
  }
  public String getName(){
   return name;
  }
  public void setEmail(String email){
   this.email=email;
  }
  public String getEmail(){
   return email;
  }
}