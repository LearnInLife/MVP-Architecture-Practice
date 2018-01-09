package com.example.test.mvptest.ui.test;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.example.test.mvptest.R;
import com.example.test.mvptest.ui.base.BaseActivity;
import com.example.test.mvptest.ui.feed.FeedActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by longzhijun on 2018/1/8.
 */

public class TestNavUtilsActivity extends BaseActivity {

    public static Intent getStartIntent(Context context) {
        Intent intent = new Intent(context, TestNavUtilsActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test);

        setUnBinder(ButterKnife.bind(this));
    }

    @OnClick(R.id.btn1)
    void btn1(View v) {
        startActivity(TestNavUtilsActivity.getStartIntent(this));
    }

    @OnClick(R.id.btn2)
    void btn2(View v) {
        startActivity(FeedActivity.getStartIntent(this));
    }
}
