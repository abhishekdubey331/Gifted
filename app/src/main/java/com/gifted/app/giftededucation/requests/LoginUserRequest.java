package com.gifted.app.giftededucation.requests;

import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.gifted.app.giftededucation.activities.RegisterActivity;
import com.gifted.app.giftededucation.interfaces.VolleyCallback;
import com.gifted.app.giftededucation.pojo.Question;
import com.gifted.app.giftededucation.pojo.UserResponses;
import com.gifted.app.giftededucation.utils.Config;
import com.orm.SugarRecord;
import com.thefinestartist.Base;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class LoginUserRequest {
    private static final String TAG = RegisterActivity.class.getName();

    public LoginUserRequest() {
    }

    public void login(String Email, String Password, final VolleyCallback callback) {
        //Getting values from edit texts
        final String email = Email;
        final String password = Password;

        //Creating a string request
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Config.KEY_LOGIN_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        callback.onSuccessResponse(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //You can handle error here if you want
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                //Adding parameters to request
                params.put(Config.KEY_EMAIL, email);
                params.put(Config.KEY_PASSWORD, password);

                //returning parameter
                return params;
            }
        };

        //Adding the string request to the queue
        RequestQueue requestQueue = Volley.newRequestQueue(Base.getContext());
        requestQueue.add(stringRequest);
    }

    public void register(final String Name, final String student_class, final String school, final String district, final String state, String Email, final String mobile, String Password, final VolleyCallback callback) {
        //Getting values from edit texts
        final String email = Email;
        final String password = Password;

        //Creating a string request
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Config.KEY_REGISTRAION_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.e(TAG, response);
                        callback.onSuccessResponse(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //You can handle error here if you want
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                //Adding parameters to request
                params.put(Config.KEY_EMAIL, email);
                params.put(Config.KEY_PASSWORD, password);
                params.put(Config.KEY_NAME, Name);
                params.put(Config.KEY_SCHOOL, school);
                params.put(Config.KEY_DISTRICT, district);
                params.put(Config.KEY_STATE, state);
                params.put(Config.KEY_CLASS, student_class);
                params.put(Config.KEY_MOBILE, mobile);


                //returning parameter
                return params;
            }
        };

        //Adding the string request to the queue
        RequestQueue requestQueue = Volley.newRequestQueue(Base.getContext());
        requestQueue.add(stringRequest);
    }

    public void get_questions(String exam_code, final VolleyCallback callback) {
        //Getting values from edit texts
        final String exam_code_ = exam_code;

        //Creating a string request
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Config.KEY_GET_QUE_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.e(TAG, response);
                        List<Question> questionList = new ArrayList<>();
                        List<UserResponses> userResponses = new ArrayList<>();

                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray jsonArray = jsonObject.getJSONArray("Result");
                            Log.e("Values", "Hello");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                                questionList.add(new Question("Q." + i,
                                        jsonObject1.getString("Que_Code"),
                                        jsonObject1.getString("Exam_Code"),
                                        jsonObject1.getString("Max_Marks"),
                                        jsonObject1.getString("Question"),
                                        jsonObject1.getString("Image"),
                                        jsonObject1.getJSONObject("Options") + "",
                                        jsonObject1.getString("Answer")));
                                userResponses.add(new UserResponses(jsonObject1.getString("Que_Code"), jsonObject1.getString("Question"), jsonObject1.getString("Exam_Code"),
                                        jsonObject1.getString("Max_Marks"), jsonObject1.getString("Answer"), ""));
                            }
                            Question.deleteAll(Question.class);
                            UserResponses.deleteAll(UserResponses.class);
                            SugarRecord.saveInTx(questionList);
                            SugarRecord.saveInTx(userResponses);


                        } catch (JSONException e) {
                            e.printStackTrace();
                            Log.e(TAG, "Exception Occured" + e.getMessage());

                        }
                        callback.onSuccessResponse(response);

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //You can handle error here if you want
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                //Adding parameters to request
                params.put(Config.KEY_EXAM_CODE, exam_code_);
                //returning parameter
                return params;
            }
        };

        //Adding the string request to the queue
        RequestQueue requestQueue = Volley.newRequestQueue(Base.getContext());
        requestQueue.add(stringRequest);
    }


}
