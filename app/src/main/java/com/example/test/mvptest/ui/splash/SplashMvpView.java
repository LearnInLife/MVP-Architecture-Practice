package com.example.test.mvptest.ui.splash;

import com.example.test.mvptest.ui.base.MvpView;

/**
 * Created by longzhijun on 2017/11/17.
 */

public interface SplashMvpView extends MvpView{

    void openLoginActivity();

    void openMainActivity();

    void startSyncService();

}
