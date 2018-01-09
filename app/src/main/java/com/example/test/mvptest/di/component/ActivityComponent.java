package com.example.test.mvptest.di.component;

import com.example.test.mvptest.ui.about.AboutFragment;
import com.example.test.mvptest.ui.feed.FeedActivity;
import com.example.test.mvptest.ui.feed.blog.BlogFragment;
import com.example.test.mvptest.ui.feed.opensource.OpenSourceFragment;
import com.example.test.mvptest.ui.main.MainActivity;
import com.example.test.mvptest.di.PerActivity;
import com.example.test.mvptest.di.module.ActivityModule;
import com.example.test.mvptest.ui.Login.LoginActivity;
import com.example.test.mvptest.ui.main.rating.RateUsDialog;
import com.example.test.mvptest.ui.splash.SplashActivity;

import dagger.Component;

/**
 * Created by longzhijun on 2017/11/15.
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {
    void inject(MainActivity activity);

    void inject(LoginActivity activity);

    void inject(SplashActivity activity);

    void inject(RateUsDialog dialog);

    void inject(AboutFragment fragment);

    void inject(FeedActivity activity);

    void inject(BlogFragment fragment);

    void inject(OpenSourceFragment fragment);
}
