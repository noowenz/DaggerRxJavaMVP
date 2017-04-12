package com.official.noowenz.daggerrxjavamvp.registerModule.model;

import android.content.res.Resources;

import com.official.noowenz.daggerrxjavamvp.retrofit.ApiServices;

import javax.inject.Inject;

/**
 * Created by nabin on 4/10/2017.
 */

public class RegisterModelImpl {
    ApiServices apiService;
    Resources resources;

    @Inject
    public RegisterModelImpl(ApiServices apiServices, Resources resources) {
        this.apiService = apiServices;
        this.resources = resources;
    }

}
