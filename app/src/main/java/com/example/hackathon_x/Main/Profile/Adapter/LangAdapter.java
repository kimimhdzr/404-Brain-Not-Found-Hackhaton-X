package com.example.hackathon_x.Main.Profile.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hackathon_x.DataBinding.AdapterModel.JobItem3Model;
import com.example.hackathon_x.Main.Job.Adapter.JobSearchAdapter;
import com.example.hackathon_x.R;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import com.google.android.material.imageview.ShapeableImageView;

import java.util.List;

public class LangAdapter extends RecyclerView.Adapter<LangAdapter.LangViewHolder> {
    private List<String> langNameList;
    private List<String> langCertList;
    private List<String> langLevelList;
    private Context context;

    public LangAdapter(
            Context context,
            List<String> langNameList,
            List<String> langCertList,
            List<String> langLevelList
    ) {
        this.context = context;
        this.langNameList = langNameList;
        this.langCertList = langCertList;
        this.langLevelList = langLevelList;
    }

    @NonNull
    @Override
    public LangAdapter.LangViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.language_item, parent, false);
        return new LangAdapter.LangViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LangAdapter.LangViewHolder holder, int position) {

        holder.languageName.setText(langNameList.get(position));
        holder.languageCert.setText(langCertList.get(position));
        holder.languageLevel.setText(langLevelList.get(position));

    }

    @Override
    public int getItemCount() {
        return langNameList.size();
    }

    public static class LangViewHolder extends RecyclerView.ViewHolder {
        MaterialCardView languagecard;
        TextView languageName, languageCert, languageLevel;


        public LangViewHolder(@NonNull View itemView) {
            super(itemView);
            languagecard = itemView.findViewById(R.id.languagecard);
            languageName = itemView.findViewById(R.id.languageName);
            languageCert = itemView.findViewById(R.id.languageCert);
            languageLevel = itemView.findViewById(R.id.languageLevel);
        }
    }
}
