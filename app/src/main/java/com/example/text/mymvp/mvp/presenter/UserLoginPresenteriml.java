package com.example.text.mymvp.mvp.presenter;

import android.os.Handler;
import com.example.text.mymvp.bean.User;
import com.example.text.mymvp.mvp.model.IUserModel;
import com.example.text.mymvp.mvp.model.UserModelIml;
import com.example.text.mymvp.mvp.model.listener.OnLoginListener;
import com.example.text.mymvp.mvp.view.IUserLoginView;

/**
 * Created by Chris on 2017/3/15.
 * 这里也是直接写了一个类，为了体现接口变成和逻辑的清晰，写一个接口，一个类比较好
 */
public class UserLoginPresenterIml implements IUserLoginPresenter{
    private IUserModel iUserModel;
    private IUserLoginView iUserLoginView;
    private Handler mHandler = new Handler();

    /*
    * 构造方法参数传 View 层的接口
    * 在构造方法中 Model 层的实现类
    */
    public UserLoginPresenterIml(IUserLoginView userLoginView){
        this.iUserLoginView = userLoginView;
        this.iUserModel = new UserModelIml();
    }

    /******************************************上面的写法几乎是固定的***********************************************/

    /*
    * 实现要实现的功能（方法）
    */
    @Override
    public void login() {

        iUserLoginView.showLoading(); // View

         // 调用 Model 的方法，通常需要 View 处理
        iUserModel.login(iUserLoginView.getUserName(), iUserLoginView.getPassword(), new OnLoginListener(){
            @Override
            public void loginSuccess(final User user){
                //需要在UI线程执行！！！！登录是子线程模拟的
                mHandler.post(new Runnable(){
                    @Override
                    public void run(){
                        iUserLoginView.toMainActivity(user);
                        iUserLoginView.hideLoading();
                    }
                });

            }

            @Override
            public void loginFailed(){
                //需要在UI线程执行
                mHandler.post(new Runnable(){
                    @Override
                    public void run(){
                        iUserLoginView.showFailedError();
                        iUserLoginView.hideLoading();
                    }
                });

            }
        });
    }

    @Override
    public void clear() {
        iUserLoginView.clearUserName();
        iUserLoginView.clearPassword();
    }

}
