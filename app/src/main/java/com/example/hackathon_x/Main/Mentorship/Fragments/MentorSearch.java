package com.example.hackathon_x.Main.Mentorship.Fragments;

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
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.hackathon_x.DataBinding.AdapterModel.MentorItem2Model;
import com.example.hackathon_x.DataBinding.AdapterModel.MentorItem3Model;
import com.example.hackathon_x.DataBinding.Persistent.UserSharedPreference;
import com.example.hackathon_x.Main.Job.Adapter.JobSearchAdapter;
import com.example.hackathon_x.Main.MainActivity;
import com.example.hackathon_x.Main.Mentorship.Adapter.SearchAndSavedMentorAdapter;
import com.example.hackathon_x.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.imageview.ShapeableImageView;

import java.util.ArrayList;
import java.util.Arrays;

public class MentorSearch extends Fragment {
    private AppCompatImageButton savedJob;
    private ImageButton backbutton;
    private BottomNavigationView bottomNavigationView;
    ArrayList<MentorItem3Model> MentorList;
    SearchAndSavedMentorAdapter searchAndSavedMentorAdapter;
    RecyclerView recyclerView;
    ShapeableImageView profileImage;
    TextView name, jobposition;
    UserSharedPreference userSharedPreference;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_mentor_search, container, false);


        savedJob = view.findViewById(R.id.savedmentor);
        backbutton = view.findViewById(R.id.backbutton);


        bottomNavigationView = ((MainActivity) getActivity()).findViewById(R.id.bottomNavView);
        if (bottomNavigationView != null) {
            bottomNavigationView.setVisibility(View.GONE);
        }
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


        NavController navController = NavHostFragment.findNavController(this);
        savedJob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.nav_to_savedmentor);
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

        //Setup RecyclerView 1
        recyclerView = view.findViewById(R.id.recyclerview);
        // Setup MentorList with dummy data for MentorItem3Model
        ArrayList<String> jobIndustry1 = new ArrayList<>(Arrays.asList("IT", "Software Development"));
        ArrayList<String> jobIndustry2 = new ArrayList<>(Arrays.asList("Finance", "Data Science"));
        ArrayList<String> jobIndustry3 = new ArrayList<>(Arrays.asList("Marketing", "Design"));
        ArrayList<String> jobIndustry4 = new ArrayList<>(Arrays.asList("Healthcare", "Biotech"));
        ArrayList<String> jobIndustry5 = new ArrayList<>(Arrays.asList("Education", "E-learning"));

        ArrayList<String> jobSkills1 = new ArrayList<>(Arrays.asList("Java", "Python", "Spring Boot"));
        ArrayList<String> jobSkills2 = new ArrayList<>(Arrays.asList("SQL", "Data Analysis", "Machine Learning"));
        ArrayList<String> jobSkills3 = new ArrayList<>(Arrays.asList("Photoshop", "Social Media", "Branding"));
        ArrayList<String> jobSkills4 = new ArrayList<>(Arrays.asList("Public Health", "Research", "Clinical Trials"));
        ArrayList<String> jobSkills5 = new ArrayList<>(Arrays.asList("Instructional Design", "Curriculum Development", "E-learning Tools"));

        MentorList = new ArrayList<>(Arrays.asList(
                new MentorItem3Model(
                        "M101",
                        "Alice Johnson",
                        "Active",
                        "Experienced software developer with expertise in backend technologies.",
                        "Software Engineer",
                        "New York, NY",
                        "https://dummyimage.com/100x100/000/fff&text=AliceJ",
                        jobIndustry1,
                        jobSkills1,
                        "Tech Solutions Ltd"
                ),
                new MentorItem3Model(
                        "M102",
                        "Robert Smith",
                        "Available",
                        "Data analyst with a strong background in statistical analysis and machine learning.",
                        "Data Analyst",
                        "San Francisco, CA",
                        "https://dummyimage.com/100x100/000/fff&text=RobertS",
                        jobIndustry2,
                        jobSkills2,
                        "DataCorp"
                ),
                new MentorItem3Model(
                        "M103",
                        "Sophia Lee",
                        "Busy",
                        "Creative professional focused on marketing strategies and brand development.",
                        "Marketing Specialist",
                        "Austin, TX",
                        "https://dummyimage.com/100x100/000/fff&text=SophiaL",
                        jobIndustry3,
                        jobSkills3,
                        "Creative Minds"
                ),
                new MentorItem3Model(
                        "M104",
                        "Dr. Emily Chen",
                        "Active",
                        "Public health specialist with over 10 years of experience in clinical research.",
                        "Research Scientist",
                        "Boston, MA",
                        "https://dummyimage.com/100x100/000/fff&text=EmilyC",
                        jobIndustry4,
                        jobSkills4,
                        "Health Innovators"
                ),
                new MentorItem3Model(
                        "M105",
                        "James Turner",
                        "Available",
                        "E-learning expert with a passion for developing engaging online courses.",
                        "Instructional Designer",
                        "Seattle, WA",
                        "https://dummyimage.com/100x100/000/fff&text=JamesT",
                        jobIndustry5,
                        jobSkills5,
                        "EduTech Solutions"
                ),
                new MentorItem3Model(
                        "M106",
                        "Rachel Green",
                        "Available",
                        "Marketing professional with a decade of experience in digital content creation.",
                        "Content Marketing Manager",
                        "Los Angeles, CA",
                        "https://dummyimage.com/100x100/000/fff&text=RachelG",
                        jobIndustry3,
                        jobSkills3,
                        "Bright Ideas LLC"
                ),
                new MentorItem3Model(
                        "M107",
                        "Michael Brown",
                        "Busy",
                        "Financial analyst with expertise in investment strategies and portfolio management.",
                        "Investment Analyst",
                        "Chicago, IL",
                        "https://dummyimage.com/100x100/000/fff&text=MichaelB",
                        jobIndustry2,
                        jobSkills2,
                        "Money Matters Inc."
                ),
                new MentorItem3Model(
                        "M108",
                        "Dr. Susan Clark",
                        "Active",
                        "Biotech researcher focused on drug discovery and innovative healthcare solutions.",
                        "Biotech Scientist",
                        "San Diego, CA",
                        "https://dummyimage.com/100x100/000/fff&text=SusanC",
                        jobIndustry4,
                        jobSkills4,
                        "BioLabs Inc."
                ),
                new MentorItem3Model(
                        "M109",
                        "Peter Parker",
                        "Active",
                        "Software engineer specializing in web development and cloud architecture.",
                        "Frontend Developer",
                        "Denver, CO",
                        "https://dummyimage.com/100x100/000/fff&text=PeterP",
                        jobIndustry1,
                        jobSkills1,
                        "Web Solutions"
                ),
                new MentorItem3Model(
                        "M110",
                        "Lara Williams",
                        "Busy",
                        "Education consultant with experience in designing learning modules for schools.",
                        "Educational Consultant",
                        "Houston, TX",
                        "https://dummyimage.com/100x100/000/fff&text=LaraW",
                        jobIndustry5,
                        jobSkills5,
                        "Learning Hub"
                )
        ));



        searchAndSavedMentorAdapter = new SearchAndSavedMentorAdapter(
                getContext(),
                MentorList
        );
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setItemAnimator(new DefaultItemAnimator()); //add animation to the cards
        recyclerView.setAdapter(searchAndSavedMentorAdapter);







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