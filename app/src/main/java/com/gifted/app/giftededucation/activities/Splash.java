package com.gifted.app.giftededucation.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.gifted.app.giftededucation.R;
import com.thefinestartist.utils.preferences.Pref;

public class Splash extends AppCompatActivity {
    private static final String TAG = Splash.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        Pref.clear();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(Splash.this, LogInActivity.class));
                finish();

            }
        }, 2500);

    }
}
