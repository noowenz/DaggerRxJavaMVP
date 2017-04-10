package com.official.noowenz.daggerrxjavamvp.RegisterModule.view;

/**
 * Created by nabin on 4/10/2017.
 */

public interface IRegisterView {
    void onValidationSuccess(String name, String email, String password, String conform_password, String address);
    void onValidationError(String error);
    void onRegisterSuccess();
    void onError(String error);
}
