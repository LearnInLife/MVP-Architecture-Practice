package com.example.test.mvptest.ui.feed;

import com.example.test.mvptest.data.DataManager;
import com.example.test.mvptest.ui.base.BasePresenter;
import com.example.test.mvptest.ui.base.MvpView;
import com.example.test.mvptest.utils.rx.SchedulerProvider;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by longzhijun on 2018/1/8.
 */

public class FeedPresenter<V extends MvpView> extends BasePresenter<V> implements FeedMvpPresenter<V> {
    @Inject
    public FeedPresenter(DataManager dataManager, SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }
}
