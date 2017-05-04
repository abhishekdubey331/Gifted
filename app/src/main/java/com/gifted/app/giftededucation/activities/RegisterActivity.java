package com.gifted.app.giftededucation.activities;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.gifted.app.giftededucation.R;
import com.gifted.app.giftededucation.interfaces.VolleyCallback;
import com.gifted.app.giftededucation.requests.LoginUserRequest;
import com.gifted.app.giftededucation.customviews.CustomSpinnerAdapter;
import com.gifted.app.giftededucation.utils.EncryptPassword;
import com.thefinestartist.Base;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import dmax.dialog.SpotsDialog;

public class RegisterActivity extends AppCompatActivity {
    private Button sign_up;
    private EditText input_name, input_school, input_district, input_state, input_email, input_password, input_mobile;
    private int student_class;
    private String[] class_array = {"5", "6", "7", "8", "9", "10"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        generate_all_views();
        Spinner spinnerCustom = (Spinner) findViewById(R.id.spinnerCustom);
        sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                boolean fieldsOK = validate(new EditText[]{input_name, input_school, input_district, input_state, input_email, input_password, input_mobile});

                if (fieldsOK) {
                    setFields();
                } else {
                    Toast.makeText(RegisterActivity.this, "Fill Fields Correctly", Toast.LENGTH_SHORT).show();
                }

            }
        });
        // Spinner Drop down elements
        ArrayList<String> languages = new ArrayList<String>();
        languages.add("Select Your Class");
        languages.add("You are in Class 5");
        languages.add("You are in Class 6");
        languages.add("You are in Class 7");
        languages.add("You are in Class 8");
        languages.add("You are in Class 9");
        languages.add("You are in Class 10");
        CustomSpinnerAdapter customSpinnerAdapter = new CustomSpinnerAdapter(Base.getContext(), languages);
        spinnerCustom.setAdapter(customSpinnerAdapter);
        spinnerCustom.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                /*String item = parent.getItemAtPosition(position).toString();*/

                student_class = position;

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void generate_all_views() {

        Toolbar toolbarTop = (Toolbar) findViewById(R.id.toolbar_top);
        TextView mTitle = (TextView) toolbarTop.findViewById(R.id.toolbar_title);

        input_district = (EditText) findViewById(R.id.input_district);

        input_name = (EditText) findViewById(R.id.input_name);

        input_school = (EditText) findViewById(R.id.input_school);

        input_state = (EditText) findViewById(R.id.input_state);

        input_email = (EditText) findViewById(R.id.input_email);

        input_password = (EditText) findViewById(R.id.input_password);

        input_mobile = (EditText) findViewById(R.id.input_mobile);

        sign_up = (Button) findViewById(R.id.sign_up);
        sign_up.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/MuseoSans_500.otf"));


    }

    private void setFields() {

        LoginUserRequest loginUserRequest = new LoginUserRequest();
        final AlertDialog spotsDialog = new SpotsDialog(RegisterActivity.this,"Please Wait...", R.style.Custom);
        spotsDialog.show();
        loginUserRequest.register(input_name.getText().toString(), class_array[student_class], input_school.getText().toString(), input_district.getText().toString(), input_state.getText().toString(), input_email.getText().toString(), input_mobile.getText().toString(), EncryptPassword.md5(input_password.getText().toString()), new VolleyCallback() {
            @Override
            public void onSuccessResponse(String result) {
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    if (jsonObject.getBoolean("success")) {
                        spotsDialog.dismiss();
                        startActivity(new Intent(Base.getContext(), TestActivity.class));
                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

    }

    private boolean validate(EditText[] fields) {

        for (EditText currentField : fields) {

            if (currentField.getText().toString().length() <= 0) {
                return false;
            }
        }
        return true;

    }


}
