package com.gifted.app.giftededucation.adapters;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gifted.app.giftededucation.Global;
import com.gifted.app.giftededucation.R;
import com.greendao.db.DaoSession;
import com.greendao.db.UserResponses;
import com.greendao.db.UserResponsesDao;
import com.thefinestartist.Base;
import com.thefinestartist.utils.preferences.Pref;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class AnswerAdapter extends RecyclerView.Adapter<AnswerAdapter.MyViewHolder> {

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


    public AnswerAdapter(int length, JSONObject jsonObject, String rightAnswer, int question) {
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
                .inflate(R.layout.custom_answer, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        try {
            holder.answers.setText(jsonObject.getString(options_values[position]));
        } catch (JSONException e) {
            e.printStackTrace();
            Log.e("JsonException", e.getMessage());
        }
        holder.options.setText(options_array[position]);

        final UserResponses userResponses = getAppDaoSession().getUserResponsesDao()
                .queryBuilder().where(UserResponsesDao.Properties.Id.eq(question_number)).unique();


        if (!userResponses.getUser_response().contentEquals("")) {
            int pos = get_position(userResponses.getUser_response());
            if (pos == position) {
                holder.answers.setSelected(true);
                holder.options.setSelected(true);
                holder.answers.setBackgroundColor(Color.parseColor("#FFC80D"));
                holder.options.setBackgroundColor(Color.parseColor("#FFC80D"));
                holder.answers.setTextColor(Color.parseColor("#FFFFFF"));
                holder.options.setTextColor(Color.parseColor("#FFFFFF"));
            }
        }


        if (selected_position == position) {
            // Here I am just highlighting the background
            holder.answers.setSelected(true);
            holder.options.setSelected(true);
            holder.answers.setTextColor(Color.parseColor("#FFFFFF"));
            holder.options.setTextColor(Color.parseColor("#FFFFFF"));

        } else {
            holder.answers.setSelected(false);
            holder.options.setSelected(false);
            holder.answers.setTextColor(Color.parseColor("#404040"));
            holder.options.setTextColor(Color.parseColor("#404040"));
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                selected_position = holder.getAdapterPosition();
                notifyItemChanged(selected_position);
                notifyItemChanged(get_position(userResponses.getUser_response()));
                // Toast.makeText(Base.getContext(), "Right Answer is :" + right_answer, Toast.LENGTH_SHORT).show();

                UserResponses userResponses = getAppDaoSession().getUserResponsesDao()
                        .queryBuilder().where(UserResponsesDao.Properties.Id.eq(question_number)).unique();

                userResponses.setUser_response(options_values[selected_position]);
                getAppDaoSession().getUserResponsesDao().update(userResponses);
                try {
                    other_details.put("Q_ID", userResponses.getQ_no());
                    other_details.put("right_answer", right_answer);
                    other_details.put("user_response", options_values[selected_position]);
                    other_details.put("max_marks", 4);
                    jsonarray.put(other_details);
                    Pref.put("Response", Pref.get("Response", "") + "," + jsonarray.toString().replace("[", "").replace("]", ""));

                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        });
    }

    @Override
    public int getItemCount() {
        return length_options;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View
            .OnClickListener {
        private TextView answers, options;

        private MyViewHolder(View view) {
            super(view);
            answers = (TextView) view.findViewById(R.id.answer);
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