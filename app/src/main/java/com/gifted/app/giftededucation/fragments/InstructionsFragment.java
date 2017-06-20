package com.gifted.app.giftededucation.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.gifted.app.giftededucation.R;
import com.gifted.app.giftededucation.activities.TestActivity;
import com.gifted.app.giftededucation.interfaces.VolleyCallback;
import com.gifted.app.giftededucation.requests.LoginUserRequest;
import com.gifted.app.giftededucation.utils.Config;
import com.thefinestartist.Base;
import com.thefinestartist.utils.preferences.Pref;

public class InstructionsFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.activity_instructions, container, false);
        Button start_test = (Button) view.findViewById(R.id.start_test);

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
            }
        });


        return view;
    }

}
