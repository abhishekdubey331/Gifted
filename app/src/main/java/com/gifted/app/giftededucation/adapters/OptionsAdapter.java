package com.gifted.app.giftededucation.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gifted.app.giftededucation.R;
import com.gifted.app.giftededucation.customviews.CircularTextView;


public class OptionsAdapter extends RecyclerView.Adapter<OptionsAdapter.MyViewHolder> {


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.custom_review, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        holder.que_review.setStrokeWidth(1);
        holder.que_review.setStrokeColor("#ffffff");
        holder.que_review.setSolidColor("#f1c40f");
        if (position==3)
        {
            holder.que_review.setText("2");
        }
    }

    @Override
    public int getItemCount() {
        return 10;
    }

     class MyViewHolder extends RecyclerView.ViewHolder implements View
            .OnClickListener {
        private CircularTextView que_review;

        private MyViewHolder(View view) {
            super(view);
            que_review = (CircularTextView) view.findViewById(R.id.circularTextView);
            view.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
        }
    }

}