package com.example.my_coach.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.my_coach.Model.CoachesModel;
import com.example.my_coach.ProfileActivity;
import com.example.my_coach.R;

import java.util.List;

public class CoachesAdapter extends RecyclerView.Adapter<CoachesAdapter.ViewHolder> {

    private Context context;
    private List<CoachesModel> list;

    public CoachesAdapter(Context context, List<CoachesModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public CoachesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CoachesAdapter.ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.formate_coaches, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull CoachesAdapter.ViewHolder holder, int position) {
        CoachesModel model = list.get(position);

        holder.setCoachName (model.getCoach_name ());
        holder.setSportsName(model.getSport_name ());
        holder.setTrainningPrice (model.getSport_price ());
        holder.setCoachImage (model.getCoach_image ());

        holder.layout.setOnClickListener(v -> {
            Intent intent = new Intent(context, ProfileActivity.class);
            intent.putExtra("coach_id",model.getCoach_id ());
            context.startActivity(intent);

        });


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView CoachName;
        TextView SportsName;
        TextView TrainningPrice;
        ImageView CoachImage;
        LinearLayout layout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            layout = itemView.findViewById(R.id.btn_coach);
            CoachName = itemView.findViewById(R.id.User_Name_coach);
            SportsName = itemView.findViewById(R.id.SportName_coach);
            TrainningPrice = itemView.findViewById(R.id.price);
            CoachImage = itemView.findViewById(R.id.categories_Sports_image);
        }
        void setCoachName(String name_coach) {
            CoachName .setText(name_coach);
        }
        void setSportsName(String name_sport) {
            SportsName.setText(name_sport);
        }
        void setTrainningPrice(int number) {
            TrainningPrice.setText(String.valueOf (number));
        }

        void setCoachImage(String url) {
            if (!url.isEmpty ()){
                Glide.with(context)
                        .load(url)
                        .placeholder(R.drawable.graduation_project_logo)
                        .into(CoachImage);
            }

        }
    }
}
