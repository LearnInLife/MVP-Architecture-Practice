package com.example.test.mvptest.ui.feed.opensource;

import com.example.test.mvptest.ui.base.MvpPresenter;
import com.example.test.mvptest.ui.feed.blog.BlogMvpView;

/**
 * Created by longzhijun on 2018/1/9.
 */

public interface OpenSourceMvpPresenter<V extends OpenSourceMvpView> extends MvpPresenter<V>{

    void onViewPrepared();
}
