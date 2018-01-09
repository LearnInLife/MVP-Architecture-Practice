package com.example.test.mvptest.ui.about;

import com.example.test.mvptest.data.DataManager;
import com.example.test.mvptest.ui.base.BasePresenter;
import com.example.test.mvptest.utils.rx.SchedulerProvider;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by longzhijun on 2018/1/5.
 */

public class AboutPresenter<V extends AboutMvpView> extends BasePresenter<V> implements AboutMvpPresenter<V> {
    @Inject
    public AboutPresenter(DataManager dataManager, SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }
}
