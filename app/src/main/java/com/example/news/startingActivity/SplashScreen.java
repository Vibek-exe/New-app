package com.example.news.startingActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.airbnb.lottie.LottieAnimationView;
import com.example.news.R;


public class SplashScreen extends AppCompatActivity {

    private LottieAnimationView lottie;
    private TextView quote;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash_screen);

        quote = findViewById(R.id.slogan);
        lottie = findViewById(R.id.lottie);

        quote.animate().translationY(-1600).setDuration(2700).setStartDelay(0);
        lottie.animate().translationY(1800).setDuration(2000).setStartDelay(2900);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                Intent i = new Intent(getApplicationContext(), home.class);
                startActivity(i);
                finish();
            }
        },5000);

    }

}
