package com.example.test.mvptest.ui.Login;

import android.text.TextUtils;

import com.androidnetworking.error.ANError;
import com.example.test.mvptest.R;
import com.example.test.mvptest.data.DataManager;
import com.example.test.mvptest.data.network.module.LoginRequest;
import com.example.test.mvptest.data.network.module.LoginResponse;
import com.example.test.mvptest.ui.base.BasePresenter;
import com.example.test.mvptest.utils.CommonUtils;
import com.example.test.mvptest.utils.rx.SchedulerProvider;

import javax.inject.Inject;

import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;

/**
 * Created by longzhijun on 2018/1/3.
 */

public class LoginPresenter <V extends LoginMvpView> extends BasePresenter<V> implements LoginMvpPresenter<V>{

    @Inject
    public LoginPresenter(DataManager dataManager, SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }

    @Override
    public void onServerLoginClick(String email, String password) {

        if (email == null || email.isEmpty()) {
            getMvpView().onError(R.string.empty_email);
            return;
        }

        if (!CommonUtils.isEmailValid(email)) {
            getMvpView().onError(R.string.invalid_email);
            return;
        }

        if (password == null || password.isEmpty()) {
            getMvpView().onError(R.string.empty_password);
            return;
        }

        getMvpView().showLoading();

        getCompositeDisposable().add(getDataManager()
                .doServerLoginApiCall(new LoginRequest.ServerLoginRequest(email,password))
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(new Consumer<LoginResponse>() {
                    @Override
                    public void accept(@NonNull LoginResponse loginResponse) throws Exception {
                        getDataManager().updateUserInfo(
                                loginResponse.getAccessToken(),
                                loginResponse.getUserId(),
                                DataManager.LoggedInMode.LOGGED_IN_MODE_SERVER,
                                loginResponse.getUserName(),
                                loginResponse.getUserEmail(),
                                loginResponse.getServerProfilePicUrl()
                        );

                        if (!isViewAttached())
                            return;

                        getMvpView().hideLoading();
                        getMvpView().openMainActivity();
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
    public void onGoogleLoginClick() {
        getMvpView().showLoading();

        getCompositeDisposable().add(getDataManager()
                .doGoogleLoginApiCall(new LoginRequest.GoogleLoginRequest("test1","test1"))
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(new Consumer<LoginResponse>() {
                    @Override
                    public void accept(@NonNull LoginResponse loginResponse) throws Exception {
                        getDataManager().updateUserInfo(
                                loginResponse.getAccessToken(),
                                loginResponse.getUserId(),
                                DataManager.LoggedInMode.LOGGED_IN_MODE_GOOGLE,
                                loginResponse.getUserName(),
                                loginResponse.getUserEmail(),
                                loginResponse.getServerProfilePicUrl()
                        );

                        if (!isViewAttached())
                            return;

                        getMvpView().hideLoading();
                        getMvpView().openMainActivity();
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
    public void onFacebookLoginClick() {
        getMvpView().showLoading();

        getCompositeDisposable().add(getDataManager()
                .doFacebookLoginApiCall(new LoginRequest.FacebookLoginRequest("test3", "test4"))
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(new Consumer<LoginResponse>() {
                    @Override
                    public void accept(LoginResponse response) throws Exception {
                        getDataManager().updateUserInfo(
                                response.getAccessToken(),
                                response.getUserId(),
                                DataManager.LoggedInMode.LOGGED_IN_MODE_FB,
                                response.getUserName(),
                                response.getUserEmail(),
                                response.getGoogleProfilePicUrl());

                        if (!isViewAttached()) {
                            return;
                        }

                        getMvpView().hideLoading();
                        getMvpView().openMainActivity();
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {

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
}
