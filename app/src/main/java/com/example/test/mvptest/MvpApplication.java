package com.example.test.mvptest;

import android.app.Application;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.interceptors.HttpLoggingInterceptor;
import com.example.test.mvptest.di.component.ApplicationComponent;
import com.example.test.mvptest.di.component.DaggerApplicationComponent;
import com.example.test.mvptest.di.module.ApplicationModule;
import com.example.test.mvptest.utils.AppLogger;

/**
 * Created by longzhijun on 2017/11/15.
 */

public class MvpApplication extends Application{
    private ApplicationComponent mApplicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        mApplicationComponent = DaggerApplicationComponent.builder()
                                .applicationModule(new ApplicationModule(this))
                                .build();
        mApplicationComponent.inject(this);
        AppLogger.init();

        AndroidNetworking.initialize(getApplicationContext());
        if (BuildConfig.DEBUG) {
            AndroidNetworking.enableLogging(HttpLoggingInterceptor.Level.BODY);
        }
    }

    public ApplicationComponent getComponet(){
        return mApplicationComponent;
    }

    // Needed to replace the component with a test specific one
    public void setComponent(ApplicationComponent applicationComponent) {
        mApplicationComponent = applicationComponent;
    }
}
