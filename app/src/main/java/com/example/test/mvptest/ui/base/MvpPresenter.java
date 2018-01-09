package com.example.test.mvptest.ui.base;

import com.androidnetworking.error.ANError;

/**
 * Created by longzhijun on 2017/11/16.
 */

public interface MvpPresenter<V extends MvpView> {
    void onAttach(V mvpview);

    void onDetach();

    void handleApiError(ANError error);

    void setUserAsLoggedOut();
}
