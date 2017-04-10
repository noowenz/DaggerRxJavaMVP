package com.official.noowenz.daggerrxjavamvp.injection.component;


import android.content.res.Resources;

import com.official.noowenz.daggerrxjavamvp.injection.module.AppModule;
import com.official.noowenz.daggerrxjavamvp.injection.module.NetModule;
import com.official.noowenz.daggerrxjavamvp.retrofit.ApiServices;

import javax.inject.Singleton;

import dagger.Component;
import retrofit2.Retrofit;

@Singleton
@Component(modules = {AppModule.class, NetModule.class})
public interface NetComponent {
    ApiServices apiInterface();
    Resources resources();
    Retrofit retrofit();
}
