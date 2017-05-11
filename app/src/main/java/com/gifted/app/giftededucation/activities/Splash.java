package com.gifted.app.giftededucation.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.TextView;

import com.gifted.app.giftededucation.R;
import com.gifted.app.giftededucation.interfaces.VolleyCallback;
import com.gifted.app.giftededucation.requests.LoginUserRequest;
import com.thefinestartist.utils.preferences.Pref;

public class Splash extends AppCompatActivity {
    private static final String TAG = Splash.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        Toolbar toolbarTop = (Toolbar) findViewById(R.id.toolbar_top);
        TextView mTitle = (TextView) toolbarTop.findViewById(R.id.toolbar_title);
        Pref.clear();
        /*LoginUserRequest loginUserRequest = new LoginUserRequest();
        loginUserRequest.sendResponse("h", new VolleyCallback() {
            @Override
            public void onSuccessResponse(String result) {
                Log.e("Splash", result);
            }
        });*/


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(Splash.this, LogInActivity.class));

            }
        }, 2500);

    }
}
