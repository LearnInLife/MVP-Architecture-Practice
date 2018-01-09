package com.example.test.mvptest.ui.main.rating;

import com.example.test.mvptest.R;
import com.example.test.mvptest.data.DataManager;
import com.example.test.mvptest.ui.base.BasePresenter;
import com.example.test.mvptest.utils.rx.SchedulerProvider;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by longzhijun on 2018/1/5.
 */

public class RatingDialogPresenter<V extends RatingDialogMvpView> extends BasePresenter<V> implements RatingDialogMvpPresenter<V> {

    private boolean isRatingSecondaryActionShown = false;

    @Inject
    public RatingDialogPresenter(DataManager dataManager, SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }

    @Override
    public void onRatingSubmitted(float rating, String s) {
        if (rating == 0) {
            getMvpView().showMessage(R.string.rating_not_provided_error);
            return;
        }

        if (!isRatingSecondaryActionShown) {
            if (rating == 5) {
                getMvpView().showPlayStoreRatingView();
                getMvpView().hideSubmitButton();
                getMvpView().disableRatingStars();
            } else {
                getMvpView().showRatingMessageView();
            }
            isRatingSecondaryActionShown = true;
            return;
        }

        getMvpView().showLoading();

        //for demo
        getMvpView().hideLoading();
        getMvpView().showMessage(R.string.rating_thanks);
        getMvpView().dismissDialog();
    }

    @Override
    public void onLaterClicked() {
        getMvpView().dismissDialog();
    }

    @Override
    public void onPlayStoreRatingClicked() {
        getMvpView().openPlayStoreForRating();
        getMvpView().dismissDialog();
    }
}
