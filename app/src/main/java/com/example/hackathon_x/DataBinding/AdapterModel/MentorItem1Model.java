package com.example.hackathon_x.DataBinding.AdapterModel;

public class MentorItem1Model {

    private String mentorName;
    private String jobName;
    private String mentorstatus;
    private String profileImage;
    private String date;
    private String time;

    public MentorItem1Model(
            String mentorName,
            String jobName,
            String mentorstatus,
            String profileImage,
            String date,
            String time
    ) {
        this.mentorName = mentorName;
        this.jobName = jobName;
        this.mentorstatus = mentorstatus;
        this.profileImage = profileImage;
        this.date = date;
        this.time = time;
    }

    public String getMentorName() {
        return mentorName;
    }

    public void setMentorName(String mentorName) {
        this.mentorName = mentorName;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public String getMentorstatus() {
        return mentorstatus;
    }

    public void setMentorstatus(String mentorstatus) {
        this.mentorstatus = mentorstatus;
    }

    public String getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

}
