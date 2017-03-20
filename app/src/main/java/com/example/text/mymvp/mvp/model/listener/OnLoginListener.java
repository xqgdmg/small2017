package com.example.text.mymvp.mvp.model.listener;

import com.example.text.mymvp.bean.User;

/**
 * Created by Chris on 2017/3/15.
 * 回调属于 Model层 中的部分用于外界当做参数传入，接收数据
 * 不要忽略了她的作用
 */
public interface OnLoginListener {

    void loginSuccess(User user);

    void loginFailed();
}
