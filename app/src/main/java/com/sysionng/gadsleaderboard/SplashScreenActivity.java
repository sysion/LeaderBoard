package com.sysionng.gadsleaderboard;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class SplashScreenActivity extends AppCompatActivity {
    private Context mContext;
    private final int SPLASHSCREEN_TIMEOUT = 1500;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mContext = this;

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(mContext, MainActivity.class);
                startActivity(intent);      //launch MainActivity

                finish();   //end this SplashScreenActivity
            }
        }, SPLASHSCREEN_TIMEOUT);   //time in ms

    }


}
