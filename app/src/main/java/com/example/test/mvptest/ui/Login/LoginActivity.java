package com.example.test.mvptest.ui.Login;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.test.mvptest.R;
import com.example.test.mvptest.ui.base.BaseActivity;
import com.example.test.mvptest.ui.main.MainActivity;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by longzhijun on 2017/12/21.
 */

public class LoginActivity extends BaseActivity implements LoginMvpView{

    @Inject
    LoginMvpPresenter<LoginMvpView> mPresenter;

    @BindView(R.id.et_email)
    EditText mEmailEditText;

    @BindView(R.id.et_password)
    EditText mPasswordEditText;

    public static Intent getStartIntent(Context context) {
        Intent intent = new Intent(context, LoginActivity.class);
        return intent;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getActivityComponent().inject(this);

        setUnBinder(ButterKnife.bind(this));

        mPresenter.onAttach(this);

    }

    @OnClick(R.id.btn_server_login)
    void onServerLoginClick(View v) {
        mPresenter.onServerLoginClick(mEmailEditText.getText().toString(),
                mPasswordEditText.getText().toString());
    }

    @OnClick(R.id.ib_google_login)
    void onGoogleLoginClick(View v) {
        mPresenter.onGoogleLoginClick();
    }

    @OnClick(R.id.ib_fb_login)
    void onFbLoginClick(View v) {
        mPresenter.onFacebookLoginClick();
    }


    @Override
    public void openMainActivity() {
        Intent intent = MainActivity.getStartIntent(this);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onDestroy() {
        mPresenter.onDetach();
        super.onDestroy();
    }
}
