package com.gifted.app.giftededucation.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.gifted.app.giftededucation.R;
import com.gifted.app.giftededucation.adapters.LevelsAdapter;
import com.thefinestartist.Base;

public class LevelsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_levels);

        Toolbar toolbarTop = (Toolbar) findViewById(R.id.toolbar_top);
        TextView mTitle = (TextView) toolbarTop.findViewById(R.id.toolbar_title);
        mTitle.setText(R.string.app_name);

        RecyclerView levels_recdycler = (RecyclerView) findViewById(R.id.levels_recycler);
        LevelsAdapter adapter = new LevelsAdapter();
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(Base.getContext(), 2);
        levels_recdycler.setLayoutManager(mLayoutManager);
        levels_recdycler.setItemAnimator(new DefaultItemAnimator());
        levels_recdycler.setNestedScrollingEnabled(false);
        levels_recdycler.setAdapter(adapter);



        adapter.setOnItemClickListener(new LevelsAdapter.MyClickListener() {
            @Override
            public void onItemClick(int position, View v) {
                startActivity(new Intent(Base.getContext(), InstructionsActivity.class));
            }
        });

    }


}
