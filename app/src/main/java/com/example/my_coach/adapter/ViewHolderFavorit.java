package com.example.my_coach.adapter;

import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.my_coach.Model.SportModel;
import com.example.my_coach.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class ViewHolderFavorit extends RecyclerView.ViewHolder {
   public ImageView imageView;
   public TextView textView;
  public   ImageButton fav_spor;
  public   DatabaseReference favoriteref;
   public FirebaseDatabase database= FirebaseDatabase.getInstance ();
    public ViewHolderFavorit(@NonNull View itemView) {

        super (itemView);
    }
    public void setitem(FirebaseRecyclerAdapter<SportModel, ViewHolderFavorit> activity, String id, String category_id, String name, String image){

        textView = itemView.findViewById(R.id.categories_Sports_name);
        imageView = itemView.findViewById(R.id.categories_Sports_image);

        Picasso.get ().load (image).into (imageView);
        textView.setText (name);



    }

    public void favouriteChecer(String postkey) {
        fav_spor=itemView.findViewById (R.id.Favourite_spo);

        favoriteref=database.getReference ("favourits");
        FirebaseUser user= FirebaseAuth.getInstance ().getCurrentUser ();
        String uid=user.getUid ();

        favoriteref.addValueEventListener (new ValueEventListener () {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.child (postkey).hasChild (uid)){
                    fav_spor.setImageResource (R.drawable.ic_fill_favorite_24);
                }else {
                    fav_spor.setImageResource (R.drawable.ic_favorite);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }


}
