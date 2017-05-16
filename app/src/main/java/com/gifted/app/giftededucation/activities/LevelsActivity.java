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
import android.widget.Toast;

import com.gifted.app.giftededucation.R;
import com.gifted.app.giftededucation.adapters.LevelsAdapter;
import com.gifted.app.giftededucation.utils.Config;
import com.thefinestartist.Base;
import com.thefinestartist.utils.preferences.Pref;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class LevelsActivity extends AppCompatActivity {
    private List<String> levels_allowed = new ArrayList<>();

    private List<String> levels_taken = new ArrayList<>();
    private String[] levels = {"SJI", "SJA", "JI", "JA", "SI", "SA"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setData();
        setContentView(R.layout.activity_levels);

        Toolbar toolbarTop = (Toolbar) findViewById(R.id.toolbar_top);
        TextView mTitle = (TextView) toolbarTop.findViewById(R.id.toolbar_title);
        mTitle.setText(R.string.app_name);

        RecyclerView levels_recdycler = (RecyclerView) findViewById(R.id.levels_recycler);
        LevelsAdapter adapter = null;

        adapter = new LevelsAdapter();
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(Base.getContext(), 2);
        levels_recdycler.setLayoutManager(mLayoutManager);
        levels_recdycler.setItemAnimator(new DefaultItemAnimator());
        levels_recdycler.setNestedScrollingEnabled(false);
        levels_recdycler.setAdapter(adapter);


        adapter.setOnItemClickListener(new LevelsAdapter.MyClickListener() {
            @Override
            public void onItemClick(int position, View v) {

                if (levels_allowed.contains(levels[position])) {
                    Pref.put(Config.LEVEL, levels[position]);
                    startActivity(new Intent(Base.getContext(), InstructionsActivity.class));

                } else {
                    Toast.makeText(Base.getContext(), "Not Allowed", Toast.LENGTH_LONG).show();
                }

            }
        });

    }


    private void setData() {
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(Pref.get(Config.KEY_EXAMS_ALLOWED, ""));
            for (int i = 0; i < jsonObject.getJSONArray("values").length(); i++)
                levels_allowed.add(jsonObject.getJSONArray("values").get(i).toString());
            JSONObject jsonObject2 = new JSONObject(Pref.get(Config.KEY_EXAMS_TAKEN, ""));
            for (int i = 0; i < jsonObject2.getJSONArray("values").length(); i++)
                levels_taken.add(jsonObject.getJSONArray("values").get(i).toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }


    }


}
