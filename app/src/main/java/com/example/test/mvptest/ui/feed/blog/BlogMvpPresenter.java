package com.example.test.mvptest.ui.feed.blog;

import com.example.test.mvptest.ui.base.MvpPresenter;

/**
 * Created by longzhijun on 2018/1/9.
 */

public interface BlogMvpPresenter<V extends BlogMvpView> extends MvpPresenter<V>{

    void onViewPrepared();
}
