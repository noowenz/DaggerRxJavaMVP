package com.official.noowenz.daggerrxjavamvp.RegisterModule.presenter;

import android.app.Activity;
import android.content.res.Resources;

import com.official.noowenz.daggerrxjavamvp.RegisterModule.model.RegisterModelImpl;
import com.official.noowenz.daggerrxjavamvp.RegisterModule.view.IRegisterView;

import javax.inject.Inject;

/**
 * Created by nabin on 4/10/2017.
 */

public class RegisterPresenterImpl {
    IRegisterView iRegisterView;
    RegisterModelImpl registerModel;

    @Inject
    public RegisterPresenterImpl(IRegisterView iRegisterView, RegisterModelImpl registerModel, Resources resources) {
        this.iRegisterView = iRegisterView;
        this.registerModel = registerModel;
    }
}
