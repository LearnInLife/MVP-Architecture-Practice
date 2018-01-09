package com.example.test.mvptest.ui.main;

import com.example.test.mvptest.data.db.module.Question;
import com.example.test.mvptest.ui.base.MvpView;

import java.util.List;

/**
 * Created by longzhijun on 2018/1/4.
 */

public interface MainMvpView extends MvpView {

    void updateAppVersion();

    void updateUserName(String currentUserName);

    void updateUserEmail(String currentUserEmail);

    void updateUserProfilePic(String currentUserProfilePicUrl);

    void refreshQuestionnaire(List<Question> questionList);

    void reloadQuestionnaire(List<Question> questionList);

    void closeNavigationDrawer();

    void showAboutFragment();

    void lockDrawer();

    void unlockDrawer();

    void openLoginActivity();

    void showRateUsDialog();

    void openMyFeedActivity();
}
