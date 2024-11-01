package com.example.hackathon_x.Main.Job.Fragments;

import android.os.Bundle;

import androidx.appcompat.widget.AppCompatImageButton;
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

import com.example.hackathon_x.DataBinding.AdapterModel.JobItem3Model;
import com.example.hackathon_x.Main.Job.Adapter.JobSearchAdapter;
import com.example.hackathon_x.Main.MainActivity;
import com.example.hackathon_x.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class JobSearch extends Fragment {

    private ImageButton backbutton;
    private AppCompatImageButton savedJob;
    private RecyclerView recyclerView;
    private JobSearchAdapter jobSearchAdapter;
    private List<JobItem3Model> jobList;
    private BottomNavigationView bottomNavigationView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_job_search, container, false);

        backbutton = view.findViewById(R.id.backbutton);
        savedJob = view.findViewById(R.id.savedJob);
        recyclerView = view.findViewById(R.id.recyclerview);

        bottomNavigationView = ((MainActivity) getActivity()).findViewById(R.id.bottomNavView);
        if (bottomNavigationView != null) {
            bottomNavigationView.setVisibility(View.GONE);
        }

        backbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getActivity() != null) {
                    getActivity().onBackPressed();
                }
            }
        });

        NavController navController = NavHostFragment.findNavController(this);
        savedJob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.nav_to_savedjob);
            }
        });

        // Setup jobList with dummy data for JobItem3Model
        ArrayList<String> jobSkills1 = new ArrayList<>(Arrays.asList("Java", "Python", "Spring Boot"));
        ArrayList<String> jobSkills2 = new ArrayList<>(Arrays.asList("SQL", "Data Analysis", "Power BI"));
        ArrayList<String> jobSkills3 = new ArrayList<>(Arrays.asList("React", "JavaScript", "CSS"));

        ArrayList<String> jobIndustry1 = new ArrayList<>(Arrays.asList("IT", "Software"));
        ArrayList<String> jobIndustry2 = new ArrayList<>(Arrays.asList("Finance", "Data Analysis"));
        ArrayList<String> jobIndustry3 = new ArrayList<>(Arrays.asList("Design", "Creative Services"));

        jobList = new ArrayList<>(Arrays.asList(
                new JobItem3Model(
                        "J101",
                        "Tech Solutions Ltd",
                        "https://dummyimage.com/100x100/000/fff&text=TechSolutions",
                        "Software Developer",
                        "Develop and maintain software applications.",
                        "Full-time",
                        "01 Nov 2024",
                        "New York, NY",
                        "$80,000 - $100,000",
                        jobSkills1,
                        jobIndustry1
                ),
                new JobItem3Model(
                        "J102",
                        "DataCorp",
                        "https://dummyimage.com/100x100/000/fff&text=DataCorp",
                        "Data Analyst",
                        "Analyze and interpret complex data sets.",
                        "Part-time",
                        "05 Nov 2024",
                        "San Francisco, CA",
                        "$60,000 - $75,000",
                        jobSkills2,
                        jobIndustry2
                ),
                new JobItem3Model(
                        "J103",
                        "Creative Minds",
                        "https://dummyimage.com/100x100/000/fff&text=CreativeMinds",
                        "UX Designer",
                        "Design user-friendly interfaces for web and mobile applications.",
                        "Contract",
                        "10 Nov 2024",
                        "Austin, TX",
                        "$70,000 - $85,000",
                        jobSkills3,
                        jobIndustry3
                )
        ));

        jobSearchAdapter = new JobSearchAdapter(
                getContext(),
                jobList
        );
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setItemAnimator(new DefaultItemAnimator()); //add animation to the cards
        recyclerView.setAdapter(jobSearchAdapter);




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