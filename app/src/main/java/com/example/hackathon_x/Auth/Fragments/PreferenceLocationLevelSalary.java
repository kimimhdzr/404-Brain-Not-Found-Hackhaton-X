package com.example.hackathon_x.Auth.Fragments;

import android.os.Bundle;

import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.hackathon_x.DataBinding.Cache.SignUpCache;
import com.example.hackathon_x.R;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;

import java.util.ArrayList;
import java.util.List;

public class PreferenceLocationLevelSalary extends Fragment {

    private Spinner spinner_location, spinner_job_level, spinner_job_salary;
    private AppCompatButton nextButton;
    private AppCompatButton skipButton;

    private String jobLocation;
    private String jobLevel;
    private String jobSalary;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_preference_location_level_salary, container, false);

        spinner_location = view.findViewById(R.id.spinner_location);
        spinner_job_level = view.findViewById(R.id.spinner_job_level);
        spinner_job_salary = view.findViewById(R.id.spinner_job_salary);


        nextButton = view.findViewById(R.id.nextButton);
        skipButton = view.findViewById(R.id.skipButton);

        NavController navController = NavHostFragment.findNavController(this);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SignUpCache.getInstance().getSignUpModel().setJobLocation(jobLocation);
                SignUpCache.getInstance().getSignUpModel().setJobLevel(jobLevel);
                SignUpCache.getInstance().getSignUpModel().setJobSalary(jobSalary);
                navController.navigate(R.id.nav_to_signup);
            }
        });

        skipButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.nav_to_signup);
            }
        });


        // Set up location spinner
        ArrayAdapter<CharSequence> locationAdapter = ArrayAdapter.createFromResource(
                getContext(),
                R.array.malaysia_states,
                android.R.layout.simple_spinner_item
        );
        locationAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_location.setAdapter(locationAdapter);

        // Set up job level spinner
        ArrayAdapter<CharSequence> jobLevelAdapter = ArrayAdapter.createFromResource(
                getContext(),
                R.array.job_levels,
                android.R.layout.simple_spinner_item
        );
        jobLevelAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_job_level.setAdapter(jobLevelAdapter);

        // Set up job salary spinner
        ArrayAdapter<CharSequence> jobSalaryAdapter = ArrayAdapter.createFromResource(
                getContext(),
                R.array.salary_ranges,
                android.R.layout.simple_spinner_item
        );
        jobSalaryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_job_salary.setAdapter(jobSalaryAdapter);


        // Set up listeners for each spinner
        spinner_location.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                jobLocation = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Do nothing
            }
        });

        spinner_job_level.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                jobLevel = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Do nothing
            }
        });

        spinner_job_salary.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                jobSalary = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Do nothing
            }
        });
        return view;
    }
}