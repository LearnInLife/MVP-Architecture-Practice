package com.example.test.mvptest.data.network;

import com.example.test.mvptest.data.network.module.BlogResponse;
import com.example.test.mvptest.data.network.module.LoginRequest;
import com.example.test.mvptest.data.network.module.LoginResponse;
import com.example.test.mvptest.data.network.module.LogoutResponse;
import com.example.test.mvptest.data.network.module.OpenSourceResponse;

import io.reactivex.Observable;

/**
 * Created by longzhijun on 2017/11/16.
 */

public interface ApiHelper {

    ApiHeader getApiHeader();

    Observable<LoginResponse> doGoogleLoginApiCall(LoginRequest.GoogleLoginRequest request);

    Observable<LoginResponse> doFacebookLoginApiCall(LoginRequest.FacebookLoginRequest request);

    Observable<LoginResponse> doServerLoginApiCall(LoginRequest.ServerLoginRequest request);

    Observable<LogoutResponse> doLogoutApiCall();

    Observable<BlogResponse> getBlogApiCall();

    Observable<OpenSourceResponse> getOpenSourceApiCall();
}
