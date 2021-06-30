package com.example.my_coach.ui;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
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
    private Boolean isFirstOpen=true;
    private EditText SearchBox;
     //test favorite



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sports);
        //test fav

        progressBar = findViewById(R.id.progres_sports_categories);
        recyclerView = findViewById(R.id.recycler_sports_categories);
        firestore = FirebaseFirestore.getInstance();
        SearchBox=findViewById (R.id.search_Sport);



        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        //test






        list = new ArrayList<>();

        adapter = new SportAdapter(this, list);
        recyclerView.setAdapter(adapter);
         SearchBox.addTextChangedListener (new TextWatcher () {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                filter(s.toString ());
            }


         });

        getSportsData();




    }
    private void filter(String text)
    {
        ArrayList<SportModel> filterdList=new ArrayList<> ();
        for (SportModel sportModel :list){
            if (sportModel.getName ().toLowerCase ().contains (text.toLowerCase ())){
                filterdList.add(sportModel);
            }
        }
        adapter.filterlist(filterdList);
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
                                if (!isFirstOpen)
                                    list.clear();
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