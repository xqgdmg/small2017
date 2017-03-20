package com.example.text.mymvp.mvp.model;

import com.example.text.mymvp.mvp.model.listener.OnLoginListener;

/**
 * Created by Chris on 2017/3/15.
 * Model层 接口同样是实现逻辑而已
 */
public interface IUserModel {

    public void login(String username, String password, OnLoginListener loginListener);
}
