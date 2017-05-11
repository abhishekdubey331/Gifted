package com.gifted.app.giftededucation.utils;

import android.util.Log;

import com.gifted.app.giftededucation.Global;
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

    public MakingJsonResponse() {
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
        int count = 0;
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
                }
            });

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private DaoSession getAppDaoSession() {
        return ((Global) Base.getContext()).getSession();
    }


}
