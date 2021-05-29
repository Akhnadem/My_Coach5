package com.example.my_coach.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.my_coach.Model.ExerciseModel;
import com.example.my_coach.R;

import java.util.List;

public class ExerciseAdapter extends RecyclerView.Adapter<ExerciseAdapter.ViewHolder> {

    private Context context;
    private List<ExerciseModel> list;

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
        ExerciseModel model = list.get (position);
        Toast.makeText (context, model.getName(), Toast.LENGTH_SHORT).show ();
        holder.setExerciseName (model.getName ());
        holder.setExerciseImage (model.getImage ());


    }

    @Override
    public int getItemCount() {
        return list.size ();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView ExerciseName;
        ImageView ExerciseImage;

        public ViewHolder(@NonNull View itemView) {
            super (itemView);
            ExerciseName = itemView.findViewById (R.id.ESports_name);
            ExerciseImage = itemView.findViewById (R.id.ExerciseImage);

        }

        void setExerciseName(String name) {
            ExerciseName.setText (name);
        }

        void setExerciseImage(String url) {
            if (!url.equals ("nul")) {
                Glide.with (context)
                        .load (url)
                        .placeholder (R.drawable.graduation_project_logo)
                        .into (ExerciseImage);
            }
        }
    }
}
