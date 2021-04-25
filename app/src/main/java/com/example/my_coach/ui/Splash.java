package com.example.my_coach.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;

import com.example.my_coach.R;
import com.example.my_coach.ui.auth.Login;

public class Splash extends AppCompatActivity {

    private String currentlanguage, language;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //to mack activity full screen
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);



        new Handler().postDelayed(() -> {

            startActivity(new Intent(Splash.this, Login.class));
            finish();
        }, 3000);

    }


}