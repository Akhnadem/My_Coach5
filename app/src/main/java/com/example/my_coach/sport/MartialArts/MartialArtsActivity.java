package com.example.my_coach.sport.MartialArts;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.my_coach.R;
import com.example.my_coach.sport.MartialArts.Boxing.Boxing1;
import com.example.my_coach.sport.MartialArts.Karate.Karate;
import com.example.my_coach.sport.MartialArts.KungFu.KungFu;

public class MartialArtsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_martial_arts);
        findViewById(R.id.KungFu).setOnClickListener(v -> startActivity(new Intent(MartialArtsActivity.this, KungFu.class)));
        findViewById(R.id.Karate).setOnClickListener(v -> startActivity(new Intent(MartialArtsActivity.this, Karate.class)));
        findViewById(R.id.Boxing).setOnClickListener(v -> startActivity(new Intent(MartialArtsActivity.this, Boxing1.class)));

    }
}