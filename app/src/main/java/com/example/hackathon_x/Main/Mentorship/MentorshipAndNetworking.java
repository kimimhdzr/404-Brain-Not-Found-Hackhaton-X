package com.example.hackathon_x.Main.Mentorship;

import android.os.Bundle;

import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.hackathon_x.DataBinding.AdapterModel.MentorItem1Model;
import com.example.hackathon_x.DataBinding.AdapterModel.MentorItem2Model;
import com.example.hackathon_x.DataBinding.Persistent.UserSharedPreference;
import com.example.hackathon_x.Main.Home.Adapter.JobProgresAdapter;
import com.example.hackathon_x.Main.Home.Adapter.RecommendedMentorshipAdapter;
import com.example.hackathon_x.Main.MainActivity;
import com.example.hackathon_x.Main.Mentorship.Adapter.UpcomingMentorAdapter;
import com.example.hackathon_x.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.chip.ChipGroup;
import com.google.android.material.imageview.ShapeableImageView;
import com.google.android.material.slider.Slider;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MentorshipAndNetworking extends Fragment {

    private AppCompatImageButton savedJob, filter;
    EditText search_input;
    RecyclerView recyclerView1, recyclerView2;
    ShapeableImageView profileImage;

    TextView name, jobposition;
    List<MentorItem1Model> UpcomingMentorshipList;
    List<MentorItem2Model> RecommendedMentorshipList;
    UpcomingMentorAdapter upcomingMentorAdapter;
    RecommendedMentorshipAdapter recommendedMentorshipAdapter;
    UserSharedPreference userSharedPreference;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_mentorship_and_networking, container, false);

        search_input = view.findViewById(R.id.search_input);
        filter = view.findViewById(R.id.filter);
        savedJob = view.findViewById(R.id.saved);


        NavController navController = NavHostFragment.findNavController(this);
        savedJob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.nav_to_savedmentor);
            }
        });


        name = view.findViewById(R.id.nametoolbar);
        jobposition = view.findViewById(R.id.jobtoolbar);
        profileImage = view.findViewById(R.id.profileImagetoolbar);
        userSharedPreference = new UserSharedPreference(getContext());
        if (userSharedPreference.getProfilePic() != null) {
            Glide.with(this)
                    .load(userSharedPreference.getProfilePic())
                    .into(profileImage); // Replace with your ImageView reference
        }
        String welcome = "Hello " + userSharedPreference.getUserName();
        name.setText(welcome);


        //Setup RecyclerView 1
        ArrayList<String> jobIndustry = new ArrayList<>(Arrays.asList("IT", "Software"));
        ArrayList<String> jobSkills = new ArrayList<>(Arrays.asList("Java", "Python", "Diddy"));
        RecommendedMentorshipList = new ArrayList<>(Arrays.asList(
                new MentorItem2Model(
                        "Alice Johnson",
                        "Available",
                        "Experienced software engineer with a passion for mentoring aspiring developers.",
                        "Senior Software Engineer",
                        "San Francisco, CA",
                        "https://dummyimage.com/100x100/000/fff&text=AJ",
                        new ArrayList<>(Arrays.asList("IT", "Software Development")),
                        new ArrayList<>(Arrays.asList("Java", "Python", "Agile Methodology"))
                ),
                new MentorItem2Model(
                        "Bob Smith",
                        "Busy",
                        "Data analyst and machine learning enthusiast with 10+ years of experience.",
                        "Data Scientist",
                        "New York, NY",
                        "https://dummyimage.com/100x100/000/fff&text=BS",
                        new ArrayList<>(Arrays.asList("Data Science", "Analytics")),
                        new ArrayList<>(Arrays.asList("Python", "R", "SQL"))
                ),
                new MentorItem2Model(
                        "Carla Roberts",
                        "Available",
                        "UX designer focused on creating user-centered designs and experiences.",
                        "Lead UX Designer",
                        "Austin, TX",
                        "https://dummyimage.com/100x100/000/fff&text=CR",
                        new ArrayList<>(Arrays.asList("Design", "IT")),
                        new ArrayList<>(Arrays.asList("Adobe XD", "Figma", "User Research"))
                ),
                new MentorItem2Model(
                        "David Lin",
                        "Available",
                        "Cybersecurity expert passionate about helping others learn secure coding practices.",
                        "Cybersecurity Specialist",
                        "Seattle, WA",
                        "https://dummyimage.com/100x100/000/fff&text=DL",
                        new ArrayList<>(Arrays.asList("Cybersecurity", "IT")),
                        new ArrayList<>(Arrays.asList("Network Security", "Python", "Penetration Testing"))
                ),
                new MentorItem2Model(
                        "Emma Watson",
                        "On Break",
                        "Cloud architect with expertise in AWS and Google Cloud solutions.",
                        "Cloud Architect",
                        "Boston, MA",
                        "https://dummyimage.com/100x100/000/fff&text=EW",
                        new ArrayList<>(Arrays.asList("Cloud Computing", "Software")),
                        new ArrayList<>(Arrays.asList("AWS", "Google Cloud", "DevOps"))
                )
        ));

        recyclerView1 = view.findViewById(R.id.recyclerview1);

        recommendedMentorshipAdapter = new RecommendedMentorshipAdapter(
                getContext(),
                RecommendedMentorshipList
        );
        GridLayoutManager gridLayoutManager3 = new GridLayoutManager(getContext(), 5, GridLayoutManager.HORIZONTAL, false);
        recyclerView1.setLayoutManager(gridLayoutManager3);
        recyclerView1.setItemAnimator(new DefaultItemAnimator()); // add animation to the cards
        recyclerView1.setAdapter(recommendedMentorshipAdapter);


        //Setup RecyclerView 2
        // Setup RecyclerView 2 with dummy data for UpcomingMentorshipList
        UpcomingMentorshipList = new ArrayList<>(Arrays.asList(
                new MentorItem1Model(
                        "Alice Johnson",
                        "Software Engineer",
                        "Active",
                        "https://dummyimage.com/100x100/000/fff&text=Alice",
                        "15 Nov 2024",
                        "10:00 AM"
                ),
                new MentorItem1Model(
                        "Bob Smith",
                        "Data Scientist",
                        "Pending",
                        "https://dummyimage.com/100x100/000/fff&text=Bob",
                        "20 Nov 2024",
                        "2:00 PM"
                ),
                new MentorItem1Model(
                        "Carol White",
                        "Product Manager",
                        "Confirmed",
                        "https://dummyimage.com/100x100/000/fff&text=Carol",
                        "22 Nov 2024",
                        "4:00 PM"
                ),
                new MentorItem1Model(
                        "David Brown",
                        "UX Designer",
                        "Cancelled",
                        "https://dummyimage.com/100x100/000/fff&text=David",
                        "25 Nov 2024",
                        "11:00 AM"
                ),
                new MentorItem1Model(
                        "Eve Adams",
                        "System Architect",
                        "Active",
                        "https://dummyimage.com/100x100/000/fff&text=Eve",
                        "28 Nov 2024",
                        "9:00 AM"
                )
        ));


        recyclerView2 = view.findViewById(R.id.recyclerview2);
        upcomingMentorAdapter = new UpcomingMentorAdapter(
                getContext(),
                UpcomingMentorshipList
        );

        // Set GridLayoutManager with horizontal orientation and 2 items per column
        GridLayoutManager gridLayoutManager1 = new GridLayoutManager(getContext(), 5, GridLayoutManager.HORIZONTAL, false);
        recyclerView2.setLayoutManager(gridLayoutManager1);
        recyclerView2.setItemAnimator(new DefaultItemAnimator()); // add animation to the cards
        recyclerView2.setAdapter(upcomingMentorAdapter);


        filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create a BottomSheetDialog
                BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(v.getContext());

                // Inflate the layout for the bottom sheet
                View bottomSheetView = getLayoutInflater().inflate(R.layout.bottom_sheet_mentor, null);

                // Set the layout as the content view for the BottomSheetDialog
                bottomSheetDialog.setContentView(bottomSheetView);

                // Find views in the BottomSheetDialog, if you need to interact with them

                AppCompatButton resetbutton = bottomSheetView.findViewById(R.id.resetbutton);
                AppCompatButton applybutton = bottomSheetView.findViewById(R.id.applybutton);

                resetbutton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        bottomSheetDialog.dismiss();
                        navController.navigate(R.id.nav_to_mentorsearch);
                    }
                });
                applybutton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        bottomSheetDialog.dismiss();
                        navController.navigate(R.id.nav_to_mentorsearch);
                    }
                });

                // Show the BottomSheetDialog
                bottomSheetDialog.show();
            }
        });


        return view;
    }
}