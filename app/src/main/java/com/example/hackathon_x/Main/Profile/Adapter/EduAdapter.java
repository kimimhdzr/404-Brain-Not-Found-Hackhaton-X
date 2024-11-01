package com.example.hackathon_x.Main.Profile.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.hackathon_x.R;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.imageview.ShapeableImageView;

import java.util.List;

public class EduAdapter extends RecyclerView.Adapter<EduAdapter.LangViewHolder> {

    List<String> eduImage;
    List<String> eduName;
    List<String> eduLevel;
    List<String> eduType;
    List<String> eduFrom;
    List<String> eduTo;
    List<String> eduRange;
    private Context context;

    public EduAdapter(
            Context context,
            List<String> eduImage,
            List<String> eduName,
            List<String> eduLevel,
            List<String> eduType,
            List<String> eduFrom,
            List<String> eduTo,
            List<String> eduRange
    ) {
        this.context = context;
        this.eduImage = eduImage;
        this.eduName = eduName;
        this.eduLevel = eduLevel;
        this.eduType = eduType;
        this.eduFrom = eduFrom;
        this.eduTo = eduTo;
        this.eduRange = eduRange;
    }

    @NonNull
    @Override
    public EduAdapter.LangViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.education_item, parent, false);
        return new EduAdapter.LangViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EduAdapter.LangViewHolder holder, int position) {

        if (eduImage.get(position) != null) {
            Glide.with(context)
                    .load(eduImage.get(position)) // Replace with your drawable resource
                    .into(holder.placeImage);
        }
        holder.placeName.setText(eduName.get(position));
        holder.levelName.setText(eduLevel.get(position));
        holder.eduType.setText(eduType.get(position));


        String oneline =
                eduFrom.get(position)
                        + " · " + eduTo.get(position)
                        + " · " + eduRange.get(position);
        holder.fromDate.setText(oneline);

    }

    @Override
    public int getItemCount() {
        return eduName.size();
    }

    public static class LangViewHolder extends RecyclerView.ViewHolder {
        MaterialCardView educard;
        ShapeableImageView placeImage;
        TextView placeName, levelName, eduType, fromDate, toDate, totalTime;


        public LangViewHolder(@NonNull View itemView) {
            super(itemView);
            educard = itemView.findViewById(R.id.awardcard);
            placeImage = itemView.findViewById(R.id.placeImage);
            placeName = itemView.findViewById(R.id.placeName);
            levelName = itemView.findViewById(R.id.levelName);
            eduType = itemView.findViewById(R.id.eduType);
            fromDate = itemView.findViewById(R.id.fromDate);
        }
    }
}


