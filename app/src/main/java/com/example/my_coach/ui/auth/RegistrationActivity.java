package com.example.my_coach.ui.auth;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;

import androidx.appcompat.app.AppCompatActivity;

import com.example.my_coach.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class RegistrationActivity extends AppCompatActivity {

    //view
    private EditText tedName, tedPhone, tedEmail, tedPassword;
    private ProgressBar tprogressBar;
    private RadioButton tedCoch,tedTraine;
    boolean isCoach;
    private DatePickerDialog datePickerDialog;
    private Button dateButton;

    //firebase
    private FirebaseAuth tfirebaseAuth;
    private FirebaseFirestore firestore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regestration);


        //findview
        tedName = findViewById(R.id.rt_name);
        tedPhone = findViewById(R.id.rt_phone);
        tedEmail = findViewById(R.id.rt_email);
        tedPassword =  findViewById(R.id.rt_pass);
        dateButton = findViewById(R.id.rt_dat);
        tprogressBar=findViewById(R.id.tr_progres);
        tedCoch=findViewById(R.id.rb_Coach);
        tedTraine=findViewById(R.id.rb_Trainee);
        dateButton.setText (getTodaysDate());
        iniDatePikker();

        //firebase
        tfirebaseAuth=FirebaseAuth.getInstance();
        firestore=FirebaseFirestore.getInstance();

        //on click to regest traine
        findViewById(R.id.rt_regest).setOnClickListener(v -> {


            validationdata();

        });
        //on click to finesh this Activity
        findViewById(R.id.rt_back).setOnClickListener(v -> {
            startActivity(new Intent(RegistrationActivity.this, LoginActivity.class));

        });
    }
    private String getTodaysDate() {
        Calendar cal=Calendar.getInstance ();
        int year=cal.get (Calendar.YEAR);
        int month=cal.get (Calendar.MONTH);
        month=month +1;
        int day=cal.get (Calendar.DAY_OF_MONTH);
        return MakeDateString (day,month,year);
    }
    private void iniDatePikker() {
        DatePickerDialog.OnDateSetListener dateSetListener=new DatePickerDialog.OnDateSetListener () {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {

                month=month +1;
                String dat= MakeDateString (day,month,year);
                dateButton.setText (dat);

            }
        };
        Calendar cal=Calendar.getInstance ();
        int year=cal.get (Calendar.YEAR);
        int month=cal.get (Calendar.MONTH);
        int day=cal.get (Calendar.DAY_OF_MONTH);

        int style= AlertDialog.THEME_HOLO_LIGHT;

        datePickerDialog=new DatePickerDialog (this,style,dateSetListener,year,month,day);
        datePickerDialog.getDatePicker ().setMaxDate (System.currentTimeMillis ());
    }
    private String MakeDateString(int day, int month, int year) {
        return getMonthformate (month)+" "+day+" "+year;

    }

    private String getMonthformate(int month) {
        if (month==1)
            return "JAN";
        if (month==2)
            return "FEB";
        if (month==3)
            return "MAR";
        if (month==4)
            return "APR";
        if (month==5)
            return "MAY";
        if (month==6)
            return "JuN";
        if (month==7)
            return "Jul";
        if (month==8)
            return "AUG";
        if (month==9)
            return "SEP";
        if (month==10)
            return "OCT";
        if (month==11)
            return "NOV";
        if (month==12)
            return "DEC";
        //defult
        return "JAN";
    }

    public void openDatePiker(View view) {
        datePickerDialog.show ();
    }
    private void validationdata() {
        String tname = tedName.getText().toString().trim();
        String tphone = tedPhone.getText().toString().trim();
        String temail = tedEmail.getText().toString().trim();
        String tpass = tedPassword.getText().toString().trim();
        String tdat = dateButton.getText().toString().trim();




        if (tname.isEmpty()) {
            tedName.requestFocus();
            showAlert("Name is required");
            return;
        }
        if (tphone.isEmpty()) {
            tedPhone.requestFocus();
            showAlert("Phone is required");
            return;
        }
        if (tphone.length() < 11) {
            tedPhone.requestFocus();
            showAlert("Invalid Phone Number");
            return;
        }
        if (temail.isEmpty()) {
            tedEmail.requestFocus();
            showAlert("Email is required");
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(temail).matches()) {
            tedEmail.requestFocus();
            showAlert("Invalid Email address\n Email must be yourName@company.com");
            return;
        }
        if (tpass.isEmpty()) {
            tedPassword.requestFocus();
            showAlert("Password is required");
            return;
        }
        if (tpass.length() < 6) {
            tedPassword.requestFocus();
            showAlert("Password should have 6 digit");
            return;
        }

        if (tdat.isEmpty()) {
            dateButton.requestFocus();
            showAlert("Date_of_birth is required");
            return;
        }
        signupWithFirebase(temail,tpass);


    }

    private void signupWithFirebase(String temail, String tpass) {
        tprogressBar.setVisibility(View.VISIBLE);
        tfirebaseAuth.createUserWithEmailAndPassword(temail,tpass)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()){
                        saveUserData();

                    }else {
                        tprogressBar.setVisibility(View.GONE);
                        showAlert(task.getException().getMessage());
                    }


                });
    }

    private void saveUserData() {
        if (tedCoch.isChecked()){
            isCoach=true;
        }
        if (tedTraine.isChecked()){
            isCoach=false;

        }

        if (tfirebaseAuth.getCurrentUser() != null){

            String userID=tfirebaseAuth.getCurrentUser().getUid();

            Map<String, Object> user = new HashMap<>();
            user.put("id",userID);
            user.put("name",tedName.getText().toString().trim());
            user.put("phone",tedPhone.getText().toString().trim());
            user.put("email",tedEmail.getText().toString().trim());
            user.put("coach",isCoach);
            user.put("password",tedPassword.getText().toString().trim());
            user.put("Date",dateButton.getText().toString().trim());
            user.put("image","null");

            firestore.collection("users")
                    .document(userID)
                    .set(user)
                    .addOnCompleteListener(task -> {

                        if (task.isSuccessful()){
                            tprogressBar.setVisibility(View.GONE);
                            new AlertDialog.Builder(RegistrationActivity.this)
                                    .setTitle("Congratulations")
                                    .setMessage("Account Created Successfuly")
                                    .setCancelable(false)
                                    .setPositiveButton("ok", (dialog, which) -> {

                                        startActivity(new Intent(RegistrationActivity.this, LoginActivity.class));

                                    })
                                    .create().show();

                        }else {
                            tprogressBar.setVisibility(View.GONE);
                            showAlert("Error \n"+ task.getException().getMessage());
                        }
                    });

        }



    }

    void showAlert(String msg ){
        new AlertDialog.Builder(RegistrationActivity.this)
                .setTitle("Error")
                .setMessage(msg).
                setPositiveButton("ok",null)
                .create().show();
    }
}
