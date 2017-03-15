package com.example.text.mymvp.mvp.model;

import com.example.text.mymvp.bean.User;

/**
 * Created by Chris on 2017/3/15.
 */
public interface OnLoginListener {
    void loginSuccess(User user);

    void loginFailed();
}
