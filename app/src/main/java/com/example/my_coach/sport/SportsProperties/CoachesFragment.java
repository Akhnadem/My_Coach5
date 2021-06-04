 package com.example.my_coach.sport.SportsProperties;

 import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.my_coach.Model.CoachesModel;
import com.example.my_coach.R;
import com.example.my_coach.adapter.CoachesAdapter;
import com.example.my_coach.adapter.SportAdapter;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;


public class CoachesFragment extends Fragment {

    private ProgressBar progressBar;
    private RecyclerView recyclerView;
    private FirebaseFirestore firestore;
    private List<CoachesModel> list;
    private CoachesAdapter adapter;

    private Boolean isFirstOpen = true;
    private EditText SearchBox;



    public CoachesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate (R.layout.fragment_coaches, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated (view, savedInstanceState);
        progressBar = view.findViewById (R.id.progres_Coaches);
        recyclerView = view.findViewById (R.id.recycler_Coaches);
        firestore = FirebaseFirestore.getInstance ();
        SearchBox=view.findViewById (R.id.search_Coach);

        recyclerView.setHasFixedSize (true);
        recyclerView.setLayoutManager (new LinearLayoutManager (getActivity ()));

        list = new ArrayList<> ();
        adapter = new CoachesAdapter (getActivity (), list);
        recyclerView.setAdapter (adapter);
        GetCoachesData ();
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
    }
    private void filter(String text) {
        ArrayList<CoachesModel> filterdList=new ArrayList<> ();
        for (CoachesModel coachesModel :list){
            if (coachesModel.getCoach_name ().toLowerCase ().contains (text.toLowerCase ())){
                filterdList.add(coachesModel);
            }
        }
        adapter.filterlist(filterdList);
    }

    private void GetCoachesData() {
        progressBar.setVisibility (View.VISIBLE);
        firestore.collection ("coach_sport")
                .whereEqualTo ("sport_id", SportAdapter.UID)
                .addSnapshotListener ((value, error) -> {

                    if (error == null) {

                        if (value == null) {
                            progressBar.setVisibility (View.GONE);
                            Toast.makeText (getActivity (), "value is null", Toast.LENGTH_SHORT).show ();
                        } else {
                            for (DocumentChange documentChange : value.getDocumentChanges ()) {
                                if (!isFirstOpen)
                                    list.clear ();

                                CoachesModel model = documentChange.getDocument ().toObject (CoachesModel.class);
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