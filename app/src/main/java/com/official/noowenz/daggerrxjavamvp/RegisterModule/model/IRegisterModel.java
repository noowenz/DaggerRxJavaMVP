package com.official.noowenz.daggerrxjavamvp.registerModule.model;

/**
 * Created by nabin on 4/10/2017.
 */

public interface IRegisterModel {
    void registerUser(String name, String email, String password, String conform_password, String address);
}
