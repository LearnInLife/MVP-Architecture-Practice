package com.example.test.mvptest.ui.Login;

import com.example.test.mvptest.di.PerActivity;
import com.example.test.mvptest.ui.base.MvpPresenter;

/**
 * Created by longzhijun on 2018/1/2.
 */
@PerActivity
public interface LoginMvpPresenter <V extends LoginMvpView> extends MvpPresenter<V> {
    void onServerLoginClick(String email,String password);

    void onGoogleLoginClick();

    void onFacebookLoginClick();
}
