package com.example.hackathon_x.Main.Job;

import android.os.Bundle;

import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;

import com.example.hackathon_x.DataBinding.AdapterModel.JobItem3Model;
import com.example.hackathon_x.DataBinding.AdapterModel.JobSwipeModel;
import com.example.hackathon_x.Main.Job.Adapter.JobCardAdapter;
import com.example.hackathon_x.R;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.chip.ChipGroup;
import com.google.android.material.slider.Slider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.yuyakaido.android.cardstackview.CardStackLayoutManager;
import com.yuyakaido.android.cardstackview.CardStackListener;
import com.yuyakaido.android.cardstackview.CardStackView;
import com.yuyakaido.android.cardstackview.Direction;
import com.yuyakaido.android.cardstackview.StackFrom;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Job extends Fragment {

    AppCompatImageButton savedJob, filter, reject, apply, save;
    EditText search_input;
    CardStackView cardStackView;
    List<JobSwipeModel> jobCards;
    JobCardAdapter jobCardAdapter;
    FirebaseFirestore db;
    DocumentSnapshot lastVisible; // To keep track of the last document for pagination
    boolean isLoading = false;    // To prevent multiple calls at the same time
    CardStackLayoutManager manager;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_job, container, false);

        savedJob = view.findViewById(R.id.saved);
        filter = view.findViewById(R.id.filter);
        search_input = view.findViewById(R.id.search_input);

        reject = view.findViewById(R.id.reject);
        apply = view.findViewById(R.id.apply);
        save = view.findViewById(R.id.save);

        NavController navController = NavHostFragment.findNavController(this);
        savedJob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.nav_to_savedjob);
            }
        });


        filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create a BottomSheetDialog
                BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(v.getContext());

                // Inflate the layout for the bottom sheet
                View bottomSheetView = getLayoutInflater().inflate(R.layout.bottom_sheet_job, null);

                // Set the layout as the content view for the BottomSheetDialog
                bottomSheetDialog.setContentView(bottomSheetView);

                // Find views in the BottomSheetDialog, if you need to interact with them
                ChipGroup locationChipGroup = bottomSheetView.findViewById(R.id.locationchipground);
                Slider salarySlider = bottomSheetView.findViewById(R.id.slider);

                AppCompatButton resetbutton = bottomSheetView.findViewById(R.id.resetbutton);
                AppCompatButton applybutton = bottomSheetView.findViewById(R.id.applybutton);

                db = FirebaseFirestore.getInstance();
                resetbutton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        bottomSheetDialog.dismiss();
                        navController.navigate(R.id.nav_to_jobsearch);
                    }
                });
                applybutton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        bottomSheetDialog.dismiss();
                        navController.navigate(R.id.nav_to_jobsearch);
                    }
                });

                // Show the BottomSheetDialog
                bottomSheetDialog.show();
            }
        });


        jobCards = new ArrayList<>();
        cardStackView = view.findViewById(R.id.card_stack_view);
        jobCardAdapter = new JobCardAdapter(
                jobCards
        );
        cardStackView.setAdapter(jobCardAdapter);

        manager = new CardStackLayoutManager(getContext(), new CardStackListener() {
            @Override
            public void onCardSwiped(Direction direction) {
                if (direction == Direction.Right) {
                    // Get the job ID of the current card being swiped
                    int position = manager.getTopPosition() - 1;
                    String jobId = jobCards.get(position).getId();

                    // Update the job ID to the "Ongoing" array in Firestore
                    FirebaseFirestore db = FirebaseFirestore.getInstance();
                    String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();

                    db.collection("users")
                            .document(userId)
                            .update("jobApplication.Ongoing", FieldValue.arrayUnion(jobId))
                            .addOnSuccessListener(aVoid -> Log.d("Firestore", "Job added to Ongoing"))
                            .addOnFailureListener(e -> Log.e("Firestore", "Error adding job to Ongoing", e));
                    db.collection("job_posts")
                            .document(jobId)
                            .update("applicants", FieldValue.arrayUnion(userId))
                            .addOnSuccessListener(aVoid -> Log.d("Firestore", "User Id added to the applicants"))
                            .addOnFailureListener(e -> Log.e("Firestore", "Error adding user id to the applicants", e));

                }
                if (direction == Direction.Left) {
                    // Get the job ID of the current card being swiped
                    int position = manager.getTopPosition() - 1;
                    if (position >= 0 && position < jobCards.size()) {
                        String jobId = jobCards.get(position).getId();

                        // Update the job ID to the "Ongoing" array in Firestore
                        FirebaseFirestore db = FirebaseFirestore.getInstance();
                        String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();

                        db.collection("users")
                                .document(userId)
                                .update("savedJob", FieldValue.arrayUnion(jobId))
                                .addOnSuccessListener(aVoid -> {
                                    Log.d("Firestore", "Job added to Saved");
                                    cardStackView.swipe();
                                })
                                .addOnFailureListener(e -> Log.e("Firestore", "Error adding job to Saved", e));
                    }
                }
                // Check if there are no more cards left
                if (manager.getTopPosition() <= 0) {
                    // Disable the apply and save buttons
                    reject.setAlpha(0.5f);
                    apply.setAlpha(0.5f);
                    save.setAlpha(0.5f);
                    reject.setEnabled(false);
                    apply.setEnabled(false);
                    save.setEnabled(false);
                } else {
                    // Enable the buttons if there are still cards available
                    reject.setAlpha(0.5f);
                    apply.setAlpha(0.5f);
                    save.setAlpha(0.5f);
                    reject.setEnabled(false);
                    apply.setEnabled(true);
                    save.setEnabled(true);
                }
                if (!isLoading && jobCards.size() - manager.getTopPosition() <= 3) {
                    fetchJobs(); // Fetch more jobs when close to the end
                }

            }

            @Override
            public void onCardDragging(Direction direction, float ratio) {
                // Handle card drag events
            }

            @Override
            public void onCardRewound() {
                // Handle rewind action if you add it
            }

            @Override
            public void onCardCanceled() {
                // Handle swipe cancellation
            }

            @Override
            public void onCardAppeared(View view, int position) {
                // Handle appearance of the card
            }

            @Override
            public void onCardDisappeared(View view, int position) {
                // Handle disappearance of the card
            }
        });
        if (manager.getTopPosition() >= 0) {
            // Disable the apply and save buttons
            reject.setAlpha(1.0f);
            apply.setAlpha(1.0f);
            save.setAlpha(1.0f);
            reject.setEnabled(true);
            apply.setEnabled(true);
            save.setEnabled(true);
        }

        // Customize the manager if needed
        manager.setStackFrom(StackFrom.Top);  // Cards stack from the top
        manager.setVisibleCount(3);           // Number of visible cards
        manager.setTranslationInterval(8.0f); // Space between each card

        // Set the manager and adapter to the CardStackView
        cardStackView.setLayoutManager(manager);

        reject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cardStackView.swipe();
            }
        });
        apply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = manager.getTopPosition() - 1;

                if (position >= 0 && position < jobCards.size()) {
                    String jobId = jobCards.get(position).getId();

                    // Update the job ID to the "Ongoing" array in Firestore
                    FirebaseFirestore db = FirebaseFirestore.getInstance();
                    String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();

                    db.collection("users")
                            .document(userId)
                            .update("jobApplication.Ongoing", FieldValue.arrayUnion(jobId))
                            .addOnSuccessListener(aVoid -> {
                                Log.d("Firestore", "Job added to Ongoing");
                                cardStackView.swipe();
                            })
                            .addOnFailureListener(e -> Log.e("Firestore", "Error adding job to Ongoing", e));
                    db.collection("job_posts")
                            .document(jobId)
                            .update("applicants", FieldValue.arrayUnion(userId))
                            .addOnSuccessListener(aVoid -> Log.d("Firestore", "User Id added to the applicants"))
                            .addOnFailureListener(e -> Log.e("Firestore", "Error adding user id to the applicants", e));
                } else {
                    Log.e("Job", "Invalid position: " + position + ". Job cards size: " + jobCards.size());
                    // Handle the error, e.g., show a toast or a message to the user
                }
            }
        });
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int position = manager.getTopPosition() - 1;
                if (position >= 0 && position < jobCards.size()) {
                    String jobId = jobCards.get(position).getId();

                    // Update the job ID to the "Ongoing" array in Firestore
                    FirebaseFirestore db = FirebaseFirestore.getInstance();
                    String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();

                    db.collection("users")
                            .document(userId)
                            .update("savedJob", FieldValue.arrayUnion(jobId))
                            .addOnSuccessListener(aVoid -> {
                                Log.d("Firestore", "Job added to Saved");
                                cardStackView.swipe();
                            })
                            .addOnFailureListener(e -> Log.e("Firestore", "Error adding job to Saved", e));
                } else {
                    Log.e("Job", "Invalid position: " + position + ". Job cards size: " + jobCards.size());
                    // Handle the error, e.g., show a toast or a message to the user
                }

            }
        });

        // Initial fetch of jobs
        fetchJobs();

        return view;
    }

    private void fetchJobs() {
        isLoading = true;

        FirebaseFirestore db = FirebaseFirestore.getInstance();

        Query query = db.collection("job_posts")
                .orderBy("postedDate", Query.Direction.DESCENDING)
                .limit(10);

        // Start query after the last visible document if pagination is already active
        if (lastVisible != null) {
            query = query.startAfter(lastVisible);
        }

        query.get().addOnCompleteListener(task -> {
            if (task.isSuccessful() && task.getResult() != null) {
                List<DocumentSnapshot> documents = task.getResult().getDocuments();
                if (!documents.isEmpty()) {
                    lastVisible = documents.get(documents.size() - 1);
                    for (DocumentSnapshot doc : documents) {
                        JobSwipeModel job = new JobSwipeModel();

                        job.setId(doc.getId());
                        job.setJobTitle(doc.getString("jobTitle"));
                        job.setJobDesc(doc.getString("jobDesc"));
                        job.setJobRequiredSkills((ArrayList<String>) doc.get("jobRequiredSkills"));
                        job.setJobType((ArrayList<String>) doc.get("jobType"));
                        job.setCompanyName(doc.getString("companyName"));
                        job.setCompanyLogo(doc.getString("companyLogo"));
                        job.setCompanyIndustry(doc.getString("companyIndustry"));
                        job.setCompanyLocation(doc.getString("companyLocation"));
                        job.setPostedDate(doc.getString("postedDate"));
                        job.setNumberApplicants(doc.getString("numberApplicants"));
                        jobCards.add(job);
                    }
                    jobCardAdapter.notifyDataSetChanged();
                }
            }
            isLoading = false;
        });
    }
}