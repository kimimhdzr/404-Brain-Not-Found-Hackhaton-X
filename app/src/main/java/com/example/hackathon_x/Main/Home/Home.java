package com.example.hackathon_x.Main.Home;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.anychart.AnyChart;
import com.anychart.AnyChartView;
import com.anychart.chart.common.dataentry.DataEntry;
import com.anychart.chart.common.dataentry.ValueDataEntry;
import com.anychart.charts.Pie;
import com.bumptech.glide.Glide;
import com.example.hackathon_x.DataBinding.AdapterModel.JobItem1Model;
import com.example.hackathon_x.DataBinding.AdapterModel.JobItem2Model;
import com.example.hackathon_x.DataBinding.AdapterModel.MentorItem2Model;
import com.example.hackathon_x.DataBinding.Persistent.UserSharedPreference;
import com.example.hackathon_x.Main.Home.Adapter.JobProgresAdapter;
import com.example.hackathon_x.Main.Home.Adapter.JobRecommendAdapter;
import com.example.hackathon_x.Main.Home.Adapter.LearningRecommendAdapter;
import com.example.hackathon_x.Main.Home.Adapter.RecommendedMentorshipAdapter;
import com.example.hackathon_x.R;
import com.google.android.material.imageview.ShapeableImageView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Home extends Fragment {
    ShapeableImageView profileImagetoolbar;
    TextView nametoolbar, jobtoolbar;
    TextView seemore1, seemore2, seemore3, seemore4;
    RecyclerView recyclerview1, recyclerview2, recyclerview3, recyclerview4;
    AnyChartView pieChart;
    JobProgresAdapter jobProgresAdapter;
    JobRecommendAdapter jobRecommendAdapter;
    LearningRecommendAdapter learningRecommendAdapter;
    RecommendedMentorshipAdapter recommendedMentorshipAdapter;
    List<JobItem1Model> JobProgressList;
    List<JobItem2Model> RecommendedJobList;
    List<String> LearningRecommendationList;
    List<MentorItem2Model> RecommendedMentorshipList;
    UserSharedPreference userSharedPreference;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        profileImagetoolbar = view.findViewById(R.id.profileImagetoolbar);
        nametoolbar = view.findViewById(R.id.nametoolbar);
        jobtoolbar = view.findViewById(R.id.jobtoolbar);

        seemore1 = view.findViewById(R.id.seemore1);
        seemore2 = view.findViewById(R.id.seemore2);
        seemore3 = view.findViewById(R.id.seemore3);
        seemore4 = view.findViewById(R.id.seemore4);

        userSharedPreference = new UserSharedPreference(getContext());
        if (userSharedPreference.getProfilePic() != null) {
            Glide.with(this)
                    .load(userSharedPreference.getProfilePic())
                    .into(profileImagetoolbar); // Replace with your ImageView reference
        }
        String welcome = "Hello " + userSharedPreference.getUserName();
        nametoolbar.setText(welcome);

        JobProgressList = new ArrayList<>(Arrays.asList(
                new JobItem1Model("Google", "https://dummyimage.com/100x100/000/fff&text=G", "Software Engineer"),
                new JobItem1Model("Amazon", "https://dummyimage.com/100x100/000/fff&text=A", "Data Scientist"),
                new JobItem1Model("Microsoft", "https://dummyimage.com/100x100/000/fff&text=M", "Cloud Engineer"),
                new JobItem1Model("Apple", "https://dummyimage.com/100x100/000/fff&text=A", "Product Designer"),
                new JobItem1Model("Meta", "https://dummyimage.com/100x100/000/fff&text=M", "UI/UX Designer")
        ));

        ArrayList<String> skills = new ArrayList<>(Arrays.asList("Java", "Python", "Project Management"));
        RecommendedJobList = new ArrayList<>(Arrays.asList(
                new JobItem2Model(
                        "Google",
                        "https://dummyimage.com/100x100/000/fff&text=G",
                        "Software Engineer",
                        "Develop and maintain scalable software solutions.",
                        "Full-time",
                        "20 Oct 2024",
                        "Mountain View, CA",
                        "RM6000 - RM8000",
                        skills
                ),
                new JobItem2Model(
                        "Amazon",
                        "https://dummyimage.com/100x100/000/fff&text=A",
                        "Data Analyst",
                        "Analyze and interpret complex data to drive business decisions.",
                        "Part-time",
                        "15 Oct 2024",
                        "Seattle, WA",
                        "RM4000 - RM6000",
                        new ArrayList<>(Arrays.asList("SQL", "Python", "Data Visualization"))
                ),
                new JobItem2Model(
                        "Microsoft",
                        "https://dummyimage.com/100x100/000/fff&text=M",
                        "Cloud Engineer",
                        "Manage and optimize cloud-based infrastructure.",
                        "Contract",
                        "10 Oct 2024",
                        "Redmond, WA",
                        "RM7000 - RM9000",
                        new ArrayList<>(Arrays.asList("Azure", "Networking", "Scripting"))
                ),
                new JobItem2Model(
                        "Apple",
                        "https://dummyimage.com/100x100/000/fff&text=A",
                        "Product Designer",
                        "Design and develop user-centered products.",
                        "Full-time",
                        "5 Oct 2024",
                        "Cupertino, CA",
                        "RM8000 - RM10000",
                        new ArrayList<>(Arrays.asList("UI/UX Design", "Prototyping", "Adobe Suite"))
                ),
                new JobItem2Model(
                        "Meta",
                        "https://dummyimage.com/100x100/000/fff&text=M",
                        "UI/UX Designer",
                        "Create intuitive interfaces and improve user experiences.",
                        "Internship",
                        "1 Oct 2024",
                        "Menlo Park, CA",
                        "RM2000 - RM3000",
                        new ArrayList<>(Arrays.asList("Figma", "User Research", "Wireframing"))
                )
        ));

        LearningRecommendationList = new ArrayList<>();

        ArrayList<String> jobIndustry = new ArrayList<>(Arrays.asList("IT", "Software", "Data Science"));
        ArrayList<String> jobSkills = new ArrayList<>(Arrays.asList("Java", "Python", "Project Management"));

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


        // Setup RecyclerView 1
        recyclerview1 = view.findViewById(R.id.recyclerview1);
        jobProgresAdapter = new JobProgresAdapter(
                getContext(),
                JobProgressList
        );

        // Set GridLayoutManager with horizontal orientation and 2 items per column
        GridLayoutManager gridLayoutManager1 = new GridLayoutManager(getContext(), 5, GridLayoutManager.HORIZONTAL, false);
        recyclerview1.setLayoutManager(gridLayoutManager1);
        recyclerview1.setItemAnimator(new DefaultItemAnimator()); // add animation to the cards
        recyclerview1.setAdapter(jobProgresAdapter);


        //setup recycler view 2
        recyclerview2 = view.findViewById(R.id.recyclerview2);
        jobRecommendAdapter = new JobRecommendAdapter(
                getContext(),
                RecommendedJobList
        );
        GridLayoutManager gridLayoutManager2 = new GridLayoutManager(getContext(), 4, GridLayoutManager.HORIZONTAL, false);
        recyclerview2.setLayoutManager(gridLayoutManager2);
        recyclerview2.setItemAnimator(new DefaultItemAnimator()); // add animation to the cards
        recyclerview2.setAdapter(jobRecommendAdapter);

        //setup recycler view 3
        recyclerview3 = view.findViewById(R.id.recyclerview3);


        //setup recycler view 4
        recyclerview4 = view.findViewById(R.id.recyclerview4);
        recommendedMentorshipAdapter = new RecommendedMentorshipAdapter(
                getContext(),
                RecommendedMentorshipList
        );
        GridLayoutManager gridLayoutManager3 = new GridLayoutManager(getContext(), 4, GridLayoutManager.HORIZONTAL, false);
        recyclerview4.setLayoutManager(gridLayoutManager3);
        recyclerview4.setItemAnimator(new DefaultItemAnimator()); // add animation to the cards
        recyclerview4.setAdapter(recommendedMentorshipAdapter);

        pieChart = view.findViewById(R.id.piechart);

        NavController navController = NavHostFragment.findNavController(this);

//        seemore1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                navController.navigate(R.id.nav_to_job);
//            }
//        });
        seemore2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.nav_to_job);
            }
        });
        seemore3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.nav_to_upskills);
            }
        });
        seemore4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.nav_to_mentorship);
            }
        });


        setupPieChart();
        return view;
    }


    private void setupPieChart() {
        Pie pie = AnyChart.pie();

        // Sample data for the pie chart
        List<DataEntry> dataEntries = new ArrayList<>();
        dataEntries.add(new ValueDataEntry("Ongoing", 20));
        dataEntries.add(new ValueDataEntry("Rejected", 40));
        dataEntries.add(new ValueDataEntry("Accepted", 10));

        pie.data(dataEntries);

        TypedValue typedValue = new TypedValue();
        if (getContext().getTheme().resolveAttribute(com.google.android.material.R.attr.backgroundColor, typedValue, true)) {
            int backgroundColor = typedValue.data;
            pieChart.setBackgroundColor(backgroundColor);
        }
        // Optional: Customize pie chart appearance
        pie.labels().position("outside");
        pie.legend().title().enabled(true);
        pie.legend().title().text("Categories").padding(0d, 0d, 10d, 0d);
        pie.legend().position("center-bottom").itemsLayout("horizontal").align("center");

        pieChart.setChart(pie);
    }


}