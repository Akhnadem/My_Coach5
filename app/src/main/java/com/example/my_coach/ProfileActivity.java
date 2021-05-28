package com.example.my_coach;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import de.hdodenhof.circleimageview.CircleImageView;

public class ProfileActivity extends AppCompatActivity {
    //view
    private CircleImageView CoachImage;
    private TextView CoachName;
    private TextView CoachEmail;
    private TextView CoachPhone;
    private ProgressBar progressBar;
    private FirebaseFirestore firestore;
    private String NameDB,ImageDB,EmaileDB,PhoneDB;

    private String GetCoach_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_profile);
        //find view
        CoachImage=findViewById(R.id.profile_image_A);
        CoachName=findViewById(R.id.User_Name_Profil);
        CoachEmail=findViewById(R.id.User_Email_Profil_A);
        CoachPhone=findViewById(R.id.User_Phone_Profil_A);
        progressBar = findViewById(R.id.ProgressBar_Profil_A);

        //firestor
        firestore = FirebaseFirestore.getInstance();


        GetCoach_id=getIntent ().getStringExtra ("coach_id");

        //intent to get back

        findViewById(R.id.back_SProfil_A).setOnClickListener(v -> finish());

        GetCoachData();
    }

    private void GetCoachData() {



            progressBar.setVisibility(View.VISIBLE);

            DocumentReference DB = firestore.collection("users")
                    .document(GetCoach_id);
            DB.get().addOnCompleteListener(task -> {

                if (task.isSuccessful()) {
                    DocumentSnapshot snapshot = task.getResult();
                    assert snapshot != null;
                    NameDB = snapshot.getString("name");
                    ImageDB = snapshot.getString("image");
                    EmaileDB = snapshot.getString("email");
                    PhoneDB = snapshot.getString("phone");

                    CoachName.setText(  NameDB);
                    CoachEmail.setText(  EmaileDB);
                    CoachPhone.setText(  PhoneDB);


                    if (ImageDB!=null) {

                        Glide.with(this)
                                .load(ImageDB)
                                .placeholder(R.drawable.graduation_project_logo)
                                .into(CoachImage);


                    }

                    progressBar.setVisibility(View.GONE);

                } else {
                    progressBar.setVisibility(View.GONE);
                    Toast.makeText(this, "Error in Task" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }

            });



    }
}