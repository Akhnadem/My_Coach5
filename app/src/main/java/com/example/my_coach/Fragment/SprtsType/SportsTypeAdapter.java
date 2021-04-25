package com.example.my_coach.Fragment.SprtsType;

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
import com.example.my_coach.Model.CategoriesModel;
import com.example.my_coach.R;
import com.example.my_coach.ui.SportsActivity;

import java.util.List;

public class SportsTypeAdapter extends RecyclerView.Adapter<SportsTypeAdapter.ViewHolder> {
    public Context context;
    private List<CategoriesModel> list;

    public SportsTypeAdapter(Context context, List<CategoriesModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.formate_sprts_type, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.setSportsName(list.get(position).getName());
        holder.setSportsImage(list.get(position).getImage());
        holder.layout.setOnClickListener(v -> {
            Intent intent = new Intent(context, SportsActivity.class);
            intent.putExtra("category_id", list.get(position).getID());
            context.startActivity(intent);

        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView SportsName;
        ImageView SportsImage;
        LinearLayout layout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            SportsName = itemView.findViewById(R.id.Sports_name);
            SportsImage = itemView.findViewById(R.id.Sports_image);
            layout = itemView.findViewById(R.id.container);
        }

        void setSportsName(String name) {
            SportsName.setText(name);
        }

        void setSportsImage(String url) {
            Glide.with(context)
                    .load(url)
                    .placeholder(R.drawable.graduation_project_logo)
                    .into(SportsImage);
        }
    }
}
