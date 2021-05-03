package com.example.my_coach;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.my_coach.ui.Splash;

import java.util.Locale;

import de.hdodenhof.circleimageview.CircleImageView;

public class Sitting_Activity extends AppCompatActivity {

    //view
    private CircleImageView UserImage;
    private TextView UserName;
    private TextView UserEmail;
    private TextView UserPhone;
    private TextView selectedLang;
    private String lang;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sitting_);

        //find view
        UserImage=findViewById(R.id.User_image_Sitting);
        UserName=findViewById(R.id.User_Name_Siting);
        UserEmail=findViewById(R.id.User_Email_Sitting);
        UserPhone=findViewById(R.id.User_Phone_Sitting);
        selectedLang=findViewById(R.id.selectedLang);
        String CurrentLang = Locale.getDefault().getDisplayLanguage();
         lang = getSharedPreferences("language",MODE_PRIVATE)
                .getString("lang",CurrentLang);

        if (lang.equals("ar"))
        selectedLang.setText("عربي");
        else
            selectedLang.setText("English");

        //intent to get back to profile

        findViewById(R.id.back_Sitting).setOnClickListener(v -> finish());

        GetIntentData();
        findViewById(R.id.btn_Language).setOnClickListener(v -> {

            showAlertLanguage();

        });
    }
    private void showAlertLanguage() {
        AlertDialog alert=new AlertDialog.Builder(this).create();
        alert.setCancelable(false);
        View view = LayoutInflater.from(this).inflate(R.layout.layout_lanuage,null);
        alert.setView(view);
         ImageView  cancel_lang=view.findViewById(R.id.exit_lang);
        cancel_lang.setOnClickListener(v -> {
            alert.dismiss();
        });
        RadioButton radio_ar= view.findViewById(R.id.rb_arabic);
        RadioButton radio_en= view.findViewById(R.id.rb_english);

        if (lang.equals("ar")) {
            radio_ar.setChecked(true);
            radio_en.setChecked(false);

        } else{
            radio_en.setChecked(false);
            radio_en.setChecked(true);
            }
        view.findViewById(R.id.save_lang).setOnClickListener(v -> {
            if (radio_en.isChecked())
                saveLanguage("en");
            else if (radio_ar.isChecked())
                saveLanguage("ar");
        });

        alert.show();
    }
    void saveLanguage(String lang){

        getSharedPreferences("language",MODE_PRIVATE)
                .edit()
                .putString("lang",lang)
                .apply();
        //language
        Locale l =new Locale(lang);
        Locale.setDefault(l);
        Configuration configuration=new Configuration();
        configuration.locale=l;
        getResources().updateConfiguration(configuration,getResources().getDisplayMetrics());

        Intent intent = new Intent(Sitting_Activity.this, Splash.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    private void GetIntentData(){

        Intent intent= getIntent();

        String name=intent.getStringExtra("name");
        String image=intent.getStringExtra("image");
        String email=intent.getStringExtra("email");
        String phone=intent.getStringExtra("phone");

        assert image != null;
        SetuserData(name,image,email,phone);
    }

    private void SetuserData(String name, String image, String email, String phone) {

        UserName.setText(name);
        UserEmail.setText(email);
        UserPhone.setText(phone);
        if (!image.equals("null")) {

            Glide.with(this)
                    .load(image)
                    .placeholder(R.drawable.graduation_project_logo)
                    .into(UserImage);


        }

    }


}