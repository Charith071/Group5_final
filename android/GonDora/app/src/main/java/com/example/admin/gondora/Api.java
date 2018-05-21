package com.example.admin.gondora;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface Api {

    @POST("api/signup")
    Call<ResponseBody> CreateAccount(@Body RequestBody requestBody);
}
