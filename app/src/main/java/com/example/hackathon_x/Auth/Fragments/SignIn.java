package com.example.hackathon_x.Auth.Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hackathon_x.Main.MainActivity;
import com.example.hackathon_x.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.progressindicator.LinearProgressIndicator;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.EventListener;

public class SignIn extends Fragment {

    private FirebaseAuth mAuth;
    private EditText email, password;
    private Button loginButton;
    private TextView signupTxtView;
    private TextView forgotpassTxtView;
    private boolean isPasswordVisible = false;
    AppCompatImageView truckicon;
    LinearProgressIndicator linearProgressIndicator;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sign_in, container, false);

        email = view.findViewById(R.id.emailEditText);
        password = view.findViewById(R.id.passwordEditText);

        forgotpassTxtView = view.findViewById(R.id.forgotpassword);
        signupTxtView = view.findViewById(R.id.createnewacc);

        loginButton = view.findViewById(R.id.signinButton);
        linearProgressIndicator = view.findViewById(R.id.linearprogressindicator);

        NavController navController = NavHostFragment.findNavController(this);
        signupTxtView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.nav_to_selectinterest);
            }
        });
        forgotpassTxtView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.nav_to_forgotpassword);
            }
        });

        mAuth = FirebaseAuth.getInstance();

        password.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_UP){
                    // Check if the touch is on the drawableEnd
                    if (event.getRawX() >= (password.getRight() - password.getCompoundDrawables()[2].getBounds().width())) {
                        togglePasswordVisibility(password);
                        return true;
                    }
                }
                return false;
            }
        });
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String emailText = email.getText().toString().trim();
                String passwordText = password.getText().toString().trim();

                if (TextUtils.isEmpty(emailText)) {
                    email.setError("Email is required");
                    return;
                }

                if (TextUtils.isEmpty(passwordText)) {
                    password.setError("Password is required");
                    return;
                }

                loginButton.setEnabled(false);
                loginButton.setAlpha(0.5f);
                linearProgressIndicator.setVisibility(View.VISIBLE);


                mAuth.signInWithEmailAndPassword(emailText, passwordText).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(getContext(), "Login successful!", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getActivity(), MainActivity.class);
                            startActivity(intent);
                            getActivity().finish();
                            loginButton.setEnabled(true);
                            loginButton.setAlpha(1.0f);
                        } else {
                            Toast.makeText(getContext(), "Login failed: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            loginButton.setEnabled(true);
                            loginButton.setAlpha(1.0f);
                            linearProgressIndicator.setVisibility(View.INVISIBLE);
                        }

                    }
                });

            }
        });


        return view;
    }


    private void togglePasswordVisibility(EditText passwordEditText) {
        if (isPasswordVisible) {
            // Hide password
            passwordEditText.setTransformationMethod(PasswordTransformationMethod.getInstance());
            passwordEditText.setCompoundDrawablesWithIntrinsicBounds(
                    R.drawable.baseline_lock_24, 0,
                    R.drawable.baseline_visibility_24, 0
            );
        } else {
            // Show password
            passwordEditText.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            passwordEditText.setCompoundDrawablesWithIntrinsicBounds(
                    R.drawable.baseline_lock_24, 0,
                    R.drawable.baseline_visibility_off_24, 0
            );
        }
        isPasswordVisible = !isPasswordVisible;

        // Move cursor to the end of the text
        passwordEditText.setSelection(passwordEditText.getText().length());
    }


}