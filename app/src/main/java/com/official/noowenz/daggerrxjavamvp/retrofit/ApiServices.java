package com.official.noowenz.daggerrxjavamvp.retrofit;

import com.official.noowenz.daggerrxjavamvp.RegisterModule.model.RetrofitResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;


public interface ApiServices {
    @FormUrlEncoded
    @POST("register")
    Call<RetrofitResponse> registerRequest(
            @Field("name") String name,
            @Field("email") String email,
            @Field("password") String password,
            @Field("conform_password") String conform_password,
            @Field("address") String address);
}
