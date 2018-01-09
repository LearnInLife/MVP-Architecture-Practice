package com.example.test.mvptest.ui.main;

import com.androidnetworking.error.ANError;
import com.example.test.mvptest.data.DataManager;
import com.example.test.mvptest.data.db.module.Question;
import com.example.test.mvptest.data.network.module.LogoutResponse;
import com.example.test.mvptest.ui.base.BasePresenter;
import com.example.test.mvptest.utils.rx.SchedulerProvider;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;

/**
 * Created by longzhijun on 2018/1/4.
 */

public class MainPresenter<V extends MainMvpView> extends BasePresenter <V> implements MainMvpPresenter<V>{
    @Inject
    public MainPresenter(DataManager dataManager, SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }

    @Override
    public void onDrawerOptionAboutClick() {
        getMvpView().closeNavigationDrawer();
        getMvpView().showAboutFragment();
    }

    @Override
    public void onDrawerOptionLogoutClick() {
        getMvpView().showLoading();

        getCompositeDisposable().add(getDataManager()
                .doLogoutApiCall()
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(new Consumer<LogoutResponse>() {
                    @Override
                    public void accept(@NonNull LogoutResponse logoutResponse) throws Exception {
                        if (!isViewAttached())
                            return;
                        getDataManager().setUserAsLoggedOut();
                        getMvpView().hideLoading();
                        getMvpView().openLoginActivity();
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable) throws Exception {
                        if (!isViewAttached()) {
                            return;
                        }

                        getMvpView().hideLoading();

                        // handle the login error here
                        if (throwable instanceof ANError) {
                            ANError anError = (ANError) throwable;
                            handleApiError(anError);
                        }
                    }
                }));
    }

    @Override
    public void onDrawerRateUsClick() {
        getMvpView().closeNavigationDrawer();
        getMvpView().showRateUsDialog();
    }

    @Override
    public void onDrawerMyFeedClick() {
        getMvpView().closeNavigationDrawer();
        getMvpView().openMyFeedActivity();
    }

    @Override
    public void onNavMenuCreated() {
        if (!isViewAttached())
            return;
        getMvpView().updateAppVersion();

        final String currentName = getDataManager().getCurrentUserName();
        if (currentName != null && !currentName.isEmpty()) {
            getMvpView().updateUserName(currentName);
        }

        final String currentUserEmail = getDataManager().getCurrentUserEmail();
        if (currentUserEmail != null && !currentUserEmail.isEmpty()) {
            getMvpView().updateUserEmail(currentUserEmail);
        }

        final String profilePicUrl = getDataManager().getCurrentUserProfilePicUrl();
        if (profilePicUrl != null && !profilePicUrl.isEmpty()) {
            getMvpView().updateUserProfilePic(profilePicUrl);
        }
    }

    @Override
    public void onCardExhausted() {
        getCompositeDisposable().add(getDataManager()
                .getAllQuestions()
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(new Consumer<List<Question>>() {
                    @Override
                    public void accept(@NonNull List<Question> questions) throws Exception {
                        if (!isViewAttached())
                            return;

                        if (questions != null)
                            getMvpView().reloadQuestionnaire(questions);
                    }
                }));
    }

    @Override
    public void onViewInitialized() {
        getCompositeDisposable().add(getDataManager()
                .getAllQuestions()
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(new Consumer<List<Question>>() {
                    @Override
                    public void accept(@NonNull List<Question> questions) throws Exception {
                        if (!isViewAttached())
                            return;

                        if (questions != null)
                            getMvpView().refreshQuestionnaire(questions);
                    }
                }));
    }
}
