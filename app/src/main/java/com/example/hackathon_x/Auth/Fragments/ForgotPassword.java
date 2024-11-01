package com.example.hackathon_x.Auth.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hackathon_x.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.progressindicator.LinearProgressIndicator;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;

public class ForgotPassword extends Fragment {

    private EditText emailEditText;
    private AppCompatButton resetButton;
    private TextView gotosignin;
    private LinearProgressIndicator linearprogressindicator;
    private FirebaseAuth mAuth;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_forgot_password, container, false);

        emailEditText = view.findViewById(R.id.emailEditText);
        resetButton = view.findViewById(R.id.resetButton);
        gotosignin = view.findViewById(R.id.gotosignin);
        linearprogressindicator = view.findViewById(R.id.linearprogressindicator);

        NavController navController = NavHostFragment.findNavController(this);
        mAuth = FirebaseAuth.getInstance();

        gotosignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.nav_to_signin);
            }
        });

        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailEditText.getText().toString().trim();

                if (email.isEmpty()) {
                    emailEditText.setError("Email is required");
                    emailEditText.requestFocus();
                    return;
                }
                resetButton.setEnabled(false);
                resetButton.setAlpha(0.5f);
                linearprogressindicator.setVisibility(View.VISIBLE);

                // Send password reset email
                mAuth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Snackbar.make(getView(), "Password reset email sent.", Snackbar.LENGTH_LONG).show();
                            navController.navigate(R.id.nav_to_signin);

                            resetButton.setEnabled(true);
                            resetButton.setAlpha(1.0f);
                            linearprogressindicator.setVisibility(View.INVISIBLE);
                        } else {

                            String errorMessage = "";
                            errorMessage = task.getException().getMessage();
                            Snackbar.make(getView(), "Error: " + errorMessage, Snackbar.LENGTH_LONG).show();

                            resetButton.setEnabled(true);
                            resetButton.setAlpha(1.0f);
                            linearprogressindicator.setVisibility(View.INVISIBLE);
                        }
                    }
                });

            }
        });


        return view;
    }
}