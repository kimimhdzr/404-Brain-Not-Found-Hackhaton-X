package com.example.hackathon_x.Main.Job.Services;

import android.content.Context;

import com.example.hackathon_x.DataBinding.AdapterModel.JobItem3Model;
import com.example.hackathon_x.DataBinding.AdapterModel.JobSwipeModel;
import com.example.hackathon_x.DataBinding.Persistent.UserSharedPreference;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JobSearchService {

    private FirebaseFirestore db;
    UserSharedPreference userProfile;

    public JobSearchService() {
        // Initialize Firestore
        db = FirebaseFirestore.getInstance();
    }

    public List<JobItem3Model> jobSearch(Context context, String jobName, String companyName, String jobIndustry) {
        List<JobItem3Model> jobList = new ArrayList<>();

        userProfile = new UserSharedPreference(context);


        Query query = db.collection("job_posts")
                .orderBy("postedDate", Query.Direction.DESCENDING)
                .limit(10);
        // Execute separate queries for each field to achieve OR logic
        if (jobName != null && !jobName.isEmpty()) {
            query = query.whereEqualTo("jobTitle", jobName);
        }
        else if (companyName != null && !companyName.isEmpty()) {
            query = query.whereEqualTo("companyName", companyName);
        }
        else if (jobIndustry != null && !jobIndustry.isEmpty()) {
            query = query.whereArrayContains("jobIndustry", jobIndustry);
        }


        query.get().addOnCompleteListener(task -> {
            if (task.isSuccessful() && task.getResult() != null) {
                List<DocumentSnapshot> documents = task.getResult().getDocuments();
                if (!documents.isEmpty()) {
                    for (DocumentSnapshot doc : documents) {
                        JobItem3Model job = new JobItem3Model();

                        job.setJobId(doc.getId());
                        job.setCompanyName(doc.getString("companyName"));
                        job.setCompanyLogo(doc.getString("companyLogo"));
                        job.setJobTitle(doc.getString("jobTitle"));
                        job.setJobDesc(doc.getString("jobDesc"));

                        ArrayList<String> jobTypeListDoc = (ArrayList<String>) doc.get("jobType");
                        job.setJobType(jobTypeListDoc != null && !jobTypeListDoc.isEmpty() ? jobTypeListDoc.get(0) : "");

                        job.setPostedDate(doc.getString("postedDate"));
                        job.setCompanyLocation(doc.getString("companyLocation"));
                        job.setJobSalary(doc.getString("jobSalary"));

                        ArrayList<String> jobSkillsRequiredDoc = (ArrayList<String>) doc.get("jobSkillsRequired");
                        job.setJobSkillsRequired(jobSkillsRequiredDoc != null ? jobSkillsRequiredDoc : new ArrayList<>());


                        ArrayList<String> jobIndustryDoc = (ArrayList<String>) doc.get("companyIndustry");
                        job.setJobIndustry(jobIndustryDoc != null ? jobIndustryDoc : new ArrayList<>());

                        // Avoid duplicates by checking jobList
                        if (!jobList.contains(job)) {
                            jobList.add(job);
                        }
                    }
                }
            }
        });

        return jobList;
    }


    public void jobFilterSearch(Context context, String jobLocation, String salary, String jobIndustry, String jobLevel, FirestoreCallback callback) {
        List<JobItem3Model> jobList = new ArrayList<>();
        List<Task<QuerySnapshot>> tasks = new ArrayList<>();

        // Execute separate queries for each field to achieve OR logic
        if (jobLocation != null && !jobLocation.isEmpty()) {
            tasks.add(db.collection("job_posts")
                    .whereEqualTo("state", jobLocation)
                    .orderBy("postedDate", Query.Direction.DESCENDING)
                    .limit(7).get());
        }
        if (salary != null && !salary.isEmpty()) {
            tasks.add(db.collection("job_posts")
                    .whereEqualTo("jobSalary", salary)
                    .orderBy("postedDate", Query.Direction.DESCENDING)
                    .limit(7).get());
        }
        if (jobIndustry != null && !jobIndustry.isEmpty()) {
            tasks.add(db.collection("job_posts")
                    .whereArrayContains("jobIndustry", jobIndustry)
                    .orderBy("postedDate", Query.Direction.DESCENDING)
                    .limit(7).get());
        }
        if (jobLevel != null && !jobLevel.isEmpty()) {
            tasks.add(db.collection("job_posts")
                    .whereEqualTo("jobLevel", jobLevel)
                    .orderBy("postedDate", Query.Direction.DESCENDING)
                    .limit(7).get());
        }

        // Execute all queries in parallel and process results once they all complete
        Tasks.whenAllComplete(tasks).addOnCompleteListener(task -> {
            for (Task<QuerySnapshot> individualTask : tasks) {
                if (individualTask.isSuccessful() && individualTask.getResult() != null) {
                    for (DocumentSnapshot doc : individualTask.getResult().getDocuments()) {
                        JobItem3Model job = new JobItem3Model();

                        job.setJobId(doc.getId());
                        job.setCompanyName(doc.getString("companyName"));
                        job.setCompanyLogo(doc.getString("companyLogo"));
                        job.setJobTitle(doc.getString("jobTitle"));
                        job.setJobDesc(doc.getString("jobDesc"));

                        ArrayList<String> jobTypeListDoc = (ArrayList<String>) doc.get("jobType");
                        job.setJobType(jobTypeListDoc != null && !jobTypeListDoc.isEmpty() ? jobTypeListDoc.get(0) : "");

                        job.setPostedDate(doc.getString("postedDate"));
                        job.setCompanyLocation(doc.getString("companyLocation"));
                        job.setJobSalary(doc.getString("jobSalary"));

                        ArrayList<String> jobSkillsRequiredDoc = (ArrayList<String>) doc.get("jobSkillsRequired");
                        job.setJobSkillsRequired(jobSkillsRequiredDoc != null ? jobSkillsRequiredDoc : new ArrayList<>());

                        ArrayList<String> jobIndustryDoc = new ArrayList<>();
                        jobIndustryDoc.add(doc.getString("companyIndustry"));
                        job.setJobIndustry(jobIndustryDoc);

                        // Avoid duplicates by checking jobList
                        if (!jobList.contains(job)) {
                            jobList.add(job);
                        }
                    }
                }
            }
            // Return the aggregated results through callback
            callback.onCallback(jobList);
        });
    }

    // Callback interface to handle async results
    public interface FirestoreCallback {
        void onCallback(List<JobItem3Model> jobList);
    }


    public List<JobItem3Model> jobSaved(Context context, String uid) {
        List<JobItem3Model> jobList = new ArrayList<>();


        return jobList;
    }

    public List<JobItem3Model> jobSavedSearch(Context context, String jobName, String companyName, String jobIndustry) {
        List<JobItem3Model> jobList = new ArrayList<>();


        return jobList;
    }
}
