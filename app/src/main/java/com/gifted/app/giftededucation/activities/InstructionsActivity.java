package com.gifted.app.giftededucation.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.gifted.app.giftededucation.R;
import com.gifted.app.giftededucation.interfaces.VolleyCallback;
import com.gifted.app.giftededucation.requests.LoginUserRequest;
import com.gifted.app.giftededucation.utils.Config;
import com.thefinestartist.Base;
import com.thefinestartist.utils.preferences.Pref;

public class InstructionsActivity extends AppCompatActivity {

    private static final String TAG = Splash.class.getName();
    private TextView instructions,terms_conditions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instructions);
        Toolbar toolbarTop = (Toolbar) findViewById(R.id.toolbar_top);
        TextView mTitle = (TextView) toolbarTop.findViewById(R.id.toolbar_title);
        mTitle.setText("Instructions");
        instructions = (TextView) findViewById(R.id.fontTextView);

        Button start_test = (Button) findViewById(R.id.start_test);

        LoginUserRequest loginUserRequest = new LoginUserRequest();

        loginUserRequest.get_questions(Pref.get(Config.LEVEL,"SJI"), new VolleyCallback() {
            @Override
            public void onSuccessResponse(String result) {

            }
        });


        start_test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Base.getContext(), TestActivity.class));
                finish();
            }
        });


        instructions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Base.getContext(), TestActivity.class));
                finish();

            }
        });


    }
}
