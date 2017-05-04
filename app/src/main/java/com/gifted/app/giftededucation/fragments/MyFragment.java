package com.gifted.app.giftededucation.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.gifted.app.giftededucation.R;
import com.gifted.app.giftededucation.adapters.AnswerAdapter;
import com.thefinestartist.Base;

import org.json.JSONException;
import org.json.JSONObject;


public class MyFragment extends Fragment {
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

    public MyFragment() {
        // Required empty public constructor
    }

    public static MyFragment newInstance(String json_options, String questions, String image_que, String que, String right_answer) {
        MyFragment fragment = new MyFragment();
        Bundle args = new Bundle();
        args.putString(json_of_options, json_options);
        args.putString(question, questions);
        args.putString(que_image, image_que);
        args.putString(que_number, que);
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
        AnswerAdapter mAdapter;
        TextView question, question_number;
        ImageView image_for_que;

        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_my, container, false);
        question = (TextView) rootView.findViewById(R.id.question);
        image_for_que = (ImageView) rootView.findViewById(R.id.image_view);
        question_number = (TextView) rootView.findViewById(R.id.question_number);

        // Picasso.with(Base.getContext()).load(Config.S3_BASE_URL).into(image_for_que);

        question.setText(question_);

        question_number.setText("Q." + que_number_);

        JSONObject jsonObject = null;

        try {

            jsonObject = new JSONObject(options_json);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        RecyclerView recyclerView = (RecyclerView) rootView.findViewById(R.id.answers_recycler);
        mAdapter = new AnswerAdapter(jsonObject != null ? jsonObject.length() : 0, jsonObject,getRight_answer);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(Base.getContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

        return rootView;
    }

}
