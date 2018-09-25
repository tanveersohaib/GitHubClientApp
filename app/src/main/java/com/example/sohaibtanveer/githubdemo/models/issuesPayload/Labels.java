package com.example.sohaibtanveer.githubdemo.models.issuesPayload;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
/**
 * Awesome Pojo Generator
 * */
public class Labels{
  @SerializedName("default")
  @Expose
  private Boolean isdefault;
  @SerializedName("color")
  @Expose
  private String color;
  @SerializedName("name")
  @Expose
  private String name;
  @SerializedName("id")
  @Expose
  private Integer id;
  @SerializedName("url")
  @Expose
  private String url;
  @SerializedName("node_id")
  @Expose
  private String node_id;
  public void setDefault(Boolean isdefault){
   this.isdefault=isdefault;
  }
  public Boolean getDefault(){
   return isdefault;
  }
  public void setColor(String color){
   this.color=color;
  }
  public String getColor(){
   return color;
  }
  public void setName(String name){
   this.name=name;
  }
  public String getName(){
   return name;
  }
  public void setId(Integer id){
   this.id=id;
  }
  public Integer getId(){
   return id;
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