package com.example.hackathon_x.Main.Home.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import com.bumptech.glide.Glide;
import com.example.hackathon_x.DataBinding.AdapterModel.JobItem1Model;
import com.example.hackathon_x.R;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.imageview.ShapeableImageView;

import java.util.List;

public class JobProgresAdapter extends RecyclerView.Adapter<JobProgresAdapter.JobViewHolder> {
    private List<JobItem1Model> jobList;
    private Context context;

    public JobProgresAdapter(Context context, List<JobItem1Model> jobList) {
        this.context = context;
        this.jobList = jobList;
    }

    @NonNull
    @Override
    public JobViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.job_item_1, parent, false);
        return new JobViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull JobViewHolder holder, int position) {
        JobItem1Model job = jobList.get(position);

        holder.jobName.setText(job.getJobTitle());
        holder.companyName.setText(job.getCompanyName());

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
        TextView jobName, companyName;
        ShapeableImageView companyImage;
        MaterialCardView jobCard;

        public JobViewHolder(@NonNull View itemView) {
            super(itemView);
            jobCard = itemView.findViewById(R.id.jobcard);
            jobName = itemView.findViewById(R.id.jobName);
            companyName = itemView.findViewById(R.id.companyName);
            companyImage = itemView.findViewById(R.id.companyImage);
        }
    }
}
