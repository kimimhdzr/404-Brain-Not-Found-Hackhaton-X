package com.example.hackathon_x.Auth.Fragments;

import android.os.Bundle;

import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.hackathon_x.DataBinding.Cache.SignUpCache;
import com.example.hackathon_x.R;
import com.google.android.material.chip.Chip;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PreferenceIndustry extends Fragment {

    private AppCompatButton nextButton;
    private AppCompatButton skipButton;
    private int chipCount;
    private Chip chip1, chip2, chip3, chip4, chip5, chip6, chip7, chip8, chip9, chip10, chip11, chip12;
    private List<String> selectedChipsText = new ArrayList<>();
    private static final int MAX_SELECTIONS = 3;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_select_interest, container, false);


        nextButton = view.findViewById(R.id.nextButton);
        skipButton = view.findViewById(R.id.skipButton);

        chip1 = view.findViewById(R.id.chip1);
        chip2 = view.findViewById(R.id.chip2);

        chip3 = view.findViewById(R.id.chip3);
        chip4 = view.findViewById(R.id.chip4);
        chip5 = view.findViewById(R.id.chip5);

        chip6 = view.findViewById(R.id.chip6);
        chip7 = view.findViewById(R.id.chip7);
        chip8 = view.findViewById(R.id.chip8);

        chip9 = view.findViewById(R.id.chip9);
        chip10 = view.findViewById(R.id.chip10);
        chip11 = view.findViewById(R.id.chip11);

        chip12 = view.findViewById(R.id.chip12);


        NavController navController = NavHostFragment.findNavController(this);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SignUpCache.getInstance().getSignUpModel().setJobIndustry(selectedChipsText);

                navController.navigate(R.id.nav_to_preferencelocationlevelsalary);

            }
        });
        skipButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.nav_to_preferencelocationlevelsalary);
            }
        });

        // Setup chip click listeners
        setupChipClickListeners();


        return view;
    }
    private void setupChipClickListeners() {
        // Add listener for each chip
        for (Chip chip : new Chip[]{
                chip1, chip2, chip3, chip4,
                chip5, chip6, chip7, chip8,
                chip9, chip10, chip11, chip12
        }) {
            chip.setOnCheckedChangeListener((buttonView, isChecked) -> {
                if (isChecked) {
                    if (selectedChipsText.size() < MAX_SELECTIONS) {
                        selectedChipsText.add(buttonView.getText().toString());
                        Log.d("Preferences 1", "Add : " + buttonView.getText().toString());
                    } else {
                        // Deselect the chip if more than MAX_SELECTIONS
                        chip.setChecked(false);
                    }
                } else {
                    // Remove the chip text from selected set when unchecked
                    selectedChipsText.remove(buttonView.getText().toString());
                    Log.d("Preferences 1", "Remove : " + buttonView.getText().toString());
                }
            });
        }
    }
}