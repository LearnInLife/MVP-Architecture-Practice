package com.example.test.mvptest.ui.splash;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.example.test.mvptest.R;
import com.example.test.mvptest.ui.Login.LoginActivity;
import com.example.test.mvptest.ui.base.BaseActivity;
import com.example.test.mvptest.ui.main.MainActivity;

import javax.inject.Inject;

/**
 * Created by longzhijun on 2017/11/15.
 */

public class SplashActivity extends BaseActivity implements SplashMvpView{

    @Inject
    SplashMvpPresenter<SplashMvpView> mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        getActivityComponent().inject(this);

        mPresenter.onAttach(this);
    }

    @Override
    public void openLoginActivity() {
        Intent intent = LoginActivity.getStartIntent(SplashActivity.this);
        startActivity(intent);
        finish();
    }

    @Override
    public void openMainActivity() {
        Intent intent = MainActivity.getStartIntent(SplashActivity.this);
//        Intent intent = LoginActivity.getStartIntent(SplashActivity.this);
        startActivity(intent);
        finish();
    }

    @Override
    public void startSyncService() {

    }

    @Override
    protected void onDestroy() {
        mPresenter.onDetach();
        super.onDestroy();
    }
}
