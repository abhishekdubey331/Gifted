package com.gifted.app.giftededucation.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.gifted.app.giftededucation.R;
import com.gifted.app.giftededucation.utils.GetAllCounts;
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
    private TextView score_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submission);
        pieChart = (PieChart) findViewById(R.id.chart1);
        score_text=(TextView)findViewById(R.id.score_text);

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
        GetAllCounts getAllCounts=new GetAllCounts();
          int score=getAllCounts.getCorrectCount()*4;

        entries.add(new BarEntry(getAllCounts.getCorrectCount(), 0));
        entries.add(new BarEntry(getAllCounts.getIncorrectCount(),1));
        score_text.setText("Thanks for taking the test.You scored "+score+" Marks in the test.Your certificate will be mailed to your Email id.");

    }

    public void AddValuesToPieEntryLabels() {
        PieEntryLabels.add("Correct");
        PieEntryLabels.add("Incorrect");
    }


}
