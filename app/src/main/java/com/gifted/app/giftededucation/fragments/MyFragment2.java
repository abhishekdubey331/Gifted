package com.gifted.app.giftededucation.fragments;

import android.content.DialogInterface;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.gifted.app.giftededucation.R;
import com.gifted.app.giftededucation.adapters.AnswerAdapter;
import com.gifted.app.giftededucation.adapters.ImageAnswerAdapter;
import com.gifted.app.giftededucation.utils.MakingJsonResponse;
import com.thefinestartist.Base;
import com.thefinestartist.utils.preferences.Pref;

import org.json.JSONException;
import org.json.JSONObject;


public class MyFragment2 extends Fragment {
    private static final String json_of_options = "param1";
    private static final String question = "param2";
    private static final String que_image = "que_image";

    private static final String que_number = "que_number";

    private static final String right_answer_ = "right_answer";

    // TODO: Rename and change types of parameters
    private String options_json;
    private String question_;
    private String que_image_;

    private String que_number_;

    private String getRight_answer;

    public MyFragment2() {
        // Required empty public constructor
    }

    public static MyFragment2 newInstance(String json_options, String questions, String image_que, String que_num, String right_answer) {
        MyFragment2 fragment = new MyFragment2();
        Bundle args = new Bundle();
        args.putString(json_of_options, json_options);
        args.putString(question, questions);
        args.putString(que_image, image_que);
        args.putString(que_number, que_num);
        args.putString(right_answer_, right_answer);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            options_json = getArguments().getString(json_of_options);
            question_ = getArguments().getString(question);
            que_image_ = getArguments().getString(que_image);
            que_number_ = getArguments().getString(que_number);
            getRight_answer = getArguments().getString(right_answer_);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ImageAnswerAdapter mAdapter;
        TextView question, question_number;
        Button proceed;
        Typeface typeface;
        ImageView image_for_que;
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_my, container, false);
        question = (TextView) rootView.findViewById(R.id.question);
        image_for_que = (ImageView) rootView.findViewById(R.id.image_view);
        question_number = (TextView) rootView.findViewById(R.id.question_number);
        proceed = (Button) rootView.findViewById(R.id.proceed_submission);
        typeface = Typeface.createFromAsset(Base.getAssets(), "fonts/MuseoSans_500.otf");
        if ((Integer.parseInt(que_number_) + 1) == Pref.get("last", 30)) {
            proceed.setVisibility(View.VISIBLE);
            proceed.setTypeface(typeface);
            proceed.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    new AlertDialog.Builder(getContext())
                            .setTitle("Submit Test")
                            .setMessage("Are you sure you have completed your test?")
                            .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    // continue with delete
                                    MakingJsonResponse makingJsonResponse = new MakingJsonResponse(getContext());
                                    String respo = Pref.get("Response", "").replaceAll("\\\\", "").substring(1);
                                    makingJsonResponse.makingJson("[" + respo + "]");
                                }
                            })
                            .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    // do nothing
                                }
                            })
                            .setIcon(android.R.drawable.ic_dialog_alert)
                            .show();
                }
            });

        }


        // Picasso.with(Base.getContext()).load(Config.S3_BASE_URL).into(image_for_que);

        question.setText(question_);

        question_number.setText("Q." + (Integer.parseInt(que_number_) + 1));

        JSONObject jsonObject = null;

        try {

            jsonObject = new JSONObject(options_json);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        RecyclerView recyclerView = (RecyclerView) rootView.findViewById(R.id.answers_recycler);
        mAdapter = new ImageAnswerAdapter(jsonObject != null ? jsonObject.length() : 0, jsonObject, getRight_answer, Integer.parseInt(que_number_));
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(Base.getContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

        return rootView;
    }

}
