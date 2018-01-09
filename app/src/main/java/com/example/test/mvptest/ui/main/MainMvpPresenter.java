package com.example.test.mvptest.ui.main;

import com.example.test.mvptest.ui.base.MvpPresenter;

/**
 * Created by longzhijun on 2018/1/4.
 */

public interface MainMvpPresenter<V extends MainMvpView> extends MvpPresenter<V> {

    void onDrawerOptionAboutClick();

    void onDrawerOptionLogoutClick();

    void onDrawerRateUsClick();

    void onDrawerMyFeedClick();

    void onNavMenuCreated();

    void onCardExhausted();

    void onViewInitialized();
}
