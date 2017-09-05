package com.gifted.app.giftededucation.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.gifted.app.giftededucation.R;
import com.gifted.app.giftededucation.utils.Config;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.thefinestartist.utils.preferences.Pref;

import org.json.JSONException;
import org.json.JSONObject;

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
        String json_data = getIntent().getExtras().getString("data");
        score_text = (TextView) findViewById(R.id.score_text);

        pieChart = (PieChart) findViewById(R.id.chart1);

        entries = new ArrayList<>();

        PieEntryLabels = new ArrayList<String>();


        pieDataSet = new PieDataSet(entries, "");



        try {
            JSONObject jsonObject = new JSONObject(json_data);
            JSONObject jsonObject1 = jsonObject.optJSONObject("result_details");


            String marks_obtained = jsonObject1.optString("marks_obtained");

           /* String percentage = jsonObject1.optString("percentage");

            int p = Pref.get(Config.KEY_LAST_QUE, 30);*/
            int correct = Integer.parseInt(marks_obtained) / 4;
            int incorrect = Pref.get(Config.KEY_LAST_QUE, 30) - correct;

            AddValuesToPIEENTRY(correct, incorrect);

            AddValuesToPieEntryLabels();

            pieData = new PieData(PieEntryLabels, pieDataSet);


            pieDataSet.setColors(ColorTemplate.COLORFUL_COLORS);

            pieChart.setData(pieData);

            pieChart.animateY(3000);

            score_text.setText("Thanks for taking the test.You scored " + marks_obtained + " Marks in the test.Your certificate will be mailed to your Email id.");


        } catch (JSONException e) {
            e.printStackTrace();
        }


    }

    public void AddValuesToPIEENTRY(int count, int incorrect) {
        entries.add(new BarEntry(count, 0));
        entries.add(new BarEntry(incorrect, 1));

    }

    public void AddValuesToPieEntryLabels() {
        PieEntryLabels.add("Correct");
        PieEntryLabels.add("Incorrect");
    }


}
