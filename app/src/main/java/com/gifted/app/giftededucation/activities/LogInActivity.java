package com.gifted.app.giftededucation.activities;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.gifted.app.giftededucation.R;
import com.gifted.app.giftededucation.customviews.CustomEditText;
import com.gifted.app.giftededucation.customviews.FontTextView;
import com.gifted.app.giftededucation.interfaces.VolleyCallback;
import com.gifted.app.giftededucation.requests.LoginUserRequest;
import com.gifted.app.giftededucation.utils.Config;
import com.gifted.app.giftededucation.utils.EncryptPassword;
import com.google.gson.Gson;
import com.thefinestartist.Base;
import com.thefinestartist.utils.preferences.Pref;

import org.json.JSONException;
import org.json.JSONObject;

import dmax.dialog.SpotsDialog;

public class LogInActivity extends AppCompatActivity {
    private static final String TAG = LogInActivity.class.getName();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        Toolbar toolbarTop = (Toolbar) findViewById(R.id.toolbar_top);
        TextView mTitle = (TextView) toolbarTop.findViewById(R.id.toolbar_title);

        FontTextView register = (FontTextView) findViewById(R.id.register);
        Button login_up = (Button) findViewById(R.id.login_up);
        final CustomEditText email = (CustomEditText) findViewById(R.id.input_email_login);
        final CustomEditText password = (CustomEditText) findViewById(R.id.input_password_login);


        login_up.setTypeface(Typeface.createFromAsset(Base.getAssets(), "fonts/MuseoSans_500.otf"));

        login_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isValidEmailAddress(email.getText().toString()) && password.getText().toString().length() > 5) {
                    final AlertDialog spotsDialog = new SpotsDialog(LogInActivity.this, "Please Wait...", R.style.Custom);
                    spotsDialog.show();
                    LoginUserRequest login = new LoginUserRequest();
                    login.login(email.getText().toString(), EncryptPassword.md5(password.getText().toString()), new VolleyCallback() {
                        @Override
                        public void onSuccessResponse(String result) {

                            spotsDialog.dismiss();
                            Log.e(TAG, result);

                            try {
                                JSONObject jsonObject = new JSONObject(result);
                                if (jsonObject.get("success").toString().contentEquals("true")) {
                                    String user_id = jsonObject.getJSONArray("userDetails").getJSONObject(0).getString("user_id");
                                    Pref.put(Config.KEY__USER_TOKEN, jsonObject.getString("token"));
                                    Pref.put(Config.KEY_USER_ID, user_id);


                                    Gson gson = new Gson();
                                    String save = gson.toJson(jsonObject.getJSONArray("userDetails").getJSONObject(0).getJSONArray("levels_allowed"));
                                    Log.e(TAG, save);
                                    Pref.put(Config.KEY_EXAMS_ALLOWED, save);
                                    save = gson.toJson(jsonObject.getJSONArray("userDetails").getJSONObject(0).getJSONArray("levels_taken"));
                                    Pref.put(Config.KEY_EXAMS_TAKEN, save);
                                    Log.e(TAG, save);
                                    Pref.put(Config.USER_OBJECT, gson.toJson(jsonObject.getJSONArray("userDetails").getJSONObject(0)));
                                    // Pref.put(Config.USER_OBJECT, gson.toJson(jsonObject.getJSONArray("userDetails").getJSONObject(0)));
                                    Pref.put(Config.USER_NAME, jsonObject.getJSONArray("userDetails").getJSONObject(0).getString("name"));
                                    Pref.put(Config.USER_CLASS, jsonObject.getJSONArray("userDetails").getJSONObject(0).getString("class"));

                                    startActivity(new Intent(Base.getContext(), LevelsActivity.class));

                                } else {
                                    Toast.makeText(LogInActivity.this, "Wrong Username Or Password", Toast.LENGTH_SHORT).show();
                                }

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }


                        }
                    });

                } else {
                    Toast.makeText(Base.getContext(), "Invalid Credentials", Toast.LENGTH_SHORT).show();
                }
            }
        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LogInActivity.this, RegisterActivity.class));
            }
        });
    }

    public boolean isValidEmailAddress(String email) {
        String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
        java.util.regex.Matcher m = p.matcher(email);
        return m.matches();
    }
}
