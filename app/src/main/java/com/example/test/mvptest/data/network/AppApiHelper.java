package com.example.test.mvptest.data.network;

import com.androidnetworking.AndroidNetworking;
import com.example.test.mvptest.data.network.module.BlogResponse;
import com.example.test.mvptest.data.network.module.LoginRequest;
import com.example.test.mvptest.data.network.module.LoginResponse;
import com.example.test.mvptest.data.network.module.LogoutResponse;
import com.example.test.mvptest.data.network.module.OpenSourceResponse;
import com.rx2androidnetworking.Rx2AndroidNetworking;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;

/**
 * Created by longzhijun on 2017/11/16.
 */
@Singleton
public class AppApiHelper implements ApiHelper {

    private ApiHeader apiHeader;

    @Inject
    public AppApiHelper(ApiHeader apiHeader){
        this.apiHeader = apiHeader;
    }

    @Override
    public ApiHeader getApiHeader() {
        return apiHeader;
    }

    @Override
    public Observable<LoginResponse> doGoogleLoginApiCall(LoginRequest.GoogleLoginRequest request) {
        return Rx2AndroidNetworking.post(ApiEndPoint.ENDPOINT_GOOGLE_LOGIN)
                .addHeaders(apiHeader.getPublicApiHeader())
                .addBodyParameter(request)
                .build()
                .getObjectObservable(LoginResponse.class);
    }

    @Override
    public Observable<LoginResponse> doFacebookLoginApiCall(LoginRequest.FacebookLoginRequest request) {
        return Rx2AndroidNetworking.post(ApiEndPoint.ENDPOINT_FACEBOOK_LOGIN)
                .addHeaders(apiHeader.getPublicApiHeader())
                .addBodyParameter(request)
                .build()
                .getObjectObservable(LoginResponse.class);
    }

    @Override
    public Observable<LoginResponse> doServerLoginApiCall(LoginRequest.ServerLoginRequest request) {
        return Rx2AndroidNetworking.post(ApiEndPoint.ENDPOINT_SERVER_LOGIN)
                .addHeaders(apiHeader.getPublicApiHeader())
                .addBodyParameter(request)
                .build()
                .getObjectObservable(LoginResponse.class);
    }

    @Override
    public Observable<LogoutResponse> doLogoutApiCall() {
        return Rx2AndroidNetworking.post(ApiEndPoint.ENDPOINT_LOGOUT)
                .addHeaders(apiHeader.getProtectedApiHeader())
                .build()
                .getObjectObservable(LogoutResponse.class);
    }

    @Override
    public Observable<BlogResponse> getBlogApiCall() {
        return Rx2AndroidNetworking.get(ApiEndPoint.ENDPOINT_BLOG)
                .addHeaders(apiHeader.getProtectedApiHeader())
                .build()
                .getObjectObservable(BlogResponse.class);
    }

    @Override
    public Observable<OpenSourceResponse> getOpenSourceApiCall() {
        return Rx2AndroidNetworking.get(ApiEndPoint.ENDPOINT_OPEN_SOURCE)
                .addHeaders(apiHeader.getProtectedApiHeader())
                .build()
                .getObjectObservable(OpenSourceResponse.class);
    }
}
