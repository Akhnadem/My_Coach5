package com.example.my_coach.sport.SportsProperties;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.example.my_coach.Model.AbouteSportModel;
import com.example.my_coach.R;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.FirebaseFirestore;


public class AboutSportFragment extends Fragment {
    private ImageView imageView;
    private TextView textView;

    private FirebaseFirestore firestore;




    public AboutSportFragment() {
        // Required empty public constructor
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_about_sport, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        imageView=view.findViewById(R.id.SportBrief_Image);
        textView=view.findViewById(R.id.SportBrief_Brief);

        firestore=FirebaseFirestore.getInstance();




        getSportsData();

    }

    private void getSportsData() {

        firestore.collection("aboute_sport")
                .whereEqualTo("sport_id",SportsSpecificationsActivity.UID)
                .addSnapshotListener((value, error) -> {

                    if (error==null){

                        if (value==null){

                            Toast.makeText(getActivity(), "value is null", Toast.LENGTH_SHORT).show();
                        }else {


                            for (DocumentChange documentChange:value.getDocumentChanges()){
                                AbouteSportModel model=documentChange.getDocument().toObject(AbouteSportModel.class);
                                if (!model.getSport_brief ().isEmpty ()){
                                    textView.setText (model.getSport_brief ());
                                }

                              if (!model.getSport_image ().isEmpty ()){
                                  Glide.with(getActivity ())
                                          .load(model.getSport_image ())
                                          .placeholder(R.drawable.graduation_project_logo)
                                          .into(imageView);
                              }
                            }
                   //         progressBar.setVisibility(View.GONE);
                        }


                    }else {
                    //    progressBar.setVisibility(View.GONE);
                        Toast.makeText(getActivity(), error.getMessage(), Toast.LENGTH_SHORT).show();
                    }


                });
    }


}