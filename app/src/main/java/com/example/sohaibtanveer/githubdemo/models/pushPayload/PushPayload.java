package com.example.sohaibtanveer.githubdemo.models.pushPayload;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;
/**
 * Awesome Pojo Generator
 * */
public class PushPayload {
  @SerializedName("compare")
  @Expose
  private String compare;
  @SerializedName("head_commit")
  @Expose
  private Object head_commit;
  @SerializedName("pusher")
  @Expose
  private Pusher pusher;
  @SerializedName("before")
  @Expose
  private String before;
  @SerializedName("created")
  @Expose
  private Boolean created;
  @SerializedName("forced")
  @Expose
  private Boolean forced;
  @SerializedName("base_ref")
  @Expose
  private Object base_ref;
  @SerializedName("repository")
  @Expose
  private Repository repository;
  @SerializedName("ref")
  @Expose
  private String ref;
  @SerializedName("deleted")
  @Expose
  private Boolean deleted;
  @SerializedName("sender")
  @Expose
  private Sender sender;
  @SerializedName("commits")
  @Expose
  private List<Commits> commits;
  @SerializedName("after")
  @Expose
  private Integer after;
  public void setCompare(String compare){
   this.compare=compare;
  }
  public String getCompare(){
   return compare;
  }
  public void setHead_commit(Object head_commit){
   this.head_commit=head_commit;
  }
  public Object getHead_commit(){
   return head_commit;
  }
  public void setPusher(Pusher pusher){
   this.pusher=pusher;
  }
  public Pusher getPusher(){
   return pusher;
  }
  public void setBefore(String before){
   this.before=before;
  }
  public String getBefore(){
   return before;
  }
  public void setCreated(Boolean created){
   this.created=created;
  }
  public Boolean getCreated(){
   return created;
  }
  public void setForced(Boolean forced){
   this.forced=forced;
  }
  public Boolean getForced(){
   return forced;
  }
  public void setBase_ref(Object base_ref){
   this.base_ref=base_ref;
  }
  public Object getBase_ref(){
   return base_ref;
  }
  public void setRepository(Repository repository){
   this.repository=repository;
  }
  public Repository getRepository(){
   return repository;
  }
  public void setRef(String ref){
   this.ref=ref;
  }
  public String getRef(){
   return ref;
  }
  public void setDeleted(Boolean deleted){
   this.deleted=deleted;
  }
  public Boolean getDeleted(){
   return deleted;
  }
  public void setSender(Sender sender){
   this.sender=sender;
  }
  public Sender getSender(){
   return sender;
  }
  public void setCommits(List<Commits> commits){
   this.commits=commits;
  }
  public List<Commits> getCommits(){
   return commits;
  }
  public void setAfter(Integer after){
   this.after=after;
  }
  public Integer getAfter(){
   return after;
  }
}