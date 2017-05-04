package com.gifted.app.giftededucation;

import android.app.Application;
import android.content.ContextWrapper;

import com.orm.SugarApp;
import com.orm.SugarContext;
import com.pixplicity.easyprefs.library.Prefs;
import com.thefinestartist.Base;

/**
 * Created by DIC on 4/6/2017.
 */

public class Global extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Base.initialize(this);
        SugarContext.init(this);

        new Prefs.Builder()
                .setContext(this)
                .setMode(ContextWrapper.MODE_PRIVATE)
                .setPrefsName(getPackageName())
                .setUseDefaultSharedPreference(true)
                .build();
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        SugarContext.terminate();
    }


}