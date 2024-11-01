package com.example.hackathon_x.Auth.Fragments;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.provider.MediaStore;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hackathon_x.DataBinding.Cache.Model.SignUpModel;
import com.example.hackathon_x.DataBinding.Cache.SignUpCache;
import com.example.hackathon_x.Main.MainActivity;
import com.example.hackathon_x.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.imageview.ShapeableImageView;
import com.google.android.material.progressindicator.LinearProgressIndicator;
import com.google.firebase.Timestamp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.UUID;

public class SignUp extends Fragment {

    private boolean isPasswordVisible = false;
    private boolean isConfirmPasswordVisible = false;

    private ShapeableImageView profileImage;
    private TextView signinTxtView, uploadphototxt;
    private FirebaseAuth mAuth;
    private FirebaseFirestore db;

    private EditText nameEditText, emailEditText, addressEditText, passwordEditText, confirmPasswordEditText;
    private Button signUpButton;
    AppCompatImageView truckicon;
    LinearProgressIndicator linearProgressIndicator;
    private static final int PICK_IMAGE_REQUEST = 1;
    public Uri selectedimage;
    private Uri imageUri;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sign_up, container, false);

        //select name, email, pass, confirm pass
        emailEditText = view.findViewById(R.id.emailEditText);
        passwordEditText = view.findViewById(R.id.passwordEditText);
        confirmPasswordEditText = view.findViewById(R.id.confirmpasswordEditText);
        nameEditText = view.findViewById(R.id.nameEditText);

        //button
        signUpButton = view.findViewById(R.id.signupButton);
        signinTxtView = view.findViewById(R.id.gotosignin);

        //select image
        profileImage = view.findViewById(R.id.profileImage);
        uploadphototxt = view.findViewById(R.id.uploadphototxt);

        linearProgressIndicator = view.findViewById(R.id.linearprogressindicator);

        NavController navController = NavHostFragment.findNavController(this);
        signinTxtView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.nav_to_signin);
            }
        });

        passwordEditText.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    // Check if the touch is on the drawableEnd
                    if (event.getRawX() >= (passwordEditText.getRight() - passwordEditText.getCompoundDrawables()[2].getBounds().width())) {
                        togglePasswordVisibility(passwordEditText);
                        return true;
                    }
                }
                return false;
            }
        });
        confirmPasswordEditText.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    // Check if the touch is on the drawableEnd
                    if (event.getRawX() >= (confirmPasswordEditText.getRight() - confirmPasswordEditText.getCompoundDrawables()[2].getBounds().width())) {
                        togglePasswordVisibility(confirmPasswordEditText);
                        return true;
                    }
                }
                return false;
            }
        });
        uploadphototxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openImagePicker();
            }
        });


        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();
        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signUp();
            }
        });


        return view;
    }

    private void openImagePicker() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == Activity.RESULT_OK && data != null) {
            imageUri = data.getData(); // Get the image URI
            profileImage.setImageURI(imageUri); // Set the selected image to the ImageView
            // You can call a method here to upload the image to your server or Firebase
            selectedimage = imageUri;
        }
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



    private void signUp() {
        String userName = nameEditText.getText().toString().trim();
        String userEmail = emailEditText.getText().toString().trim();
        String password = passwordEditText.getText().toString().trim();
        String confirmPassword = confirmPasswordEditText.getText().toString().trim();

        if (TextUtils.isEmpty(userName)) {
            nameEditText.setError("Name is required");
            return;
        }
        if (TextUtils.isEmpty(userEmail)) {
            emailEditText.setError("Email is required");
            return;
        }
        if (TextUtils.isEmpty(password)) {
            passwordEditText.setError("Password is required");
            return;
        }
        if (!password.equals(confirmPassword)) {
            confirmPasswordEditText.setError("Passwords do not match");
            return;
        }

        signUpButton.setEnabled(false);
        signUpButton.setAlpha(0.5f);
        linearProgressIndicator.setVisibility(View.VISIBLE);

        mAuth.createUserWithEmailAndPassword(userEmail, password)
                .addOnCompleteListener(getActivity(), task -> {
                    if (task.isSuccessful()) {

                        FirebaseUser user = mAuth.getCurrentUser();
                        Toast.makeText(getContext(), "Auth Created", Toast.LENGTH_SHORT).show();
                        uploadImageToServer(user.getUid(), userName, userEmail);

                    } else {
                        Toast.makeText(getContext(), "Sign Up Failed: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        signUpButton.setEnabled(true);
                        signUpButton.setAlpha(1.0f);
                        linearProgressIndicator.setVisibility(View.INVISIBLE);
                    }
                });
    }


    private void uploadImageToServer(String uid, String userName, String userEmail) {
        // Implement your image upload logic here
        // For example, using Firebase Storage or your backend service
        if (selectedimage != null) {

            FirebaseStorage firebaseStorage = FirebaseStorage.getInstance();
            StorageReference storageReference = firebaseStorage.getReference();
            StorageReference profileimageref = storageReference.child("images/profiles/" + uid + ".jpg");

            profileimageref.putFile(selectedimage)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            profileimageref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {
                                    String userProfilePic = uri.toString();

                                    Toast.makeText(getContext(), "Successfully Upload profile image", Toast.LENGTH_SHORT).show();
                                    uploadProfile(uid, userName, userEmail, userProfilePic);
                                }
                            });
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.e("Edit Profile", "Fail to upload profile image");

                            Toast.makeText(getContext(), "Fail to upload profile image", Toast.LENGTH_SHORT).show();
                            signUpButton.setEnabled(true);
                            signUpButton.setAlpha(1.0f);
                            linearProgressIndicator.setVisibility(View.INVISIBLE);
                        }
                    });
        } else {
            Log.e("Edit Profile", "No image selected");
            uploadProfile(uid, userName, userEmail, "");
        }
    }
    private void uploadProfile(String uid, String userName, String userEmail, String userProfilePic) {

        //default
        String userPhoneNum = "";
        String userRole = "user";
        String userAddress = "";
        String currentJobPosition = "";
        String joinDate = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());

        SignUpCache.getInstance().getSignUpModel().setuserName(userName);
        SignUpCache.getInstance().getSignUpModel().setuserEmail(userEmail);
        SignUpCache.getInstance().getSignUpModel().setuserPhoneNum(userPhoneNum);
        SignUpCache.getInstance().getSignUpModel().setuserRole(userRole);
        SignUpCache.getInstance().getSignUpModel().setuserProfilePic(userProfilePic);
        SignUpCache.getInstance().getSignUpModel().setuserAddress(userAddress);

        SignUpCache.getInstance().getSignUpModel().setCurrentJobPosition(currentJobPosition);
        SignUpCache.getInstance().getSignUpModel().setJoinDate(joinDate);

        db.collection("users")
                .document(uid)
                .set(SignUpCache.getInstance().getSignUpModel())
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(getContext(), "Sign Up Successful", Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(getActivity(), MainActivity.class);
                        startActivity(intent);
                        getActivity().finish();

//                        NavController navController = NavHostFragment.findNavController(getParentFragment());
//                        navController.navigate(R.id.nav_to_signin);

                        signUpButton.setEnabled(true);
                        signUpButton.setAlpha(1.0f);
                        linearProgressIndicator.setVisibility(View.INVISIBLE);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getContext(), "Failed to save user info", Toast.LENGTH_SHORT).show();

                        signUpButton.setEnabled(true);
                        signUpButton.setAlpha(1.0f);
                        linearProgressIndicator.setVisibility(View.INVISIBLE);
                    }
                });
    }


}