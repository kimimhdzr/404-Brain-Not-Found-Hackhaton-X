package com.example.hackathon_x.Main.Mentorship.Services;

import android.content.Context;

import com.example.hackathon_x.DataBinding.AdapterModel.MentorItem3Model;
import com.example.hackathon_x.DataBinding.Persistent.UserSharedPreference;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class UserSearchService {

    private FirebaseFirestore db;
    UserSharedPreference userProfile;

    public UserSearchService() {
        // Initialize Firestore
        db = FirebaseFirestore.getInstance();
    }

    public List<MentorItem3Model> userSearch(Context context, String mentorName, String companyName, String jobIndustry) {
        List<MentorItem3Model> mentorList = new ArrayList<>();

        userProfile = new UserSharedPreference(context);


        Query query = db.collection("job_posts")
                .orderBy("postedDate", Query.Direction.DESCENDING)
                .limit(10);
        // Execute separate queries for each field to achieve OR logic
        if (mentorName != null && !mentorName.isEmpty()) {
            query = query.whereEqualTo("mentorName", mentorName);
        }
        else if (companyName != null && !companyName.isEmpty()) {
            query = query.whereEqualTo("companyName", companyName);
        } else if (jobIndustry != null && !jobIndustry.isEmpty()) {
            query = query.whereArrayContains("jobIndustry", jobIndustry);
        }


        query.get().addOnCompleteListener(task -> {
            if (task.isSuccessful() && task.getResult() != null) {
                List<DocumentSnapshot> documents = task.getResult().getDocuments();
                if (!documents.isEmpty()) {
                    for (DocumentSnapshot doc : documents) {
                        MentorItem3Model mentor = new MentorItem3Model();


                        mentor.setMentorId(doc.getString("mentorId"));
                        mentor.setMentorName(doc.getString("mentorName"));
                        mentor.setMentorStatus(doc.getString("mentorStatus"));
                        mentor.setMentorBio(doc.getString("mentorBio"));
                        mentor.setJobName(doc.getString("jobName"));
                        mentor.setLocation(doc.getString("location"));
                        mentor.setProfileImage(doc.getString("profileImage"));

                        ArrayList<String> jobIndustryDoc = (ArrayList<String>) doc.get("jobIndustry");
                        mentor.setJobIndustry(jobIndustryDoc != null ? jobIndustryDoc : new ArrayList<>());

                        ArrayList<String> jobSkillsDoc = (ArrayList<String>) doc.get("jobSkills");
                        mentor.setJobSkills(jobSkillsDoc != null ? jobSkillsDoc : new ArrayList<>());

                        mentor.setCompanyName(doc.getString("companyName"));


                        // Avoid duplicates by checking jobList
                        if (!mentorList.contains(mentor)) {
                            mentorList.add(mentor);
                        }
                    }
                }
            }
        });

        return mentorList;
    }


    public void userFilterSearch(Context context, String mentorLocation, String yoe, String jobIndustry, String jobLevel, MentorSearchService.FirestoreCallback callback) {
        List<MentorItem3Model> mentorList = new ArrayList<>();
        List<Task<QuerySnapshot>> tasks = new ArrayList<>();

        // Execute separate queries for each field to achieve OR logic
        if (mentorLocation != null && !mentorLocation.isEmpty()) {
            tasks.add(db.collection("mentors")
                    .whereEqualTo("state", mentorLocation)
                    .orderBy("postedDate", Query.Direction.DESCENDING)
                    .limit(7).get());
        }
        if (yoe != null && !yoe.isEmpty()) {
            tasks.add(db.collection("mentors")
                    .whereEqualTo("yoe", yoe)
                    .orderBy("postedDate", Query.Direction.DESCENDING)
                    .limit(7).get());
        }
        if (jobIndustry != null && !jobIndustry.isEmpty()) {
            tasks.add(db.collection("mentors")
                    .whereArrayContains("jobIndustry", jobIndustry)
                    .orderBy("postedDate", Query.Direction.DESCENDING)
                    .limit(7).get());
        }
        if (jobLevel != null && !jobLevel.isEmpty()) {
            tasks.add(db.collection("mentors")
                    .whereEqualTo("jobLevel", jobLevel)
                    .orderBy("postedDate", Query.Direction.DESCENDING)
                    .limit(7).get());
        }

        // Execute all queries in parallel and process results once they all complete
        Tasks.whenAllComplete(tasks).addOnCompleteListener(task -> {
            for (Task<QuerySnapshot> individualTask : tasks) {
                if (individualTask.isSuccessful() && individualTask.getResult() != null) {
                    for (DocumentSnapshot doc : individualTask.getResult().getDocuments()) {
                        MentorItem3Model mentor = new MentorItem3Model();


                        mentor.setMentorId(doc.getString("mentorId"));
                        mentor.setMentorName(doc.getString("mentorName"));
                        mentor.setMentorStatus(doc.getString("mentorStatus"));
                        mentor.setMentorBio(doc.getString("mentorBio"));
                        mentor.setJobName(doc.getString("jobName"));
                        mentor.setLocation(doc.getString("location"));
                        mentor.setProfileImage(doc.getString("profileImage"));

                        ArrayList<String> jobIndustryDoc = (ArrayList<String>) doc.get("jobIndustry");
                        mentor.setJobIndustry(jobIndustryDoc != null ? jobIndustryDoc : new ArrayList<>());

                        ArrayList<String> jobSkillsDoc = (ArrayList<String>) doc.get("jobSkills");
                        mentor.setJobSkills(jobSkillsDoc != null ? jobSkillsDoc : new ArrayList<>());

                        mentor.setCompanyName(doc.getString("companyName"));

                        // Avoid duplicates by checking jobList
                        if (!mentorList.contains(mentor)) {
                            mentorList.add(mentor);
                        }
                    }
                }
            }
            // Return the aggregated results through callback
            callback.onCallback(mentorList);
        });
    }

    // Callback interface to handle async results
    public interface FirestoreCallback {
        void onCallback(List<MentorItem3Model> mentorList);
    }


    public List<MentorItem3Model> userSaved(Context context, String uid) {
        List<MentorItem3Model> mentorList = new ArrayList<>();


        return mentorList;
    }

    public List<MentorItem3Model> userSavedSearch(Context context, String mentorName, String companyName, String jobIndustry) {
        List<MentorItem3Model> mentorList = new ArrayList<>();


        return mentorList;
    }
}
