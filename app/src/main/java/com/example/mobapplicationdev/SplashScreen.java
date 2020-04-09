package com.example.mobapplicationdev;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import gr.net.maroulis.library.EasySplashScreen;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        EasySplashScreen config = new EasySplashScreen(SplashScreen.this)
                .withFullScreen()
                .withTargetActivity(SignUp.class)
                .withSplashTimeOut(5000)
                .withBackgroundColor(Color.parseColor("#4a0072"))
                .withLogo(R.mipmap.app_icon);

        View view = config.create();
        setContentView(view);
    }
}
