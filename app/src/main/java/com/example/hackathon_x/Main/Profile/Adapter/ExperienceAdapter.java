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

public class ExperienceAdapter extends RecyclerView.Adapter<ExperienceAdapter.LangViewHolder> {

    List<String> expImage;
    List<String> expPosition;
    List<String> expAssociated;
    List<String> expType;
    List<String> expFrom;
    List<String> expTo;
    List<String> expRange;
    private Context context;

    public ExperienceAdapter(
            Context context,
            List<String> expImage,
            List<String> expPosition,
            List<String> expAssociated,
            List<String> expType,
            List<String> expFrom,
            List<String> expTo,
            List<String> expRange
    ) {
        this.context = context;
        this.expImage = expImage;
        this.expPosition = expPosition;
        this.expAssociated = expAssociated;
        this.expType = expType;
        this.expFrom = expFrom;
        this.expTo = expTo;
        this.expRange = expRange;
    }

    @NonNull
    @Override
    public ExperienceAdapter.LangViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.experience_item, parent, false);
        return new ExperienceAdapter.LangViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ExperienceAdapter.LangViewHolder holder, int position) {

        if (expImage.get(position) != null) {
            Glide.with(context)
                    .load(expImage.get(position)) // Replace with your drawable resource
                    .into(holder.companyImage);
        }
        holder.jobName.setText(expPosition.get(position));
        holder.companyName.setText(expAssociated.get(position));
        holder.jobtype.setText(expType.get(position));

        String oneline =
                expFrom.get(position)
                + " · " + expTo.get(position)
                + " · " + expRange.get(position);
        holder.fromDate.setText(oneline);

    }

    @Override
    public int getItemCount() {
        return expPosition.size();
    }

    public static class LangViewHolder extends RecyclerView.ViewHolder {
        MaterialCardView jobcard;
        ShapeableImageView companyImage;
        TextView jobName, companyName, jobtype, fromDate;


        public LangViewHolder(@NonNull View itemView) {
            super(itemView);
            jobcard = itemView.findViewById(R.id.jobcard);
            companyImage = itemView.findViewById(R.id.companyImage);
            jobName = itemView.findViewById(R.id.jobName);
            companyName = itemView.findViewById(R.id.companyName);
            jobtype = itemView.findViewById(R.id.jobtype);
            fromDate = itemView.findViewById(R.id.fromDate);
        }
    }
}



