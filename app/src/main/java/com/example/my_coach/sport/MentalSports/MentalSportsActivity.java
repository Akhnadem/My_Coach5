package com.example.my_coach.sport.MentalSports;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.my_coach.R;
import com.example.my_coach.sport.MentalSports.Chess.Chess;
import com.example.my_coach.sport.MentalSports.Sudoku.Sudoku;

public class MentalSportsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mental_sports);
        findViewById(R.id.Chess).setOnClickListener(v -> startActivity(new Intent(MentalSportsActivity.this, Chess.class)));
        findViewById(R.id.Sudoku).setOnClickListener(v -> startActivity(new Intent(MentalSportsActivity.this, Sudoku.class)));

    }
}