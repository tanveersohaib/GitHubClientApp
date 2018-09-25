package com.example.sohaibtanveer.githubdemo.util;

import com.example.sohaibtanveer.githubdemo.models.AccessTokenPOJO;
import com.example.sohaibtanveer.githubdemo.models.DirectoryPOJO;
import com.example.sohaibtanveer.githubdemo.models.Event;
import com.example.sohaibtanveer.githubdemo.models.ReadmePOJO;
import com.example.sohaibtanveer.githubdemo.models.RepoBranchPOJO;
import com.example.sohaibtanveer.githubdemo.models.RepoSearchResultPOJO;
import com.example.sohaibtanveer.githubdemo.models.TagsPOJO;
import com.example.sohaibtanveer.githubdemo.models.UserPOJO;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.Url;

public interface GitHubService {

    @GET("/search/repositories?")
    Call<RepoSearchResultPOJO> getRepositorySearchResults(@Query("q") String repository, @Query("access_token") String accessToken);

    @FormUrlEncoded
    @Headers("Accept: application/json")
    @POST("/login/oauth/access_token")
    Call<AccessTokenPOJO> getAccessToken(@Field("client_id") String clientId, @Field("client_secret") String clientSecret,
                                         @Field("code") String code);

    @GET("/user?")
    Call<UserPOJO> getUser(@Query("access_token") String token);

    @GET
    Call<ReadmePOJO> getReadmeObject(@Url String url, @Query("access_token") String accessToken);

    @GET
    Call<List<DirectoryPOJO>> getFiles(@Url String url, @Query("ref") String ref, @Query("access_token") String accessToken);

    @GET
    Call<List<RepoBranchPOJO>> getBranches(@Url String url, @Query("access_token") String access_token);

    @GET
    Call<List<TagsPOJO>> getTags(@Url String url, @Query("access_token") String access_token);

    @GET
    Call<List<Event>> getFeeds(@Url String url, @Query("access_token") String access_token);
}
