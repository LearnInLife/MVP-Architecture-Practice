package com.example.test.mvptest.ui.main.rating;

import com.example.test.mvptest.ui.base.DialogMvpView;
import com.example.test.mvptest.ui.base.MvpView;

/**
 * Created by longzhijun on 2018/1/5.
 */

public interface RatingDialogMvpView extends DialogMvpView {
    void openPlayStoreForRating();

    void showPlayStoreRatingView();

    void showRatingMessageView();

    void hideSubmitButton();

    void disableRatingStars();

    void dismissDialog();
}
