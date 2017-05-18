package com.gifted.app.giftededucation.utils;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.gifted.app.giftededucation.Global;
import com.gifted.app.giftededucation.activities.SubmissionActivity;
import com.gifted.app.giftededucation.activities.TestActivity;
import com.gifted.app.giftededucation.interfaces.VolleyCallback;
import com.gifted.app.giftededucation.requests.LoginUserRequest;
import com.greendao.db.DaoSession;
import com.greendao.db.UserResponses;
import com.greendao.db.UserResponsesDao;
import com.thefinestartist.Base;
import com.thefinestartist.utils.preferences.Pref;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by DIC on 5/8/2017.
 */

public class MakingJsonResponse {
    private Context mContext;
    private int count = 0;

    public MakingJsonResponse(Context mContext) {
        this.mContext = mContext;
    }


    private int getTotalScore(int number) {
        UserResponses userResponses = getAppDaoSession().getUserResponsesDao()
                .queryBuilder().where(UserResponsesDao.Properties.Id.eq(number)).unique();

        UserResponses userResponses2 = getAppDaoSession().getUserResponsesDao()
                .queryBuilder().where(UserResponsesDao.Properties.Id.eq(number)).unique();

        if (userResponses.getUser_response().contentEquals(userResponses2.getAnswer())) {
            Log.e("Check", "Correct");

            return 4;
        } else {
            Log.e("Check", "InCorrect");

            return 0;

        }

    }


    public void makingJson(String jsonObject) {

        JSONObject user_id = new JSONObject();
        try {
            for (int i = 0; i < Pref.get("last", 30); i++) {
                count = count + getTotalScore(i);
            }
            Pref.put("UserScore", count);
           /* user_id.put("user_id", Pref.get("user_id", ""));
            user_id.put("exam_code", Pref.get(Config.LEVEL, ""));
*/
            user_id.put("responses", jsonObject);
            JSONArray jsonArray = new JSONArray();
            jsonArray.put(user_id);
            Log.e("Array", jsonArray.toString());

            LoginUserRequest loginUserRequest = new LoginUserRequest();
            loginUserRequest.sendResponse(jsonArray.toString(), new VolleyCallback() {
                @Override
                public void onSuccessResponse(String result) {
                    Log.e("Response", result);
                    calculateUserResult(count);


                }
            });

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void calculateUserResult(int marks_obtained) {
        int total_marks = 4 * Pref.get("last", 30);
        String result_status = "Not Qualified";
        int percentage = 90;
        Log.e("Precentage", percentage + "total" + total_marks);
        if (percentage > 80) {
            result_status = "Qualified";
        }
        LoginUserRequest loginUserRequest = new LoginUserRequest();
        loginUserRequest.send_User_Result(marks_obtained, percentage, result_status, new VolleyCallback() {
            @Override
            public void onSuccessResponse(String result) {
                Log.e("Response", result);

                Base.getContext().startActivity(new Intent(Base.getContext(), SubmissionActivity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
                ((TestActivity) mContext).finish();
            }
        });

    }

    private DaoSession getAppDaoSession() {
        return ((Global) Base.getContext()).getSession();
    }


}
