package com.gifted.app.giftededucation.adapters;

import android.graphics.Color;
import android.media.Image;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.gifted.app.giftededucation.Global;
import com.gifted.app.giftededucation.R;
import com.greendao.db.DaoSession;
import com.greendao.db.UserResponses;
import com.greendao.db.UserResponsesDao;
import com.squareup.picasso.Picasso;
import com.thefinestartist.Base;
import com.thefinestartist.utils.preferences.Pref;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class ImageAnswerAdapter extends RecyclerView.Adapter<ImageAnswerAdapter.MyViewHolder> {

    private String[] options_array = {"a.)", "b.)", "c.)", "d.)", "e.)", "f.)"};

    private String[] options_values = {"a", "b", "c", "d", "e", "f"};

    private int selected_position = -1;

    private JSONObject jsonObject = null;

    private int length_options;

    private String right_answer;

    private int question_number;

    private JSONObject responses_obj;

    private JSONObject other_details;

    private JSONArray jsonarray;



    public ImageAnswerAdapter(int length, JSONObject jsonObject, String rightAnswer, int question) {
        this.jsonObject = jsonObject;
        this.length_options = length;
        this.right_answer = rightAnswer;
        question_number = question;
        responses_obj = new JSONObject();
        other_details = new JSONObject();
        jsonarray = new JSONArray();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.qimg_layout, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {

        Picasso.with(Base.getContext()).load("http://i.imgur.com/DvpvklR.png").into(holder.answer_image);

    }

    @Override
    public int getItemCount() {
        return length_options;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View
            .OnClickListener {
        private TextView  options;
        private ImageView answer_image;

        private MyViewHolder(View view) {
            super(view);
            answer_image = (ImageView) view.findViewById(R.id.answer_image);
            options = (TextView) view.findViewById(R.id.options);
            view.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
        }
    }

    private DaoSession getAppDaoSession() {
        return ((Global) Base.getContext()).getSession();
    }


    private int get_position(String chara) {
        int b = 0;
        switch (chara) {

            case "a":
                b = 1;
                break;

            case "b":
                b = 2;
                break;

            case "c":
                b = 3;
                break;

            case "d":
                b = 4;
                break;

            case "e":
                b = 5;
                break;

            case "f":
                b = 6;
                break;


        }


        return b - 1;
    }


}