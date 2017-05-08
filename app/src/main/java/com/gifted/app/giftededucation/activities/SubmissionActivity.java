package com.gifted.app.giftededucation.activities;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;

import com.gifted.app.giftededucation.R;
import com.gifted.app.giftededucation.utils.Config;
import com.thefinestartist.Base;
import com.thefinestartist.utils.preferences.Pref;

import org.json.JSONException;
import org.json.JSONObject;

public class SubmissionActivity extends AppCompatActivity {

    private static final String TAG = SubmissionActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submission);
        set_card();


    }


    private void set_card() {
        Typeface typeface;

        Button submit = (Button) findViewById(R.id.submit_);
        TextView name_ = (TextView) findViewById(R.id.name_);

        TextView classs_ = (TextView) findViewById(R.id.classs_);
        TextView school_ = (TextView) findViewById(R.id.school_);
        TextView email_ = (TextView) findViewById(R.id.email_);
        typeface = Typeface.createFromAsset(Base.getAssets(), "fonts/MuseoSans_500.otf");
        submit.setTypeface(typeface);


        try {
            JSONObject jsonObject = new JSONObject(Pref.get(Config.USER_OBJECT, "Soo"));
            JSONObject jsonObject1 = jsonObject.getJSONObject("nameValuePairs");
            name_.setText(jsonObject1.getString("name"));
            classs_.setText(jsonObject1.getString("class"));
            school_.setText(jsonObject1.getString("school"));
            email_.setText(jsonObject1.getString("email"));

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


}
