package com.example.text.mymvp.mvp.model;

import com.example.text.mymvp.mvp.model.listener.OnLoginListener;

/**
 * Created by Chris on 2017/3/15.
 * Model层 通过 OnLoginListener 回调给外界信息
 */
public interface IUserModel {

    public void login(String username, String password, OnLoginListener loginListener);
}
