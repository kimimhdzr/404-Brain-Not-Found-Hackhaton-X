package com.example.hackathon_x.Main.Home.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.hackathon_x.DataBinding.AdapterModel.MentorItem2Model;
import com.example.hackathon_x.R;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.chip.ChipGroup;
import com.google.android.material.imageview.ShapeableImageView;

import java.util.ArrayList;
import java.util.List;

public class RecommendedMentorshipAdapter extends RecyclerView.Adapter<RecommendedMentorshipAdapter.MentorViewHolder> {
    private List<MentorItem2Model> mentorList;
    private Context context;

    public RecommendedMentorshipAdapter(Context context, List<MentorItem2Model> mentorList) {
        this.context = context;
        this.mentorList = mentorList;
    }

    @NonNull
    @Override
    public MentorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.mentor_item_2, parent, false);
        return new MentorViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MentorViewHolder holder, int position) {
        MentorItem2Model mentor = mentorList.get(position);

        holder.mentorname.setText(mentor.getMentorName());
        holder.jobposition.setText(mentor.getJobName());
        holder.jobloc.setText(mentor.getLocation());
        holder.jobdesc.setText(mentor.getMentorBio());

        holder.mentorstatus.setText(mentor.getMentorStatus());

        ArrayList<String> jobIndustry = mentor.getJobIndustry();
        ArrayList<String> jobSkills = mentor.getJobSkills();

//        holder.mentorIndustry.setText(mentor.getCompanyName());
//        holder.mentorSkills.setText(mentor.getCompanyName());



        if (mentor.getProfileImage() != null) {
            Glide.with(context)
                    .load(mentor.getProfileImage()) // Replace with your drawable resource
                    .into(holder.profile_image);
        }

        holder.mentorcard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavController navController = Navigation.findNavController(v);
                navController.navigate(R.id.nav_to_mentorprofile);

            }
        });
    }

    @Override
    public int getItemCount() {
        return mentorList.size();
    }

    public static class MentorViewHolder extends RecyclerView.ViewHolder {
        TextView mentorname, jobposition, jobloc, jobdesc, mentorstatus;
        ChipGroup mentorIndustry, mentorSkills;
        ShapeableImageView profile_image;
        MaterialCardView mentorcard;

        public MentorViewHolder(@NonNull View itemView) {
            super(itemView);
            mentorname = itemView.findViewById(R.id.mentorname);
            jobposition = itemView.findViewById(R.id.jobposition);
            jobloc = itemView.findViewById(R.id.jobloc);
            jobdesc = itemView.findViewById(R.id.jobdesc);
            mentorstatus = itemView.findViewById(R.id.mentorstatus);

            mentorIndustry = itemView.findViewById(R.id.chipGroup3);
            mentorSkills = itemView.findViewById(R.id.chipGroup1);

            profile_image = itemView.findViewById(R.id.profile_image);
            mentorcard = itemView.findViewById(R.id.mentorcard);
        }
    }
}
