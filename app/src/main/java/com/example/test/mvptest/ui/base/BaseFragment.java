package com.example.test.mvptest.ui.base;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;
import android.view.View;

import com.example.test.mvptest.di.component.ActivityComponent;
import com.example.test.mvptest.utils.CommonUtils;

import butterknife.Unbinder;

/**
 * Created by longzhijun on 2018/1/5.
 */

public abstract class BaseFragment extends Fragment implements MvpView {
    private BaseActivity mActivity;
    private Unbinder mUnBinder;
    private ProgressDialog mProgressDialog;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setUp(view);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof BaseActivity) {
            BaseActivity activity = (BaseActivity) context;
            this.mActivity = activity;
            activity.onFragmentAttached();
        }
    }

    @Override
    public void onDetach() {
        mActivity = null;
        super.onDetach();
    }

    protected abstract void setUp(View view);

    @Override
    public void showLoading() {
        hideLoading();
        mProgressDialog = CommonUtils.showLoadingDialog(getContext());
    }

    @Override
    public void hideLoading() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.cancel();
        }
    }

    @Override
    public void openActivityOnTokenExpire() {
        if (mActivity != null) {
            mActivity.openActivityOnTokenExpire();
        }
    }

    @Override
    public void onError(@StringRes int resId) {
        if (mActivity != null) {
            mActivity.showMessage(resId);
        }
    }

    @Override
    public void onError(String msg) {
        if (mActivity != null) {
            mActivity.onError(msg);
        }
    }

    @Override
    public void showMessage(String msg) {
        if (mActivity != null) {
            mActivity.showMessage(msg);
        }
    }

    @Override
    public void showMessage(@StringRes int resId) {
        if (mActivity != null) {
            mActivity.showMessage(resId);
        }
    }

    @Override
    public boolean isNetworkConnected() {
        if (mActivity != null) {
            return mActivity.isNetworkConnected();
        }
        return false;
    }

    @Override
    public void hideKeyboard() {
        if (mActivity != null) {
            mActivity.hideKeyboard();
        }
    }

    public ActivityComponent getActivityComponent() {
        if (mActivity != null) {
            return mActivity.getActivityComponent();
        }
        return null;
    }

    public BaseActivity getBaseActivity() {
        return mActivity;
    }

    public void setUnBinder(Unbinder unBinder) {
        mUnBinder = unBinder;
    }

    @Override
    public void onDestroy() {
        if (mUnBinder != null) {
            mUnBinder.unbind();
        }
        super.onDestroy();
    }

    public interface Callback {

        void onFragmentAttached();

        void onFragmentDetached(String tag);
    }
}
