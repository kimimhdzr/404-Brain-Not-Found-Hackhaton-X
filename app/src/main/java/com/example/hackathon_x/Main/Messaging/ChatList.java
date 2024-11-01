package com.example.hackathon_x.Main.Messaging;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.hackathon_x.DataBinding.Persistent.UserSharedPreference;
import com.example.hackathon_x.Main.MainActivity;
import com.example.hackathon_x.Main.Messaging.Adapter.ChatListAdapter;
import com.example.hackathon_x.Main.Profile.Adapter.ExperienceAdapter;
import com.example.hackathon_x.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.imageview.ShapeableImageView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ChatList extends Fragment {
    ImageButton backbutton;
    RecyclerView recyclerview;
    ShapeableImageView profileImagetoolbar;
    TextView nametoolbar;
    ChatListAdapter chatListAdapter;
    UserSharedPreference userSharedPreference;

    private BottomNavigationView bottomNavigationView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_chat_list, container, false);

        backbutton = view.findViewById(R.id.backbutton);

        backbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getActivity() != null) {
                    getActivity().onBackPressed();
                }
            }
        });

        bottomNavigationView = ((MainActivity) getActivity()).findViewById(R.id.bottomNavView);
        if (bottomNavigationView != null) {
            bottomNavigationView.setVisibility(View.GONE);
        }


        nametoolbar = view.findViewById(R.id.nametoolbar);
        profileImagetoolbar = view.findViewById(R.id.profileImagetoolbar);
        userSharedPreference = new UserSharedPreference(getContext());
        if (userSharedPreference.getProfilePic() != null) {
            Glide.with(this)
                    .load(userSharedPreference.getProfilePic())
                    .into(profileImagetoolbar); // Replace with your ImageView reference
        }
        String welcome = "Hello " + userSharedPreference.getUserName();
        nametoolbar.setText(welcome);


        // Setup recycler view
        recyclerview = view.findViewById(R.id.recyclerview);
        List<String> chatProfileList = new ArrayList<>(Arrays.asList(
                "https://dummyimage.com/100x100/000/fff&text=G",
                "https://dummyimage.com/100x100/000/fff&text=A",
                "https://dummyimage.com/100x100/000/fff&text=M",
                "https://dummyimage.com/100x100/000/fff&text=M",
                "https://dummyimage.com/100x100/000/fff&text=A"
        ));

        List<String> chatNameList = new ArrayList<>(Arrays.asList(
                "Alice Johnson",
                "Bob Smith",
                "Charlie Brown",
                "Daisy Evans",
                "Eve Black"
        ));

        List<String> chatLastMessageTimestampList = new ArrayList<>(Arrays.asList(
                "10:45 AM",
                "Yesterday",
                "Oct 28, 2024",
                "Oct 27, 2024",
                "Oct 26, 2024"
        ));

        List<String> chatLastMessageList = new ArrayList<>(Arrays.asList(
                "Hey! Are you available for a quick call?",
                "Sent over the documents you requested.",
                "Let's discuss the project details tomorrow.",
                "Thanks for the update!",
                "Looking forward to our meeting next week."
        ));

        chatListAdapter = new ChatListAdapter(
                getContext(),
                chatProfileList,
                chatNameList,
                chatLastMessageTimestampList,
                chatLastMessageList
        );
        recyclerview.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerview.setItemAnimator(new DefaultItemAnimator()); //add animation to the cards
        recyclerview.setAdapter(chatListAdapter);

        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        if (bottomNavigationView != null) {
            bottomNavigationView.setVisibility(View.VISIBLE);
        }
    }
}