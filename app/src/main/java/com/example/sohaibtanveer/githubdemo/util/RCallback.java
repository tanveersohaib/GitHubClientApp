package com.example.sohaibtanveer.githubdemo.util;

import com.example.sohaibtanveer.githubdemo.models.ErrorBody;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public abstract class RCallback<T> implements Callback<T> {

    @Override
    public void onResponse(Call<T> call, Response<T> response) {
        if(response.isSuccessful()){
            success(response.body());
        }
        else{
            int code = response.code();
            try {
                Gson gson = new Gson();
                ErrorBody errorBody = gson.fromJson(response.errorBody().string(), ErrorBody.class);
                if(code == 400){
                    error(errorBody.getMessage());
                }
                else if(code == 422){
                    error(errorBody.getMessage());
                    //errors
                }
            }catch(Exception e){
                error("Unknown Error Occurred");
            }
        }

    }

    @Override
    public void onFailure(Call<T> call, Throwable t) {
        error(t.getMessage());
    }

    public abstract void success(T object);
    public abstract void error(String error);
}
