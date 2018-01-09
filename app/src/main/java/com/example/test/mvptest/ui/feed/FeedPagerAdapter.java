package com.example.test.mvptest.ui.feed;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.test.mvptest.ui.feed.blog.BlogFragment;
import com.example.test.mvptest.ui.feed.opensource.OpenSourceFragment;

/**
 * Created by longzhijun on 2018/1/8.
 */

public class FeedPagerAdapter extends FragmentStatePagerAdapter{

    private int mTabCount;
    public FeedPagerAdapter(FragmentManager fm) {
        super(fm);
        this.mTabCount = 0;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return BlogFragment.newInstance();
            case 1:
                return OpenSourceFragment.newInstance();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mTabCount;
    }

    public void setCount(int count) {
        mTabCount = count;
    }
}
