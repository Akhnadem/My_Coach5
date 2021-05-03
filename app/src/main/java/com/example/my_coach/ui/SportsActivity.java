package com.example.my_coach.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.my_coach.Model.SportModel;
import com.example.my_coach.R;
import com.example.my_coach.adapter.SportAdapter;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

public class SportsActivity extends AppCompatActivity {

    private ProgressBar progressBar;
    private RecyclerView recyclerView;
    private FirebaseFirestore firestore;
    private List<SportModel> list;
    private SportAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sports);
        progressBar = findViewById(R.id.progres_sports);
        recyclerView = findViewById(R.id.recycler_sports);
        firestore = FirebaseFirestore.getInstance();


        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        list = new ArrayList<>();

        adapter = new SportAdapter(this, list);
        recyclerView.setAdapter(adapter);

        getSportsData();
    }

    private void getSportsData() {

         String category_id = getIntent().getStringExtra("category_id");
        progressBar.setVisibility(View.VISIBLE);
        firestore.collection("sport")
                .addSnapshotListener((value, error) -> {

                    if (error == null) {

                        if (value == null) {
                            progressBar.setVisibility(View.GONE);
                            Toast.makeText(this, "value is null", Toast.LENGTH_SHORT).show();
                        } else {
                            for (DocumentChange documentChange : value.getDocumentChanges()) {
                                String ID = documentChange.getDocument().getString("category_id");

                                assert ID != null;
                                if (ID.equals(category_id)) {
                                    SportModel model = documentChange.getDocument().toObject(SportModel.class);
                                    list.add(model);
                                    adapter.notifyDataSetChanged();
                                 }

                            }
                            progressBar.setVisibility(View.GONE);
                        }


                    } else {
                        progressBar.setVisibility(View.GONE);
                        Toast.makeText(this, error.getMessage(), Toast.LENGTH_SHORT).show();
                    }


                });
    }

}