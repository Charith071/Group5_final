package com.example.admin.myapplication2;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface Api {

    @POST("api/signin")
    Call<ResponseBody> login(@Body RequestBody requestBody);

    @GET("api")
    Call<ResponseBody> Home();
}
