package com.example.my_coach.sport.SportsProperties;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.my_coach.Model.ExerciseModel;
import com.example.my_coach.R;
import com.example.my_coach.adapter.ExerciseAdapter;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;


public class ExerciseFragment extends Fragment {

    private ProgressBar progressBar;
    private RecyclerView recyclerView;
    private FirebaseFirestore firestore;
    private List<ExerciseModel> list;
    private ExerciseAdapter adapter;
    private Boolean isFirstOpen=true;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_exercise, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        progressBar = view.findViewById(R.id.progres_Exercise);
        recyclerView = view.findViewById(R.id.recycler_Exercise);
        firestore = FirebaseFirestore.getInstance();

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager (getActivity ()));

        list = new ArrayList<> ();
        adapter = new ExerciseAdapter ( getActivity (), list);
        recyclerView.setAdapter(adapter);

        GetEData ();
    }
    private void GetEData() {
        progressBar.setVisibility (View.VISIBLE);
        firestore.collection ("sports_exercise")
               // .whereEqualTo ("sport_id", SportAdapter.UID)
                .addSnapshotListener ((value, error) -> {

                    if (error == null) {

                        if (value == null) {
                            progressBar.setVisibility (View.GONE);

                            Toast.makeText (getActivity (), "value is null", Toast.LENGTH_SHORT).show ();
                        } else {
                            for (DocumentChange documentChange : value.getDocumentChanges ()) {
                                if (!isFirstOpen)
                                    list.clear ();

                                ExerciseModel model = documentChange.getDocument ().toObject (ExerciseModel.class);
                                list.add (model);

                                adapter.notifyDataSetChanged ();


                            }

                            progressBar.setVisibility (View.GONE);
                        }


                    } else {
                        progressBar.setVisibility (View.GONE);

                        Toast.makeText (getActivity (), error.getMessage (), Toast.LENGTH_SHORT).show ();
                    }


                });
    }
}