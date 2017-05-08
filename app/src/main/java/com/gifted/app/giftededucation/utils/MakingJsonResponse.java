package com.gifted.app.giftededucation.utils;

import android.util.Log;

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


    public void makingJson(String jsonObject) {
        JSONObject user_id = new JSONObject();
        try {
            user_id.put("user_id", Pref.get("user_id", ""));
            user_id.put("exam_code", Pref.get(Config.LEVEL, ""));
            user_id.put("responses", jsonObject);
            JSONArray jsonArray = new JSONArray();
            jsonArray.put(user_id);
            Log.e("Array", jsonArray.toString());

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


}
