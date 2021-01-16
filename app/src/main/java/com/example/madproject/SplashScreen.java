package com.example.madproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;


public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);


        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run()
            {
                startActivity(new Intent(SplashScreen.this, MainActivity.class));
                //startActivity(new Intent(SplashScreen.this,AddActivity.class));
                finish();

            }

        },2500);
    }
    public void Login(){
        Intent intent= new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}

