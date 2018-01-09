package com.example.test.mvptest.ui.splash;

import com.example.test.mvptest.R;
import com.example.test.mvptest.data.DataManager;
import com.example.test.mvptest.ui.base.BasePresenter;
import com.example.test.mvptest.utils.rx.SchedulerProvider;

import javax.inject.Inject;

import io.reactivex.ObservableSource;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

/**
 * Created by longzhijun on 2017/11/17.
 */

public class SplashPresenter<V extends SplashMvpView> extends BasePresenter<V> implements SplashMvpPresenter<V> {


    @Inject
    public SplashPresenter(DataManager dataManager, SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }

    @Override
    public void onAttach(V mvpview) {
        super.onAttach(mvpview);

        getMvpView().startSyncService();

        getCompositeDisposable().add(getDataManager()
                .seedDatabaseQuestions()
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .concatMap(new Function<Boolean, ObservableSource<Boolean>>() {
                    @Override
                    public ObservableSource<Boolean> apply(@NonNull Boolean aBoolean) throws Exception {
                        return getDataManager().seedDatabaseOptions();
                    }
                })
                .subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(@NonNull Boolean aBoolean) throws Exception {
                        if (!isViewAttached())
                            return;
                        decideNextActivity();
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable) throws Exception {
                        if (!isViewAttached()) {
                            return;
                        }
                        getMvpView().onError(R.string.some_error);
                        decideNextActivity();
                    }
                }));
    }
    private void decideNextActivity() {
        if (getDataManager().getCurrentUserLoggedInMode() == DataManager.LoggedInMode.LOGGED_IN_MODE_LOGGED_OUT.getType()) {
            getMvpView().openLoginActivity();
        } else {
            getMvpView().openMainActivity();
        }
    }
}
