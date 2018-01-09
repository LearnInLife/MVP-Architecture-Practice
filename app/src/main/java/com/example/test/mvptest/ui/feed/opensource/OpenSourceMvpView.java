package com.example.test.mvptest.ui.feed.opensource;

import com.example.test.mvptest.data.network.module.OpenSourceResponse;
import com.example.test.mvptest.ui.base.MvpView;

import java.util.List;

/**
 * Created by longzhijun on 2018/1/9.
 */

public interface OpenSourceMvpView extends MvpView{
    void updateRepo(List<OpenSourceResponse.Repo> repoList);
}
