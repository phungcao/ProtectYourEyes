package com.example.phung.protectyoureyes;

import android.content.DialogInterface;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.concurrent.TimeUnit;

public class Time extends AppCompatActivity {
    TextView stime;
    SeekBar ngan;
    Button set;
    private CountDownTimer countDownTimer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        stime = (TextView)findViewById(R.id.stime);
        ngan = (SeekBar)findViewById(R.id.sbShort);
        set = (Button)findViewById(R.id.btSet);
        setSupportActionBar(toolbar);
        stime.setText( ngan.getProgress() +" "+ "phút");

        set.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startTimer(ngan.getProgress() * 1000);
            }
        });

    }
    private void startTimer(final long milisecods) {
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
        countDownTimer = new CountDownTimer(milisecods, 500) {
            @Override
            public void onTick(long leftTimeInMilliseconds) {

                int barVal = (int) leftTimeInMilliseconds / 1000;
                ngan.setProgress(barVal);
                stime.setText(String.format("%02d:%02d",
                        TimeUnit.MILLISECONDS.toMinutes(leftTimeInMilliseconds),
                        TimeUnit.MILLISECONDS.toSeconds(leftTimeInMilliseconds) -
                                TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(leftTimeInMilliseconds))
                ));
            }
            @Override
            public void onFinish() {

            }
        }.start();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

}
