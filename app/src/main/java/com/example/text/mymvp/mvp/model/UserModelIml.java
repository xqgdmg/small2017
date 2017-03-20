package com.example.text.mymvp.mvp.model;

import com.example.text.mymvp.bean.User;
import com.example.text.mymvp.mvp.model.listener.OnLoginListener;

/**
 * Created by Chris on 2017/3/15.
 * Model 通过方法中的 回调，给外界返回信息
 */
public class UserModelIml implements IUserModel {
    @Override
    public void login(final String username, final String password, final OnLoginListener loginListener) {

        //模拟子线程耗时操作
        new Thread() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                //模拟登录成功
                if ("czx".equals(username) && "123".equals(password)) {
                    User user = new User();
                    user.setUsername(username);
                    user.setPassword(password);
                    loginListener.loginSuccess(user);
                } else {
                    loginListener.loginFailed();
                }
            }
        }.start();
    }
}
