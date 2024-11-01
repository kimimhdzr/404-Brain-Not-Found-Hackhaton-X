package com.example.hackathon_x.Main.Mentorship.Fragments;

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
import com.example.hackathon_x.DataBinding.AdapterModel.MentorItem3Model;
import com.example.hackathon_x.DataBinding.Persistent.UserSharedPreference;
import com.example.hackathon_x.Main.MainActivity;
import com.example.hackathon_x.Main.Mentorship.Adapter.SearchAndSavedMentorAdapter;
import com.example.hackathon_x.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.imageview.ShapeableImageView;

import java.util.ArrayList;
import java.util.Arrays;

public class SavedMentors extends Fragment {

    private ImageButton backbutton;
    private BottomNavigationView bottomNavigationView;
    ArrayList<MentorItem3Model> MentorList;
    SearchAndSavedMentorAdapter searchAndSavedMentorAdapter;
    RecyclerView recyclerView;
    ShapeableImageView profileImage;
    TextView name, jobposition;
    UserSharedPreference userSharedPreference;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_saved_mentors, container, false);


        backbutton = view.findViewById(R.id.backbutton);
        backbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getActivity() != null) {
                    getActivity().onBackPressed();
                }
            }
        });
        name = view.findViewById(R.id.nametoolbar);
        jobposition = view.findViewById(R.id.jobtoolbar);
        profileImage = view.findViewById(R.id.profileImagetoolbar);
        userSharedPreference = new UserSharedPreference(getContext());
        if (userSharedPreference.getProfilePic() != null) {
            Glide.with(this)
                    .load(userSharedPreference.getProfilePic())
                    .into(profileImage); // Replace with your ImageView reference
        }
        String welcome = "Hello " + userSharedPreference.getUserName();
        name.setText(welcome);



        bottomNavigationView = ((MainActivity) getActivity()).findViewById(R.id.bottomNavView);
        if (bottomNavigationView != null) {
            bottomNavigationView.setVisibility(View.GONE);
        }


        //Setup RecyclerView 1
        recyclerView = view.findViewById(R.id.recyclerview);
        // Setup MentorList with dummy data for MentorItem3Model
        ArrayList<String> jobIndustry1 = new ArrayList<>(Arrays.asList("IT", "Software Development"));
        ArrayList<String> jobIndustry2 = new ArrayList<>(Arrays.asList("Finance", "Data Science"));
        ArrayList<String> jobIndustry3 = new ArrayList<>(Arrays.asList("Marketing", "Design"));

        ArrayList<String> jobSkills1 = new ArrayList<>(Arrays.asList("Java", "Python", "Spring Boot"));
        ArrayList<String> jobSkills2 = new ArrayList<>(Arrays.asList("SQL", "Data Analysis", "R"));
        ArrayList<String> jobSkills3 = new ArrayList<>(Arrays.asList("Photoshop", "Content Creation", "Branding"));

        MentorList = new ArrayList<>(Arrays.asList(
                new MentorItem3Model(
                        "M101",
                        "Alice Johnson",
                        "Active",
                        "Experienced software developer with expertise in backend technologies.",
                        "Software Engineer",
                        "New York, NY",
                        "https://dummyimage.com/100x100/000/fff&text=AliceJ",
                        jobIndustry1,
                        jobSkills1,
                        "Tech Solutions Ltd"
                ),
                new MentorItem3Model(
                        "M102",
                        "Robert Smith",
                        "Available",
                        "Data analyst with a strong background in statistical analysis and machine learning.",
                        "Data Analyst",
                        "San Francisco, CA",
                        "https://dummyimage.com/100x100/000/fff&text=RobertS",
                        jobIndustry2,
                        jobSkills2,
                        "DataCorp"
                ),
                new MentorItem3Model(
                        "M103",
                        "Sophia Lee",
                        "Busy",
                        "Creative professional focused on marketing strategies and brand development.",
                        "Marketing Specialist",
                        "Austin, TX",
                        "https://dummyimage.com/100x100/000/fff&text=SophiaL",
                        jobIndustry3,
                        jobSkills3,
                        "Creative Minds"
                )
        ));

        searchAndSavedMentorAdapter = new SearchAndSavedMentorAdapter(
                getContext(),
                MentorList
        );
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setItemAnimator(new DefaultItemAnimator()); //add animation to the cards
        recyclerView.setAdapter(searchAndSavedMentorAdapter);


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