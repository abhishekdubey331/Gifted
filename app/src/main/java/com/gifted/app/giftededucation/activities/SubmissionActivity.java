package com.gifted.app.giftededucation.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.gifted.app.giftededucation.R;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class SubmissionActivity extends AppCompatActivity {

    private static final String TAG = SubmissionActivity.class.getSimpleName();
    PieChart pieChart;
    ArrayList<Entry> entries;
    ArrayList<String> PieEntryLabels;
    PieDataSet pieDataSet;
    PieData pieData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submission);
        pieChart = (PieChart) findViewById(R.id.chart1);

        entries = new ArrayList<>();

        PieEntryLabels = new ArrayList<String>();

        AddValuesToPIEENTRY();

        AddValuesToPieEntryLabels();

        pieDataSet = new PieDataSet(entries, "");

        pieData = new PieData(PieEntryLabels, pieDataSet);

        pieDataSet.setColors(ColorTemplate.COLORFUL_COLORS);

        pieChart.setData(pieData);

        pieChart.animateY(3000);

    }

    public void AddValuesToPIEENTRY() {

        entries.add(new BarEntry(2f, 0));
        entries.add(new BarEntry(4f, 1));
        entries.add(new BarEntry(6f, 2));
        entries.add(new BarEntry(8f, 3));

    }

    public void AddValuesToPieEntryLabels() {
        PieEntryLabels.add("Attempted");
        PieEntryLabels.add("Correct");
        PieEntryLabels.add("Incorrect");
        PieEntryLabels.add("Unattempted");
    }

   /* private void set_card() {
        Typeface typeface;

        Button submit = (Button) findViewById(R.id.logout);
        TextView name_ = (TextView) findViewById(R.id.name_);

        TextView classs_ = (TextView) findViewById(R.id.classs_);
        TextView school_ = (TextView) findViewById(R.id.school_);
        TextView email_ = (TextView) findViewById(R.id.email_);
        typeface = Typeface.createFromAsset(Base.getAssets(), "fonts/MuseoSans_500.otf");
        submit.setTypeface(typeface);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Base.getContext(), LogInActivity.class));
                finish();
            }
        });


        try {
            JSONObject jsonObject = new JSONObject(Pref.get(Config.USER_OBJECT, "Soo"));
            JSONObject jsonObject1 = jsonObject.getJSONObject("nameValuePairs");
            name_.setText(jsonObject1.getString("name"));
            classs_.setText(jsonObject1.getString("class"));
            school_.setText(jsonObject1.getString("school"));
            email_.setText(jsonObject1.getString("email"));

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }*/


}
