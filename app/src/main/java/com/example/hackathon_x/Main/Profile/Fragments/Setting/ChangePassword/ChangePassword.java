package com.example.hackathon_x.Main.Profile.Fragments.Setting.ChangePassword;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.hackathon_x.Main.MainActivity;
import com.example.hackathon_x.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class ChangePassword extends Fragment {
    private BottomNavigationView bottomNavigationView;
    TextView gotosignin;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_change_password, container, false);


        bottomNavigationView = ((MainActivity) getActivity()).findViewById(R.id.bottomNavView);
        if (bottomNavigationView != null) {
            bottomNavigationView.setVisibility(View.GONE);
        }

        gotosignin = view.findViewById(R.id.gotosignin);

        gotosignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getActivity() != null) {
                    getActivity().onBackPressed();
                }
            }
        });



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