package com.example.test.mvptest.data.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.test.mvptest.data.db.module.DaoMaster;
import com.example.test.mvptest.di.ApplicationContext;
import com.example.test.mvptest.di.DatabaseInfo;
import com.example.test.mvptest.utils.AppLogger;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by longzhijun on 2017/11/16.
 */
@Singleton
public class DbOpenHelper extends DaoMaster.OpenHelper {

    @Inject
    public DbOpenHelper(@ApplicationContext Context context, @DatabaseInfo String name) {
        super(context, name);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        super.onUpgrade(db, oldVersion, newVersion);
        AppLogger.d("DEBUG", "DB_OLD_VERSION : " + oldVersion + ", DB_NEW_VERSION : " + newVersion);
        switch (oldVersion) {
            case 1:
            case 2:
                //db.execSQL("ALTER TABLE " + UserDao.TABLENAME + " ADD COLUMN "
                // + UserDao.Properties.Name.columnName + " TEXT DEFAULT 'DEFAULT_VAL'");
        }
    }
}
