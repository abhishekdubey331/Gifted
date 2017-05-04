package com.gifted.app.giftededucation.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.gifted.app.giftededucation.R;
import com.gifted.app.giftededucation.utils.Config;
import com.thefinestartist.Base;


public class LevelsAdapter extends RecyclerView.Adapter<LevelsAdapter.ViewHolder> {
    private MyClickListener myClickListener;


    public LevelsAdapter() {
    }

    @Override
    public LevelsAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.custom_level_image, viewGroup, false);
        return new ViewHolder(view);
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ImageView level_image;

        @Override
        public void onClick(View view) {
            myClickListener.onItemClick(getAdapterPosition(), view);


        }

        private ViewHolder(View view) {
            super(view);

            level_image = (ImageView) view.findViewById(R.id.level_image);


            view.setOnClickListener(this);


        }
    }

    @Override
    public void onBindViewHolder(LevelsAdapter.ViewHolder viewHolder, int i) {
        Glide.with(Base.getContext())
                .load(Config.S3_BASE_URL + (i + 1) + Config.IMAGE_TYPE)
                .centerCrop()
                .placeholder(R.drawable.progress_animation)
                .crossFade()
                .into(viewHolder.level_image);
    }

    public void setOnItemClickListener(MyClickListener myClickListener) {
        this.myClickListener = myClickListener;
    }

    @Override
    public int getItemCount() {
        return 6;
    }

    public interface MyClickListener {
        public void onItemClick(int position, View v);

    }


}