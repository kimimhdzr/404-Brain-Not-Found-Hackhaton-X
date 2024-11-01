package com.example.hackathon_x.Main.Profile.Fragments.Setting;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.hackathon_x.Auth.AuthActivity;
import com.example.hackathon_x.DataBinding.Cache.SignUpCache;
import com.example.hackathon_x.DataBinding.Persistent.UserSharedPreference;
import com.example.hackathon_x.Main.MainActivity;
import com.example.hackathon_x.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.imageview.ShapeableImageView;
import com.google.android.material.switchmaterial.SwitchMaterial;
import com.google.firebase.auth.FirebaseAuth;

public class Setting extends Fragment {
    private FirebaseAuth mAuth;
    ShapeableImageView profileImage;
    private SwitchMaterial switchnotification;
    TextView name;
    private BottomNavigationView bottomNavigationView;
    UserSharedPreference userSharedPreference;
    private ImageButton navtoeditprofile, navtochangepassword, navtoappinformation, navtosupport, navtologout, backbutton;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_setting, container, false);

        mAuth = FirebaseAuth.getInstance();

        profileImage = view.findViewById(R.id.profileImage);
        userSharedPreference = new UserSharedPreference(getContext());
        if (userSharedPreference.getProfilePic() != null) {
            Glide.with(this)
                    .load(userSharedPreference.getProfilePic())
                    .into(profileImage); // Replace with your ImageView reference
        }

        name = view.findViewById(R.id.name);
        String welcome = "Hello " + userSharedPreference.getUserName();
        name.setText(welcome);




        switchnotification = view.findViewById(R.id.switchnotification);

        navtoeditprofile = view.findViewById(R.id.navtoeditprofile);
        navtochangepassword = view.findViewById(R.id.navtochangepassword);
        navtoappinformation = view.findViewById(R.id.navtoappinformation);
        navtosupport = view.findViewById(R.id.navtosupport);
        navtologout = view.findViewById(R.id.navtologout);
        backbutton = view.findViewById(R.id.backbutton);


        NavController navController = NavHostFragment.findNavController(this);
        navtoeditprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.nav_to_editprofile);
            }
        });
        navtochangepassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.nav_to_changepassword);
            }
        });
        navtoappinformation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.nav_to_appinfo);
            }
        });
        navtosupport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.nav_to_support);
            }
        });
        navtologout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Create an AlertDialog builder
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext()); // Use 'getActivity()' if in a Fragment
                builder.setTitle("Confirmation")
                        .setMessage("Do you want to logout now?")
                        .setPositiveButton("Yes", (dialog, which) -> {
                            signOut();
                            dialog.dismiss();
                        })
                        .setNegativeButton("No", (dialog, which) -> {
                            dialog.dismiss();
                        });

                // Create and show the AlertDialog
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });
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

        return view;
    }
    @Override
    public void onDestroy() {
        super.onDestroy();

        if (bottomNavigationView != null) {
            bottomNavigationView.setVisibility(View.VISIBLE);
        }
    }


    private void signOut() {
        mAuth.signOut();
        Toast.makeText(getContext(), "Signed out successfully", Toast.LENGTH_SHORT).show();

        // Optionally, redirect the user to the login screen
        Intent intent = new Intent(getActivity(), AuthActivity.class);
        startActivity(intent);
        getActivity().finish();
    }
}