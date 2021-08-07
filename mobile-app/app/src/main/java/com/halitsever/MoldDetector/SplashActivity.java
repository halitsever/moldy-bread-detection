package com.halitsever.MoldDetector;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;

import com.halitsever.MoldDetector.util.ConnectionTester;


public class SplashActivity extends AppCompatActivity {


    ConnectionTester ConnectionStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        addFadeinAnim();




        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {


                ConnectionStatus = new ConnectionTester(getApplicationContext());


                if (ConnectionStatus.isConnectingToInternet() == false) {
                    Intent ErrorActivity = new Intent(getApplicationContext(), ErrorActivity.class);
                    startActivity(ErrorActivity);
                    finish();
                } else {
                    Intent MainActivity = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(MainActivity);
                    finish();


                }

            }
        }, 10000);





    }
    private void addFadeinAnim() {
        Animation fadeIn = new AlphaAnimation(0, 1);
        fadeIn.setInterpolator(new DecelerateInterpolator());
        fadeIn.setDuration(2000);
        fadeIn.setRepeatCount(Animation.INFINITE);
        ImageView splashImage = (ImageView)findViewById(R.id.splashImage);
        splashImage.startAnimation(fadeIn);
    }
}