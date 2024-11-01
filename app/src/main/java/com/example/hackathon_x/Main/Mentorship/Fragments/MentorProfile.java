package com.example.hackathon_x.Main.Mentorship.Fragments;

import android.os.Bundle;

import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.example.hackathon_x.Main.MainActivity;
import com.example.hackathon_x.Main.Profile.Adapter.AwardsAdapter;
import com.example.hackathon_x.Main.Profile.Adapter.EduAdapter;
import com.example.hackathon_x.Main.Profile.Adapter.ExperienceAdapter;
import com.example.hackathon_x.Main.Profile.Adapter.LangAdapter;
import com.example.hackathon_x.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MentorProfile extends Fragment {

    private ImageButton backbutton;
    AppCompatButton connectButton, apprenticeButton, messageButton;
    private BottomNavigationView bottomNavigationView;
    RecyclerView recyclerviewexp;
    RecyclerView recyclerviewedu;
    RecyclerView recyclerviewawards;
    RecyclerView recyclerviewlang;
    RecyclerView recyclerviewwebinar;
    LangAdapter langAdapter;
    ExperienceAdapter experienceAdapter;
    EduAdapter eduAdapter;
    AwardsAdapter awardsAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_mentor_profile, container, false);

        bottomNavigationView = ((MainActivity) getActivity()).findViewById(R.id.bottomNavView);
        if (bottomNavigationView != null) {
            bottomNavigationView.setVisibility(View.GONE);
        }

        NavController navController = NavHostFragment.findNavController(this);


        backbutton = view.findViewById(R.id.backbutton);
        backbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getActivity() != null) {
                    getActivity().onBackPressed();
                }
            }
        });


        connectButton = view.findViewById(R.id.connectButton);
        apprenticeButton = view.findViewById(R.id.apprenticeButton);
        messageButton = view.findViewById(R.id.messageButton);
        connectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
        apprenticeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
        messageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.nav_to_chatlist);

            }
        });


        //Setup recyclerviewexp
        recyclerviewexp = view.findViewById(R.id.recyclerview1);
        List<String> expImage = new ArrayList<>(Arrays.asList(
                "https://dummyimage.com/100x100/000/fff&text=Google",
                "https://dummyimage.com/100x100/000/fff&text=Amazon",
                "https://dummyimage.com/100x100/000/fff&text=Facebook",
                "https://dummyimage.com/100x100/000/fff&text=Microsoft",
                "https://dummyimage.com/100x100/000/fff&text=Apple"
        ));
        List<String> expPosition = new ArrayList<>(Arrays.asList(
                "Software Engineer",
                "Data Analyst",
                "Product Manager",
                "UX Designer",
                "System Architect"
        ));
        List<String> expAssociated = new ArrayList<>(Arrays.asList(
                "Google",
                "Amazon",
                "Meta",
                "Microsoft",
                "Apple"
        ));
        List<String> expType = new ArrayList<>(Arrays.asList(
                "Full-time",
                "Part-time",
                "Contract",
                "Internship",
                "Full-time"
        ));
        List<String> expFrom = new ArrayList<>(Arrays.asList(
                "June 2018",
                "April 2019",
                "September 2020",
                "January 2021",
                "March 2022"
        ));
        List<String> expTo = new ArrayList<>(Arrays.asList(
                "Present",
                "December 2020",
                "November 2021",
                "July 2022",
                "Present"
        ));
        List<String> expRange = new ArrayList<>(Arrays.asList(
                "3 years",
                "1 year 8 months",
                "1 year 2 months",
                "1 year 6 months",
                "2 years"
        ));

        experienceAdapter = new ExperienceAdapter(
                getContext(),
                expImage,
                expPosition,
                expAssociated,
                expType,
                expFrom,
                expTo,
                expRange
        );
        recyclerviewexp.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerviewexp.setItemAnimator(new DefaultItemAnimator()); //add animation to the cards
        recyclerviewexp.setAdapter(experienceAdapter);



        //Setup recyclerviewedu
        recyclerviewedu = view.findViewById(R.id.recyclerview2);
        List<String> eduImage = new ArrayList<>(Arrays.asList(
                "https://dummyimage.com/100x100/000/fff&text=Harvard",
                "https://dummyimage.com/100x100/000/fff&text=MIT",
                "https://dummyimage.com/100x100/000/fff&text=Stanford",
                "https://dummyimage.com/100x100/000/fff&text=Oxford",
                "https://dummyimage.com/100x100/000/fff&text=Cambridge"
        ));
        List<String> eduName = new ArrayList<>(Arrays.asList(
                "Harvard University",
                "Massachusetts Institute of Technology",
                "Stanford University",
                "University of Oxford",
                "University of Cambridge"
        ));

        List<String> eduLevel = new ArrayList<>(Arrays.asList(
                "Bachelor's Degree",
                "Master's Degree",
                "PhD",
                "Bachelor's Degree",
                "Master's Degree"
        ));
        List<String> eduType = new ArrayList<>(Arrays.asList(
                "Full-time",
                "Full-time",
                "Research-based",
                "Part-time",
                "Full-time"
        ));

        List<String> eduFrom = new ArrayList<>(Arrays.asList(
                "September 2015",
                "September 2016",
                "September 2017",
                "October 2018",
                "October 2019"
        ));
        List<String> eduTo = new ArrayList<>(Arrays.asList(
                "May 2019",
                "May 2018",
                "August 2021",
                "July 2020",
                "July 2021"
        ));

        List<String> eduRange = new ArrayList<>(Arrays.asList(
                "4 years",
                "2 years",
                "4 years",
                "2 years",
                "2 years"
        ));

        eduAdapter = new EduAdapter(
                getContext(),
                eduImage,
                eduName,
                eduLevel,
                eduType,
                eduFrom,
                eduTo,
                eduRange
        );
        recyclerviewedu.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerviewedu.setItemAnimator(new DefaultItemAnimator()); //add animation to the cards
        recyclerviewedu.setAdapter(eduAdapter);



        //Setup recyclerviewawards
        recyclerviewawards = view.findViewById(R.id.recyclerview3);
        List<String> awardsNameList = new ArrayList<>(Arrays.asList(
                "Employee of the Year",
                "Innovation in Technology",
                "Best Project Leader",
                "Top Developer Award",
                "Excellence in Coding"
        ));

        List<String> awardsByList = new ArrayList<>(Arrays.asList(
                "Tech Corp Inc.",
                "National Science Foundation",
                "Project Managers Association",
                "Software Developer Society",
                "Code Excellence International"
        ));

        List<String> awardsDateList = new ArrayList<>(Arrays.asList(
                "December 2021",
                "June 2022",
                "September 2020",
                "March 2023",
                "November 2019"
        ));

        awardsAdapter = new AwardsAdapter(
                getContext(),
                awardsNameList,
                awardsByList,
                awardsDateList
        );
        recyclerviewawards.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerviewawards.setItemAnimator(new DefaultItemAnimator()); //add animation to the cards
        recyclerviewawards.setAdapter(awardsAdapter);


        //Setup recyclerviewlang
        recyclerviewlang = view.findViewById(R.id.recyclerview4);
        List<String> langNameList = new ArrayList<>(Arrays.asList(
                "English",
                "Mandarin",
                "French",
                "Spanish",
                "Japanese"
        ));

        List<String> langCertList = new ArrayList<>(Arrays.asList(
                "TOEFL",
                "HSK Level 5",
                "DELF B2",
                "DELE B1",
                "JLPT N3"
        ));

        List<String> langLevelList = new ArrayList<>(Arrays.asList(
                "Fluent",
                "Intermediate",
                "Conversational",
                "Beginner",
                "Intermediate"
        ));

        langAdapter = new LangAdapter(
                getContext(),
                langNameList,
                langCertList,
                langLevelList
        );
        recyclerviewlang.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerviewlang.setItemAnimator(new DefaultItemAnimator()); //add animation to the cards
        recyclerviewlang.setAdapter(langAdapter);

        recyclerviewwebinar = view.findViewById(R.id.recyclerviewwebinar);





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