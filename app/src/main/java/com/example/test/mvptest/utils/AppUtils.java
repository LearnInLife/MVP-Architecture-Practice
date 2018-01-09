package com.example.test.mvptest.utils;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import com.example.test.mvptest.R;

/**
 * Created by longzhijun on 2018/1/5.
 */

public class AppUtils {
    public static void openPlayStoreForApp(Context context){
        final String appPackageName = context.getPackageName();
        try {
            context.startActivity(new Intent(Intent.ACTION_VIEW,
                    Uri.parse(context
                            .getResources()
                            .getString(R.string.app_market_link) + appPackageName)));
        } catch (android.content.ActivityNotFoundException e) {
            context.startActivity(new Intent(Intent.ACTION_VIEW,
                    Uri.parse(context
                            .getResources()
                            .getString(R.string.app_google_play_store_link) + appPackageName)));
        }
    }
}
