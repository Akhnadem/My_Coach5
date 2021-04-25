package com.example.my_coach.Fragment.SprtsType;

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

import com.example.my_coach.Model.CategoriesModel;
import com.example.my_coach.R;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import java.util.ArrayList;
import java.util.List;


public class HomeFrag extends Fragment {

    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    private SportsTypeAdapter adapter;
    private List<CategoriesModel> list;

    private FirebaseFirestore firestore;

    public HomeFrag() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView=view.findViewById(R.id.recycler_sports);
        progressBar=view.findViewById(R.id.progres_sports);

        firestore=FirebaseFirestore.getInstance();

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        list=new ArrayList<>();
        adapter=new SportsTypeAdapter(getActivity(),list);
        recyclerView.setAdapter(adapter);


        getSportsData();
    }

    private void getSportsData(){

        progressBar.setVisibility(View.VISIBLE);
        firestore.collection("Sports")
                .orderBy("Time",Query.Direction.ASCENDING)
                .addSnapshotListener((value, error) -> {

                    if (error==null){

                        if (value==null){
                            progressBar.setVisibility(View.GONE);
                            Toast.makeText(getActivity(), "value is null", Toast.LENGTH_SHORT).show();
                        }else {

                            for (DocumentChange documentChange:value.getDocumentChanges()){

                                CategoriesModel model=documentChange.getDocument().toObject(CategoriesModel.class);
                                list.add(model);
                                adapter.notifyDataSetChanged();
                            }
                            progressBar.setVisibility(View.GONE);
                        }


                    }else {
                        progressBar.setVisibility(View.GONE);
                        Toast.makeText(getActivity(), error.getMessage(), Toast.LENGTH_SHORT).show();
                    }


                });
    }


}