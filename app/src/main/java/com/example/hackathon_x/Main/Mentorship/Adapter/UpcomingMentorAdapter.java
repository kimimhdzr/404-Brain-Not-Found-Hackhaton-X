package com.example.hackathon_x.Main.Mentorship.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.hackathon_x.DataBinding.AdapterModel.MentorItem1Model;
import com.example.hackathon_x.DataBinding.AdapterModel.MentorItem2Model;
import com.example.hackathon_x.R;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import com.google.android.material.imageview.ShapeableImageView;

import java.util.ArrayList;
import java.util.List;

public class UpcomingMentorAdapter extends RecyclerView.Adapter<UpcomingMentorAdapter.MentorViewHolder> {
    private List<MentorItem1Model> mentorList;
    private Context context;

    public UpcomingMentorAdapter(Context context, List<MentorItem1Model> mentorList) {
        this.context = context;
        this.mentorList = mentorList;
    }

    @NonNull
    @Override
    public UpcomingMentorAdapter.MentorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.mentor_item_1, parent, false);
        return new UpcomingMentorAdapter.MentorViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UpcomingMentorAdapter.MentorViewHolder holder, int position) {
        MentorItem1Model mentor = mentorList.get(position);

        holder.mentorname.setText(mentor.getMentorName());
        holder.jobname.setText(mentor.getJobName());

        String mentorstatus = mentor.getMentorstatus();
        if (mentorstatus.equals("Ongoing")){
            holder.mentorstatus.setBackgroundColor(com.google.android.material.R.attr.colorTertiary);
        } else { //finish
            holder.mentorstatus.setBackgroundColor(com.google.android.material.R.attr.colorSecondary);
        }

        holder.date.setText(mentor.getDate());

        holder.time.setText(mentor.getTime());


        if (mentor.getProfileImage() != null) {
            Glide.with(context)
                    .load(mentor.getProfileImage()) // Replace with your drawable resource
                    .into(holder.profile_image);
        }
    }

    @Override
    public int getItemCount() {
        return mentorList.size();
    }

    public static class MentorViewHolder extends RecyclerView.ViewHolder {
        TextView mentorname, jobname, mentorstatus;
        ChipGroup chipGroup;
        Chip date, time;
        ShapeableImageView profile_image;
        MaterialCardView mentorcard;

        public MentorViewHolder(@NonNull View itemView) {
            super(itemView);
            mentorname = itemView.findViewById(R.id.mentorname);
            jobname = itemView.findViewById(R.id.jobname);

            mentorstatus = itemView.findViewById(R.id.mentorstatus);
            profile_image = itemView.findViewById(R.id.profile_image);

            chipGroup = itemView.findViewById(R.id.chipGroup);
            date = itemView.findViewById(R.id.date);
            time = itemView.findViewById(R.id.time);

            mentorcard = itemView.findViewById(R.id.mentorcard);
        }
    }
}
