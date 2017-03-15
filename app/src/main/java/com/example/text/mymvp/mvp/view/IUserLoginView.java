package com.example.text.mymvp.mvp.view;

import com.example.text.mymvp.bean.User;

/**
 * Created by Chris on 2017/3/15.
 */
public interface IUserLoginView {
    String getUserName();

    String getPassword();

    void clearUserName();

    void clearPassword();

    void showLoading();

    void hideLoading();

    void toMainActivity(User user);

    void showFailedError();

}
