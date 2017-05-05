package com.gifted.app.giftededucation;

import android.app.Application;
import android.content.ContextWrapper;
import android.database.sqlite.SQLiteDatabase;

import com.gifted.app.giftededucation.utils.Config;
import com.greendao.db.DaoMaster;
import com.greendao.db.DaoSession;
import com.pixplicity.easyprefs.library.Prefs;
import com.thefinestartist.Base;

/**
 * Created by DIC on 4/6/2017.
 */

public class Global extends Application {

    private final Object mDaoLock = new Object();

    private DaoSession daoSession;

    @Override
    public void onCreate() {
        super.onCreate();
        Base.initialize(this);

        new Prefs.Builder()
                .setContext(this)
                .setMode(ContextWrapper.MODE_PRIVATE)
                .setPrefsName(getPackageName())
                .setUseDefaultSharedPreference(true)
                .build();
    }


    public DaoSession getSession() {
        synchronized (mDaoLock) {
            if (daoSession == null) {
                DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(Global.this,
                        Config.DB_NAME, null);
                SQLiteDatabase mSQLiteDatabase = helper.getWritableDatabase();
                DaoMaster daoMaster = new DaoMaster(mSQLiteDatabase);
                daoSession = daoMaster.newSession();
            }
            return daoSession;
        }
    }

}