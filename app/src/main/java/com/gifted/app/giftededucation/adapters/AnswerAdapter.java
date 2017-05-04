package com.gifted.app.giftededucation.adapters;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.gifted.app.giftededucation.R;
import com.gifted.app.giftededucation.pojo.UserResponses;
import com.thefinestartist.Base;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class AnswerAdapter extends RecyclerView.Adapter<AnswerAdapter.MyViewHolder> {

    private String[] options_array = {"a.)", "b.)", "c.)", "d.)", "e.)", "f.)"};
    private String[] options_values = {"a", "b", "c", "d", "e", "f"};

    private int selected_position = -1;

    private JSONObject jsonObject = null;

    private int length_options;

    private String right_answer;

    private String question;


    public AnswerAdapter(int length, JSONObject jsonObject, String rightAnswer, String question) {
        this.jsonObject = jsonObject;
        this.length_options = length;
        this.right_answer = rightAnswer;
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

                // Updating old as well as new positions
                notifyItemChanged(selected_position);
                selected_position = holder.getAdapterPosition();
                notifyItemChanged(selected_position);
                Toast.makeText(Base.getContext(), "Right Answer is :" + right_answer, Toast.LENGTH_SHORT).show();

                List<UserResponses> responses = new ArrayList<>();
                long count = UserResponses.count(UserResponses.class);
                Log.e("Selected Answer", count + "");

                if (count > 0) {
                    responses = UserResponses.listAll(UserResponses.class);
                    Log.e("Answer", responses.toString());
                }

               /* if (responses == null) {
                    //querying list return empty, there is no record found matching the query.
                    Log.e(AnswerAdapter.class.getSimpleName(), "No Response");
                } else {
                    List<UserResponses> userResponses = UserResponses.findWithQuery(UserResponses.class, "Select * from UserResponses where question = ?", question);
                    Log.e("Selected Answer", (selected_position + 1) + "" + userResponses.get(0).getId());

                    SugarRecord.saveInTx(userResponses.get(0).getId());
                }*/

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

}