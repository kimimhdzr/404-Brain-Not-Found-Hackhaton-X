package com.example.hackathon_x.Main.Job.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.hackathon_x.DataBinding.AdapterModel.JobSwipeModel;
import com.example.hackathon_x.DataBinding.Cache.Model.JobModel;
import com.example.hackathon_x.R;
import com.google.android.material.chip.ChipGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class JobCardAdapter extends RecyclerView.Adapter<JobCardAdapter.JobViewHolder> {

    List<JobSwipeModel> jobCards;

    public JobCardAdapter(List<JobSwipeModel> jobCards) {
        this.jobCards = jobCards;
    }

    @NonNull
    @Override
    public JobViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.job_swipe_item, parent, false);
        return new JobViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull JobViewHolder holder, int position) {
        JobSwipeModel job = jobCards.get(position);
        holder.bind(job);
    }

    @Override
    public int getItemCount() {
        return jobCards.size();
    }

    public static class JobViewHolder extends RecyclerView.ViewHolder {

        ImageView companyLogo;
        TextView jobTitle, jobDesc, jobRequiredSkills;
        TextView companyName, companyIndustry, companyLocation;
        TextView postedDate, numberApplicants;
        ChipGroup jobtype;

        public JobViewHolder(@NonNull View itemView) {
            super(itemView);
            companyLogo = itemView.findViewById(R.id.companyLogo);

            jobTitle = itemView.findViewById(R.id.jobTitle);
            jobDesc = itemView.findViewById(R.id.jobDesc);
//            jobRequiredSkills = itemView.findViewById(R.id.jobRequiredSkills);
            jobtype = itemView.findViewById(R.id.chipGroup1);

            companyName = itemView.findViewById(R.id.companyName);
            companyIndustry = itemView.findViewById(R.id.companyIndustry);
            companyLocation = itemView.findViewById(R.id.companyLocation);

            postedDate = itemView.findViewById(R.id.postedDate);
            numberApplicants = itemView.findViewById(R.id.numberApplicants);
        }

        public void bind(JobSwipeModel job) {
            if (job.getCompanyLogo() != null) {
                Glide.with(itemView)
                        .load(job.getCompanyLogo()) // Replace with your drawable resource
                        .into(companyLogo);
            }
            jobTitle.setText(job.getJobTitle());
            jobDesc.setText(job.getJobDesc());
//            jobRequiredSkills.setText(job.getJobRequiredSkills());
//            jobType.setText(job.getJobType());

            companyName.setText(job.getCompanyName());
            companyIndustry.setText(job.getCompanyIndustry());
            companyLocation.setText(job.getCompanyLocation());

            postedDate.setText(job.getPostedDate());
            numberApplicants.setText(job.getNumberApplicants());
        }
    }
}
