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
import com.example.my_coach.Model.ExerciseModel;

import com.example.my_coach.R;
import com.example.my_coach.sport.SportsProperties.SportsSpecificationsActivity;

import java.util.List;

public class ExerciseAdapter extends RecyclerView.Adapter<ExerciseAdapter.ViewHolder> {

    private Context context;
    private List<ExerciseModel> list;
    public static String UID;
    public ExerciseAdapter(Context context, List<ExerciseModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ExerciseAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ExerciseAdapter.ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.formate_exercise, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ExerciseAdapter.ViewHolder holder, int position) {
        ExerciseModel model = list.get(position);

        holder.setExerciseName (list.get(position).getExercise_name ());
        holder.setExercisesImage (list.get(position). getImage ());

        holder.layout.setOnClickListener(v -> {
            UID = model.getId();
            Intent intent = new Intent(context, SportsSpecificationsActivity.class);
            intent.putExtra("id",model.getId());
            context.startActivity(intent);

        });


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView ExerciseName;
        ImageView ExercisesImage;
        LinearLayout layout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            layout = itemView.findViewById(R.id.btn_sports);
            ExerciseName = itemView.findViewById(R.id.categories_Sports_name);
            ExercisesImage = itemView.findViewById(R.id.categories_Sports_image);
        }
        void setExerciseName(String name) {
            ExerciseName.setText(name);
        }

        void setExercisesImage(String url) {
            Glide.with(context)
                    .load(url)
                    .placeholder(R.drawable.graduation_project_logo)
                    .into(ExercisesImage);
        }
    }
}
