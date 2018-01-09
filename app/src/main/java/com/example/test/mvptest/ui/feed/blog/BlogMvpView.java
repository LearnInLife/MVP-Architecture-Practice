package com.example.test.mvptest.ui.feed.blog;

import com.example.test.mvptest.data.network.module.BlogResponse;
import com.example.test.mvptest.ui.base.MvpView;

import java.util.List;

/**
 * Created by longzhijun on 2018/1/9.
 */

public interface BlogMvpView extends MvpView{

    void updateBlog(List<BlogResponse.Blog> blogList);
}
