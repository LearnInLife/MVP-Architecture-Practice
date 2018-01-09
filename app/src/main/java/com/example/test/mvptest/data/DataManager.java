package com.example.test.mvptest.data;

import com.example.test.mvptest.data.db.DbHelper;
import com.example.test.mvptest.data.network.ApiHelper;
import com.example.test.mvptest.data.prefs.PreferencesHelper;

import io.reactivex.Observable;

/**
 * Created by longzhijun on 2017/11/16.
 */

public interface DataManager extends DbHelper, PreferencesHelper, ApiHelper {
    Observable<Boolean> seedDatabaseQuestions();

    Observable<Boolean> seedDatabaseOptions();

    void updateApiHeader(Long userId, String accessToken);

    void setUserAsLoggedOut();

    void updateUserInfo(
            String accessToken,
            Long userId,
            LoggedInMode loggedInMode,
            String userName,
            String email,
            String profilePicPath);

    enum LoggedInMode {

        LOGGED_IN_MODE_LOGGED_OUT(0),
        LOGGED_IN_MODE_GOOGLE(1),
        LOGGED_IN_MODE_FB(2),
        LOGGED_IN_MODE_SERVER(3);

        private final int mType;

        LoggedInMode(int type) {
            mType = type;
        }

        public int getType() {
            return mType;
        }
    }
}
