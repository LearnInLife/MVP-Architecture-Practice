package com.example.test.mvptest.di.module;

import android.app.Application;
import android.content.Context;

import com.example.test.mvptest.BuildConfig;
import com.example.test.mvptest.data.AppDataManager;
import com.example.test.mvptest.data.DataManager;
import com.example.test.mvptest.data.db.AppDbHelper;
import com.example.test.mvptest.data.db.DbHelper;
import com.example.test.mvptest.data.network.ApiHeader;
import com.example.test.mvptest.data.network.ApiHelper;
import com.example.test.mvptest.data.network.AppApiHelper;
import com.example.test.mvptest.data.prefs.AppPreferencesHelper;
import com.example.test.mvptest.data.prefs.PreferencesHelper;
import com.example.test.mvptest.di.ApiInfo;
import com.example.test.mvptest.di.ApplicationContext;
import com.example.test.mvptest.di.DatabaseInfo;
import com.example.test.mvptest.di.PreferenceInfo;
import com.example.test.mvptest.utils.AppConstants;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by longzhijun on 2017/11/15.
 */
@Module
public class ApplicationModule {
    private final Application mApplication;

    public ApplicationModule(Application application) {
        mApplication = application;
    }

    @Provides
    @ApplicationContext
    Context provideContext(){
        return mApplication;
    }

    @Provides
    Application provideApplication(){
        return mApplication;
    }

    @DatabaseInfo
    @Provides
    String provideDatabaseName(){
        return AppConstants.DB_NAME;
    }

    @Provides
    @PreferenceInfo
    String providePreferenceName() {
        return AppConstants.PREF_NAME;
    }

    @Provides
    @ApiInfo
    String provideApiKey() {
        return BuildConfig.API_KEY;
    }

    @Provides
    @Singleton
    DbHelper provideDbHelper(AppDbHelper appDbHelper){
        return appDbHelper;
    }

    @Provides
    @Singleton
    PreferencesHelper providePreferencesHelper(AppPreferencesHelper appPreferencesHelper) {
        return appPreferencesHelper;
    }

    @Provides
    @Singleton
    ApiHelper provideApiHelper(AppApiHelper appApiHelper) {
        return appApiHelper;
    }

    @Provides
    @Singleton
    DataManager provideDataManager(AppDataManager manager){
        return manager;
    }


    @Provides
    @Singleton
    ApiHeader.ProtectedApiHeader provideProtectedApiHeader(@ApiInfo String apiKey,
                                                        PreferencesHelper preferencesHelper) {
        return new ApiHeader.ProtectedApiHeader(apiKey, preferencesHelper.getCurrentUserId(),preferencesHelper.getAccessToken());
    }
}
