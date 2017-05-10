package com.gifted.app.giftededucation.adapters;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gifted.app.giftededucation.Global;
import com.gifted.app.giftededucation.R;
import com.gifted.app.giftededucation.activities.TestActivity;
import com.gifted.app.giftededucation.customviews.CircularTextView;
import com.greendao.db.DaoSession;
import com.greendao.db.UserResponses;
import com.greendao.db.UserResponsesDao;
import com.thefinestartist.Base;
import com.thefinestartist.utils.preferences.Pref;


public class OptionsAdapter extends RecyclerView.Adapter<OptionsAdapter.MyViewHolder> {
    private ViewPager viewPager;
    private Context mContext;

    public OptionsAdapter(ViewPager pager, Context context) {
        this.viewPager = pager;
        this.mContext = context;
    }

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
        holder.que_review.setText(String.valueOf(position + 1));
        UserResponses userResponses = getAppDaoSession().getUserResponsesDao()
                .queryBuilder().where(UserResponsesDao.Properties.Id.eq(position)).unique();
        if (!userResponses.getUser_response().contentEquals("")) {
            holder.que_review.setSolidColor("#27ae60");
        }
    }

    @Override
    public int getItemCount() {
        return Pref.get("last", 0);
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
            viewPager.setCurrentItem(getAdapterPosition());
            ((TestActivity) mContext).removeFragment();
        }
    }

    private DaoSession getAppDaoSession() {
        return ((Global) Base.getContext()).getSession();
    }


}