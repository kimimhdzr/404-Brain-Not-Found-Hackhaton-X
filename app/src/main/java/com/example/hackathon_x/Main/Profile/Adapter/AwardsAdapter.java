package com.example.hackathon_x.Main.Profile.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hackathon_x.R;
import com.google.android.material.card.MaterialCardView;

import java.util.List;

public class AwardsAdapter extends RecyclerView.Adapter<AwardsAdapter.LangViewHolder> {
    private List<String> awardsNameList;
    private List<String> awardsByList;
    private List<String> awardsDateList;
    private Context context;

    public AwardsAdapter(
            Context context,
            List<String> awardsNameList,
            List<String> awardsByList,
            List<String> awardsDateList
    ) {
        this.context = context;
        this.awardsNameList = awardsNameList;
        this.awardsByList = awardsByList;
        this.awardsDateList = awardsDateList;
    }

    @NonNull
    @Override
    public AwardsAdapter.LangViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.award_item, parent, false);
        return new AwardsAdapter.LangViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AwardsAdapter.LangViewHolder holder, int position) {

        holder.placeName.setText(awardsNameList.get(position));
        holder.issuedBy.setText(awardsByList.get(position));


        String oneline = "Issued by the " + awardsByList.get(position)
                        + " · " + awardsDateList.get(position);

        holder.issuedBy.setText(oneline);

    }

    @Override
    public int getItemCount() {
        return awardsNameList.size();
    }

    public static class LangViewHolder extends RecyclerView.ViewHolder {
        MaterialCardView awardcard;
        TextView placeName, issuedBy;


        public LangViewHolder(@NonNull View itemView) {
            super(itemView);
            awardcard = itemView.findViewById(R.id.awardcard);
            placeName = itemView.findViewById(R.id.placeName);
            issuedBy = itemView.findViewById(R.id.issuedBy);
        }
    }
}

