package com.example.test.mvptest.ui.feed.blog;

import com.androidnetworking.error.ANError;
import com.example.test.mvptest.data.DataManager;
import com.example.test.mvptest.data.network.module.BlogResponse;
import com.example.test.mvptest.ui.base.BasePresenter;
import com.example.test.mvptest.utils.rx.SchedulerProvider;

import javax.inject.Inject;

import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;

/**
 * Created by longzhijun on 2018/1/9.
 */

public class BlogPresenter<V extends BlogMvpView> extends BasePresenter<V> implements BlogMvpPresenter<V> {
    @Inject
    public BlogPresenter(DataManager dataManager, SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }

    @Override
    public void onViewPrepared() {
        getMvpView().showLoading();

        getCompositeDisposable().add(getDataManager()
                .getBlogApiCall()
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(new Consumer<BlogResponse>() {
                    @Override
                    public void accept(@NonNull BlogResponse blogResponse) throws Exception {

                        if (blogResponse!=null && blogResponse.getData() != null) {
                            getMvpView().updateBlog(blogResponse.getData());
                        }

                        getMvpView().hideLoading();
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable) throws Exception {
                        if (!isViewAttached()) {
                            return;
                        }

                        getMvpView().hideLoading();

                        // handle the error here
                        if (throwable instanceof ANError) {
                            ANError anError = (ANError) throwable;
                            handleApiError(anError);
                        }
                    }
                }));

    }
}
