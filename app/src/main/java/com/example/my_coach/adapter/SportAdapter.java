package com.example.my_coach.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.my_coach.Model.SportModel;
import com.example.my_coach.R;

import java.util.List;

public class SportAdapter extends RecyclerView.Adapter<SportAdapter.ViewHolder> {

    private Context context;
    private List<SportModel> list;

    public SportAdapter(Context context, List<SportModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public SportAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SportAdapter.ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.formate_sprts_type, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull SportAdapter.ViewHolder holder, int position) {
        SportModel model = list.get(position);

holder.SportsName.setText(model.getName());

        Glide.with(context)
                .load(model.getImage())
                .placeholder(R.drawable.graduation_project_logo)
                .into(holder.SportsImage);


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView SportsName;
        ImageView SportsImage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            SportsName = itemView.findViewById(R.id.Sports_name);
            SportsImage = itemView.findViewById(R.id.Sports_image);
        }
    }
}
