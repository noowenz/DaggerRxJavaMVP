package com.official.noowenz.daggerrxjavamvp.utils;

import android.app.Application;
import android.content.Context;

import com.official.noowenz.daggerrxjavamvp.injection.component.DaggerNetComponent;
import com.official.noowenz.daggerrxjavamvp.injection.component.NetComponent;
import com.official.noowenz.daggerrxjavamvp.injection.module.AppModule;
import com.official.noowenz.daggerrxjavamvp.injection.module.NetModule;

/**
 * Created by nabin on 9/9/16.
 */
public class MyApplication extends Application {
    private NetComponent mNetComponent;

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
    }

    @Override
    public void onCreate() {
        super.onCreate();

        mNetComponent = DaggerNetComponent.builder()
                .appModule(new AppModule(this))
                .netModule(new NetModule("https://appstaging.crewgo.co/staff/staff_webservice/"))
                .build();
    }

    public NetComponent getNetComponent() {
        return mNetComponent;
    }

}
