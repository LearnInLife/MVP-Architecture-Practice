package com.example.test.mvptest.ui.main.rating;

import com.example.test.mvptest.ui.base.MvpPresenter;

/**
 * Created by longzhijun on 2018/1/5.
 */

public interface RatingDialogMvpPresenter<V extends RatingDialogMvpView> extends MvpPresenter<V>{
    void onRatingSubmitted(float rating, String s);

    void onLaterClicked();

    void onPlayStoreRatingClicked();
}
