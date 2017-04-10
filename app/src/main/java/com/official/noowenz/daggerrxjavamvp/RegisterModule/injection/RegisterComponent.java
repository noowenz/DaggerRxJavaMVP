package com.official.noowenz.daggerrxjavamvp.RegisterModule.injection;

import com.official.noowenz.daggerrxjavamvp.RegisterModule.view.RegisterActivity;
import com.official.noowenz.daggerrxjavamvp.injection.ActivityScope;
import com.official.noowenz.daggerrxjavamvp.injection.component.NetComponent;

import dagger.Component;

@ActivityScope
@Component(dependencies = NetComponent.class, modules = RegisterModule.class)
public interface RegisterComponent {
    void inject(RegisterActivity activity);
}
