package com.example.hackathon_x.Main.Home.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.hackathon_x.DataBinding.AdapterModel.JobItem1Model;
import com.example.hackathon_x.DataBinding.AdapterModel.JobItem2Model;
import com.example.hackathon_x.R;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import com.google.android.material.imageview.ShapeableImageView;

import java.util.ArrayList;
import java.util.List;

public class JobRecommendAdapter extends RecyclerView.Adapter<JobRecommendAdapter.JobViewHolder> {
    private List<JobItem2Model> jobList;
    private Context context;

    public JobRecommendAdapter(Context context, List<JobItem2Model> jobList) {
        this.context = context;
        this.jobList = jobList;
    }

    @NonNull
    @Override
    public JobViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.job_item_2, parent, false);
        return new JobViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull JobViewHolder holder, int position) {
        JobItem2Model job = jobList.get(position);

        holder.jobName.setText(job.getJobTitle());
        holder.companyName.setText(job.getCompanyName());
        holder.posteddate.setText(job.getPostedDate());
        holder.jobloc.setText(job.getCompanyLocation());
        holder.jobdesc.setText(job.getJobDesc());
        holder.chip_jobsalary.setText(job.getJobSalary());
        holder.chip_jobtype.setText(job.getJobType());

        ArrayList<String> jobSkillsRequired = job.getJobSkillsRequired();


        if (job.getCompanyLogo() != null) {
            Glide.with(context)
                    .load(job.getCompanyLogo()) // Replace with your drawable resource
                    .into(holder.companyImage);
        }
    }

    @Override
    public int getItemCount() {
        return jobList.size();
    }

    public static class JobViewHolder extends RecyclerView.ViewHolder {
        TextView jobName, companyName, posteddate, jobloc, jobdesc;
        Chip chip_jobtype, chip_jobsalary;
        ChipGroup requiredSkills;
        ShapeableImageView companyImage;
        MaterialCardView jobCard;

        public JobViewHolder(@NonNull View itemView) {
            super(itemView);
            jobName = itemView.findViewById(R.id.jobname);
            companyName = itemView.findViewById(R.id.companyname);
            posteddate = itemView.findViewById(R.id.posteddate);
            jobloc = itemView.findViewById(R.id.jobloc);
            jobdesc = itemView.findViewById(R.id.jobdesc);
            chip_jobtype = itemView.findViewById(R.id.chip_jobtype);
            chip_jobsalary = itemView.findViewById(R.id.chip_jobsalary);
            companyImage = itemView.findViewById(R.id.profile_image);

            requiredSkills = itemView.findViewById(R.id.chipGroup);

            jobCard = itemView.findViewById(R.id.jobcard);
        }
    }
}
