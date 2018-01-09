package com.example.test.mvptest.di.module;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;

import com.example.test.mvptest.data.network.module.BlogResponse;
import com.example.test.mvptest.data.network.module.OpenSourceResponse;
import com.example.test.mvptest.di.ActivityContext;
import com.example.test.mvptest.di.PerActivity;
import com.example.test.mvptest.ui.Login.LoginMvpPresenter;
import com.example.test.mvptest.ui.Login.LoginMvpView;
import com.example.test.mvptest.ui.Login.LoginPresenter;
import com.example.test.mvptest.ui.about.AboutMvpPresenter;
import com.example.test.mvptest.ui.about.AboutMvpView;
import com.example.test.mvptest.ui.about.AboutPresenter;
import com.example.test.mvptest.ui.feed.FeedMvpPresenter;
import com.example.test.mvptest.ui.feed.FeedMvpView;
import com.example.test.mvptest.ui.feed.FeedPagerAdapter;
import com.example.test.mvptest.ui.feed.FeedPresenter;
import com.example.test.mvptest.ui.feed.blog.BlogAdapter;
import com.example.test.mvptest.ui.feed.blog.BlogMvpPresenter;
import com.example.test.mvptest.ui.feed.blog.BlogMvpView;
import com.example.test.mvptest.ui.feed.blog.BlogPresenter;
import com.example.test.mvptest.ui.feed.opensource.OpenSourceAdapter;
import com.example.test.mvptest.ui.feed.opensource.OpenSourceMvpPresenter;
import com.example.test.mvptest.ui.feed.opensource.OpenSourceMvpView;
import com.example.test.mvptest.ui.feed.opensource.OpenSourcePresenter;
import com.example.test.mvptest.ui.main.MainMvpPresenter;
import com.example.test.mvptest.ui.main.MainMvpView;
import com.example.test.mvptest.ui.main.MainPresenter;
import com.example.test.mvptest.ui.main.rating.RatingDialogMvpPresenter;
import com.example.test.mvptest.ui.main.rating.RatingDialogMvpView;
import com.example.test.mvptest.ui.main.rating.RatingDialogPresenter;
import com.example.test.mvptest.ui.splash.SplashMvpPresenter;
import com.example.test.mvptest.ui.splash.SplashMvpView;
import com.example.test.mvptest.ui.splash.SplashPresenter;
import com.example.test.mvptest.utils.rx.AppSchedulerProvider;
import com.example.test.mvptest.utils.rx.SchedulerProvider;

import java.util.ArrayList;

import dagger.Module;
import dagger.Provides;
import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by longzhijun on 2017/11/15.
 */
@Module
public class ActivityModule {
    private AppCompatActivity mActivity;

    public ActivityModule(AppCompatActivity activity) {
        this.mActivity = activity;
    }

    @Provides
    @ActivityContext
    Context provideContext() {
        return mActivity;
    }

    @Provides
    AppCompatActivity provideActivity() {
        return mActivity;
    }

    @Provides
    CompositeDisposable provideCompositeDisposable() {
        return new CompositeDisposable();
    }

    @Provides
    SchedulerProvider provideAppSchedulerProvider(){
        return new AppSchedulerProvider();
    }

    @Provides
    @PerActivity
    SplashMvpPresenter<SplashMvpView> provideSplashPresenter(
            SplashPresenter<SplashMvpView> presenter) {
        return presenter;
    }

    @Provides
    @PerActivity
    LoginMvpPresenter<LoginMvpView> provideLoginPresenter(
            LoginPresenter<LoginMvpView> presenter) {
        return presenter;
    }

    @Provides
    @PerActivity
    MainMvpPresenter<MainMvpView> provideMainPresenter(
            MainPresenter<MainMvpView> presenter) {
        return presenter;
    }

    @Provides
    RatingDialogMvpPresenter<RatingDialogMvpView> provideRateUsPresenter(
            RatingDialogPresenter<RatingDialogMvpView> presenter) {
        return presenter;
    }

    @Provides
    AboutMvpPresenter<AboutMvpView> provideAboutPresenter(
            AboutPresenter<AboutMvpView> presenter) {
        return presenter;
    }

    @Provides
    FeedMvpPresenter<FeedMvpView> provideFeedPresenter(
            FeedPresenter<FeedMvpView> presenter) {
        return presenter;
    }

    @Provides
    FeedPagerAdapter provideFeedPagerAdapter(AppCompatActivity activity) {
        return new FeedPagerAdapter(activity.getSupportFragmentManager());
    }

    @Provides
    BlogMvpPresenter<BlogMvpView> provideBlogMvpPresenter(
            BlogPresenter<BlogMvpView> presenter) {
        return presenter;
    }

    @Provides
    LinearLayoutManager provideLinearLayoutManager(AppCompatActivity activity) {
        return new LinearLayoutManager(activity);
    }

    @Provides
    BlogAdapter provideBlogAdapter() {
        return new BlogAdapter(new ArrayList<BlogResponse.Blog>());
    }

    @Provides
    OpenSourceMvpPresenter<OpenSourceMvpView> provideOpenSourcePresenter(
            OpenSourcePresenter<OpenSourceMvpView> presenter) {
        return presenter;
    }

    @Provides
    OpenSourceAdapter provideOpenSourceAdapter() {
        return new OpenSourceAdapter(new ArrayList<OpenSourceResponse.Repo>());
    }
}
