package com.example.test.mvptest.di.component;

import android.app.Application;
import android.content.Context;

import com.example.test.mvptest.MvpApplication;
import com.example.test.mvptest.data.DataManager;
import com.example.test.mvptest.di.ApplicationContext;
import com.example.test.mvptest.di.module.ApplicationModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by longzhijun on 2017/11/15.
 */
@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {
    void inject(MvpApplication app);

    @ApplicationContext
    Context context();

    Application application();

    DataManager getDataManager();
}
