package com.example.text.mymvp.mvp.model.listener;

import com.example.text.mymvp.bean.User;

/**
 * Created by Chris on 2017/3/15.
 * 回调属于 Model层 中的部分，
 */
public interface OnLoginListener {

    void loginSuccess(User user);

    void loginFailed();
}
