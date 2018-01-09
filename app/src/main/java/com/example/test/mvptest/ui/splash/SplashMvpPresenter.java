package com.example.test.mvptest.ui.splash;

import com.example.test.mvptest.di.PerActivity;
import com.example.test.mvptest.ui.base.MvpPresenter;

/**
 * Created by longzhijun on 2017/11/17.
 */
@PerActivity
public interface SplashMvpPresenter<V extends SplashMvpView> extends MvpPresenter<V> {
}
