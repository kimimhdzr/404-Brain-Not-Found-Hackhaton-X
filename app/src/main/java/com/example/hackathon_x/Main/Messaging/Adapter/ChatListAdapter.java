package com.example.hackathon_x.Main.Messaging.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.bumptech.glide.Glide;
import com.example.hackathon_x.R;

import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.card.MaterialCardView;
import com.google.android.material.imageview.ShapeableImageView;

import java.util.List;

public class ChatListAdapter extends RecyclerView.Adapter<ChatListAdapter.CardViewHolder> {

    private Context context;
    private List<String> chatProfileList;
    private List<String> chatNameList;
    private List<String> chatLastMessageTimestampList;
    private List<String> chatLastMessageList;

    public ChatListAdapter(Context context,
                           List<String> chatProfileList,
                           List<String> chatNameList,
                           List<String> chatLastMessageTimestampList,
                           List<String> chatLastMessageList
    ) {
        this.context = context;
        this.chatProfileList = chatProfileList;
        this.chatNameList = chatNameList;
        this.chatLastMessageTimestampList = chatLastMessageTimestampList;
        this.chatLastMessageList = chatLastMessageList;
    }

    @NonNull
    @Override
    public CardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.chat_list_item, parent, false);
        return new CardViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CardViewHolder holder, int position) {

        holder.nametxt.setText(chatNameList.get(position));
        holder.lastmessagetimestamptxt.setText(chatLastMessageTimestampList.get(position));
        holder.lastmessagetxt.setText(chatLastMessageList.get(position));
        if (chatProfileList.get(position) != null) {
            Glide.with(context)
                    .load(chatProfileList.get(position)) // Replace with your drawable resource
                    .into(holder.profile_image);
        }
    }

    @Override
    public int getItemCount() {
        return chatNameList.size();
    }


    public static class CardViewHolder extends RecyclerView.ViewHolder {

        TextView nametxt;
        TextView lastmessagetimestamptxt;
        TextView lastmessagetxt;
        MaterialCardView materialCardView;
        ShapeableImageView profile_image;

        public CardViewHolder(@NonNull View itemView) {
            super(itemView);
            profile_image = itemView.findViewById(R.id.profile_image);
            nametxt = itemView.findViewById(R.id.name);
            lastmessagetimestamptxt = itemView.findViewById(R.id.lastmessagetimestamp);
            lastmessagetxt = itemView.findViewById(R.id.lastmessage);
            materialCardView = itemView.findViewById(R.id.chatMaterialCard);
        }
    }
}
