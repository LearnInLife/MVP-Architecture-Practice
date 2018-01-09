package com.example.test.mvptest.ui.base;

import android.annotation.TargetApi;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethod;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;
import android.widget.Toast;

import com.example.test.mvptest.MvpApplication;
import com.example.test.mvptest.R;
import com.example.test.mvptest.di.component.ActivityComponent;
import com.example.test.mvptest.di.component.DaggerActivityComponent;
import com.example.test.mvptest.di.module.ActivityModule;
import com.example.test.mvptest.ui.Login.LoginActivity;
import com.example.test.mvptest.utils.CommonUtils;
import com.example.test.mvptest.utils.NetworkUtils;

import butterknife.Unbinder;

/**
 * Created by longzhijun on 2017/11/15.
 */

public class BaseActivity extends AppCompatActivity implements MvpView , BaseFragment.Callback{
    private ActivityComponent mActivityComponent;

    private ProgressDialog mProgressDialog;

    private Unbinder mUnBinder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivityComponent = DaggerActivityComponent.builder()
                .applicationComponent(((MvpApplication) getApplication()).getComponet())
                .activityModule(new ActivityModule(this))
                .build();
    }

    public ActivityComponent getActivityComponent() {
        return mActivityComponent;
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(newBase);
    }

    @TargetApi(Build.VERSION_CODES.M)
    public void requestPermissionsSafely(String[] permissions, int requestCode) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(permissions, requestCode);
        }
    }

    @TargetApi(Build.VERSION_CODES.M)
    public boolean hasPermission(String permission) {
        return Build.VERSION.SDK_INT < Build.VERSION_CODES.M ||
                checkSelfPermission(permission) == PackageManager.PERMISSION_GRANTED;
    }

    @Override
    public void showLoading() {
        hideLoading();
        mProgressDialog = CommonUtils.showLoadingDialog(this);
    }

    @Override
    public void hideLoading() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.cancel();
        }
    }

    @Override
    public void openActivityOnTokenExpire() {
        startActivity(LoginActivity.getStartIntent(this));
        finish();
    }

    @Override
    public void onError(@StringRes int resId) {
        onError(getString(resId));
    }

    @Override
    public void onError(String msg) {
        if (msg != null) {
            showSnackBar(msg);
        } else {
            showSnackBar(getString(R.string.some_error));
        }
    }

    @Override
    public void showMessage(String msg) {
        if (msg != null) {
            Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, getString(R.string.some_error), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void showMessage(@StringRes int resId) {
        showMessage(getString(resId));
    }

    @Override
    public boolean isNetworkConnected() {
        return NetworkUtils.isNetworkConnected(getApplicationContext());
    }

    @Override
    public void hideKeyboard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager im = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            im.hideSoftInputFromInputMethod(view.getWindowToken(),0);
        }
    }

    private void showSnackBar(String msg) {
        Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content), msg, Snackbar.LENGTH_SHORT);
        View sbView = snackbar.getView();
        TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
        textView.setTextColor(ContextCompat.getColor(this, R.color.white));
        snackbar.show();
    }

    public void setUnBinder(Unbinder unBinder) {
        mUnBinder = unBinder;
    }

    @Override
    protected void onDestroy() {

        if (mUnBinder != null) {
            mUnBinder.unbind();
        }
        super.onDestroy();
    }

    @Override
    public void onFragmentAttached() {

    }

    @Override
    public void onFragmentDetached(String tag) {

    }
}
