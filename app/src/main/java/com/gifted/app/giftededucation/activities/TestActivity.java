package com.gifted.app.giftededucation.activities;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.gifted.app.giftededucation.Global;
import com.gifted.app.giftededucation.R;
import com.gifted.app.giftededucation.adapters.MyPageAdapter;
import com.gifted.app.giftededucation.fragments.MyFragment;
import com.gifted.app.giftededucation.fragments.MyFragment2;
import com.gifted.app.giftededucation.fragments.ReviewFragment;
import com.greendao.db.DaoSession;
import com.greendao.db.Question;
import com.thefinestartist.Base;

import java.util.ArrayList;
import java.util.List;

public class TestActivity extends AppCompatActivity {
    MyPageAdapter pageAdapter;
    boolean showingFirst = true;
    LinearLayout main_layout;
    private static final String TAG = TestActivity.class.getName();
    private TextView timer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        Toolbar toolbarTop = (Toolbar) findViewById(R.id.toolbar_top);
        setSupportActionBar(toolbarTop);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        TextView mTitle = (TextView) toolbarTop.findViewById(R.id.toolbar_title);

        List<Fragment> fragments = getFragments();
        pageAdapter = new MyPageAdapter(getSupportFragmentManager(), fragments);
        main_layout = (LinearLayout) findViewById(R.id.main_layout);
        timer = (TextView) findViewById(R.id.timer);
        ViewPager pager = (ViewPager) findViewById(R.id.viewpager);
        pager.setAdapter(pageAdapter);

        setCountDownTimer();
    }

    private void setCountDownTimer() {
        int hoursToGo = 0;
        int minutesToGo = 30;
        int secondsToGo = 00;

        int millisToGo = secondsToGo * 1000 + minutesToGo * 1000 * 60 + hoursToGo * 1000 * 60 * 60;

        new CountDownTimer(millisToGo, 1000) {

            @Override
            public void onTick(long millis) {
                int seconds = (int) (millis / 1000) % 60;
                int minutes = (int) ((millis / (1000 * 60)) % 60);
                String text = String.format("%02d mins : %02d sec", minutes, seconds);
                timer.setText(text);
            }

            @Override
            public void onFinish() {
                timer.setText("Done..!!");
                startActivity(new Intent(Base.getContext(), SubmissionActivity.class));
                finish();
            }
        }.start();

    }

    private List<Fragment> getFragments() {
        List<Fragment> fList = new ArrayList<Fragment>();
        List<Question> questionList = getAppDaoSession().getQuestionDao().loadAll();
        for (Question question : questionList) {
            System.out.println(question);
            if (question.getQ_code().contentEquals("QTX")) {
                fList.add(MyFragment.newInstance(question.getOption_json(), question.getQuestion(), question.getImage(), question.getId() + "", question.getAnswer()));

            } else if(question.getQ_code().contentEquals("QIM")) {
                fList.add(MyFragment2.newInstance(question.getOption_json(), question.getQuestion(), question.getImage(), question.getId() + "", question.getAnswer()));

            }

        }
        return fList;
    }


    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.my_options_menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.review_action:

                if (showingFirst) {
                    ReviewFragment newFragment = new ReviewFragment();
                    FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                    transaction.setCustomAnimations(R.anim.slide_in_left, 0, 0, R.anim.slide_out_right);
                    transaction.replace(R.id.container, newFragment);
                    transaction.addToBackStack(null);
                    transaction.commit();
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            main_layout.setAlpha((float) 0.5);
                        }
                    }, 500);

                    showingFirst = false;
                } else {
                    removeFragment();
                }

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    public void removeFragment() {
        main_layout.setAlpha(1);


        getSupportFragmentManager().popBackStack();
        showingFirst = true;
    }

    @Override
    public void onBackPressed() {
        int backStackEntryCount = getSupportFragmentManager().getBackStackEntryCount();
        if (backStackEntryCount == 0) {
            Log.e(TAG, "Last");
        } else {
            main_layout.setAlpha(1);
            showingFirst = true;
            super.onBackPressed();
        }

    }

    private DaoSession getAppDaoSession() {
        return ((Global) Base.getContext()).getSession();
    }


}