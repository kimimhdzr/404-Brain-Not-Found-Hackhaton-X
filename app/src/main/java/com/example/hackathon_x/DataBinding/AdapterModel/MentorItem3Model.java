package com.example.hackathon_x.DataBinding.AdapterModel;

import java.util.ArrayList;

public class MentorItem3Model {
    private String mentorName;
    private String mentorStatus;
    private String mentorBio;
    private String jobName;
    private String location;
    private String profileImage;
    private ArrayList<String> jobIndustry;
    private ArrayList<String> jobSkills;

    public String getMentorId() {
        return mentorId;
    }

    public void setMentorId(String mentorId) {
        this.mentorId = mentorId;
    }

    private String mentorId;

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    private String companyName;

    public MentorItem3Model() {

    }

    public MentorItem3Model(
            String mentorId,
            String mentorName,
            String mentorStatus,
            String mentorBio,
            String jobName,
            String location,
            String profileImage,
            ArrayList<String> jobIndustry,
            ArrayList<String> jobSkills,
            String companyName
    ) {
        this.mentorId = mentorId;
        this.mentorName = mentorName;
        this.mentorStatus = mentorStatus;
        this.mentorBio = mentorBio;
        this.jobName = jobName;
        this.location = location;
        this.profileImage = profileImage;
        this.jobIndustry = jobIndustry;
        this.jobSkills = jobSkills;
        this.companyName = companyName;
    }


    public String getMentorName() {
        return mentorName;
    }

    public void setMentorName(String mentorName) {
        this.mentorName = mentorName;
    }

    public String getMentorStatus() {
        return mentorStatus;
    }

    public void setMentorStatus(String mentorStatus) {
        this.mentorStatus = mentorStatus;
    }

    public String getMentorBio() {
        return mentorBio;
    }

    public void setMentorBio(String mentorBio) {
        this.mentorBio = mentorBio;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }

    public ArrayList<String> getJobIndustry() {
        return jobIndustry;
    }

    public void setJobIndustry(ArrayList<String> jobIndustry) {
        this.jobIndustry = jobIndustry;
    }

    public ArrayList<String> getJobSkills() {
        return jobSkills;
    }

    public void setJobSkills(ArrayList<String> jobSkills) {
        this.jobSkills = jobSkills;
    }

}
