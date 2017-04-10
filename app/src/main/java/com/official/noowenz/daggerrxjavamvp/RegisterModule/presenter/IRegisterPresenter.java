package com.official.noowenz.daggerrxjavamvp.RegisterModule.presenter;

/**
 * Created by ebpearls on 4/10/2017.
 */

public interface IRegisterPresenter {
    void validateRegister(String name, String email, String password, String conform_password, String address);
    void registerSuccess();
    void registerError(String error);
}
