package com.official.noowenz.daggerrxjavamvp.registerModule.injection;


import android.content.res.Resources;

import com.official.noowenz.daggerrxjavamvp.registerModule.model.RegisterModelImpl;
import com.official.noowenz.daggerrxjavamvp.registerModule.view.IRegisterView;
import com.official.noowenz.daggerrxjavamvp.injection.ActivityScope;
import com.official.noowenz.daggerrxjavamvp.retrofit.ApiServices;

import dagger.Module;
import dagger.Provides;

@Module
public class RegisterModule {
    private final IRegisterView iIRegisterView;

    public RegisterModule(IRegisterView iIRegisterView) {
        this.iIRegisterView = iIRegisterView;
    }

    @Provides
    @ActivityScope
    IRegisterView providesIRegisterView() {
        return iIRegisterView;
    }

    @Provides
    @ActivityScope
    RegisterModelImpl providesRegisterModelImpl(ApiServices apiServices, Resources resources) {
        return new RegisterModelImpl(apiServices, resources);
    }
}
