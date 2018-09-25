package com.example.sohaibtanveer.githubdemo.models;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
/**
 * Awesome Pojo Generator
 * */
public class Event{
  @SerializedName("actor")
  @Expose
  private Actor actor;
  @SerializedName("public")
  @Expose
  private Boolean isPublic;
  @SerializedName("payload")
  @Expose
  private Object payload;
  @SerializedName("org")
  @Expose
  private Org org;
  @SerializedName("repo")
  @Expose
  private Repo repo;
  @SerializedName("created_at")
  @Expose
  private String created_at;
  @SerializedName("id")
  @Expose
  private Long id;
  @SerializedName("type")
  @Expose
  private String type;
  public void setActor(Actor actor){
   this.actor=actor;
  }
  public Actor getActor(){
   return actor;
  }
  public void setisPublic(Boolean isPublic){
   this.isPublic=isPublic;
  }
  public Boolean getisPublic(){
   return isPublic;
  }
  public void setPayload(Object payload){
   this.payload=payload;
  }
  public Object getPayload(){
   return payload;
  }
  public void setOrg(Org org){
   this.org=org;
  }
  public Org getOrg(){
   return org;
  }
  public void setRepo(Repo repo){
   this.repo=repo;
  }
  public Repo getRepo(){
   return repo;
  }
  public void setCreated_at(String created_at){
   this.created_at=created_at;
  }
  public String getCreated_at(){
   return created_at;
  }
  public void setId(Long id){
   this.id=id;
  }
  public Long getId(){
   return id;
  }
  public void setType(String type){
   this.type=type;
  }
  public String getType(){
   return type;
  }
}