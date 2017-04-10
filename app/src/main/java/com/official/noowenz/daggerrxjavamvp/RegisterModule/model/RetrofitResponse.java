package com.official.noowenz.daggerrxjavamvp.RegisterModule.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RetrofitResponse {

    @SerializedName("code")
    @Expose
    public String code;
    @SerializedName("msg")
    @Expose
    public String msg;
}