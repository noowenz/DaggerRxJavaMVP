package com.official.noowenz.daggerrxjavamvp.retrofit;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by nabin on 9/2/16.
 */
public class ErrorResponse {
    @SerializedName("message")
    @Expose
    public String message;
}
