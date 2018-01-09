package com.example.test.mvptest.ui.base;

import android.support.annotation.StringRes;

/**
 * Created by longzhijun on 2017/11/15.
 */

public interface MvpView {
    void showLoading();

    void hideLoading();

    void openActivityOnTokenExpire();

    void onError(@StringRes int resId);

    void onError(String msg);

    void showMessage(String msg);

    void showMessage(@StringRes int resId);

    boolean isNetworkConnected();

    void hideKeyboard();
}
