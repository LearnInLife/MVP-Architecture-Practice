package com.example.test.mvptest.ui.about;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.test.mvptest.R;
import com.example.test.mvptest.di.component.ActivityComponent;
import com.example.test.mvptest.ui.base.BaseFragment;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by longzhijun on 2018/1/5.
 */

public class AboutFragment extends BaseFragment implements AboutMvpView{
    public static final String TAG = "AboutFragment";

    @Inject
    AboutMvpPresenter<AboutMvpView> mPresenter;

    public static AboutFragment newInstance() {
        Bundle args = new Bundle();
        AboutFragment fragment = new AboutFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_about, container, false);

        ActivityComponent component = getActivityComponent();
        if (component != null) {
            component.inject(this);
            setUnBinder(ButterKnife.bind(this, view));
            mPresenter.onAttach(this);
        }

        return view;
    }

    @Override
    protected void setUp(View view) {

    }

    @OnClick(R.id.nav_back_btn)
    void onNavBackClick() {
        getBaseActivity().onFragmentDetached(TAG);
    }

    @Override
    public void onDestroyView() {
        mPresenter.onDetach();
        super.onDestroyView();
    }
}
