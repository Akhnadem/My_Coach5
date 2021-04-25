package com.example.my_coach.sport.bodybulding;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.my_coach.R;
import com.example.my_coach.sport.bodybulding.Running.Running;
import com.example.my_coach.sport.bodybulding.Swimming.Swimming;
import com.example.my_coach.sport.bodybulding.WigehtLifeThing.WigehtLifeThing;

public class bodybuilding_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bodybuilding_);
        findViewById(R.id.Swimming).setOnClickListener(v -> startActivity(new Intent(bodybuilding_Activity.this, Swimming.class)));
        findViewById(R.id.Running).setOnClickListener(v -> startActivity(new Intent(bodybuilding_Activity.this, Running.class)));
        findViewById(R.id.WgehtLifething).setOnClickListener(v -> startActivity(new Intent(bodybuilding_Activity.this,WigehtLifeThing.class)));

    }
}