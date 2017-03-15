package com.example.text.mymvp.mvp.model;

/**
 * Created by Chris on 2017/3/15.
 */
public interface IUserBiz {
    public void login(String username, String password, OnLoginListener loginListener);
}
