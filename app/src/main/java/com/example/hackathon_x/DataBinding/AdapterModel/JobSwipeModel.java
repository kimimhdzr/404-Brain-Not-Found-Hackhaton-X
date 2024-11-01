package com.example.hackathon_x.DataBinding.AdapterModel;

import java.util.ArrayList;

public class JobSwipeModel {

    private String id;
    private String jobTitle;
    private String jobDesc;
    private ArrayList<String> jobRequiredSkills;
    private ArrayList<String> jobType;
    private String companyName;
    private String companyLogo;
    private String companyIndustry;
    private String companyLocation;
    private String postedDate;
    private String numberApplicants;

    public JobSwipeModel() {
    }

    public JobSwipeModel(
            String id, String jobTitle, String jobDesc,
            ArrayList<String> jobRequiredSkills, ArrayList<String> jobType,
            String companyName, String companyLogo, String companyIndustry, String companyLocation,
            String postedDate, String numberApplicants
    ) {
        this.id = id;
        this.jobTitle = jobTitle;
        this.jobDesc = jobDesc;
        this.jobRequiredSkills = jobRequiredSkills;
        this.jobType = jobType;
        this.companyName = companyName;
        this.companyLogo = companyLogo;
        this.companyIndustry = companyIndustry;
        this.companyLocation = companyLocation;
        this.postedDate = postedDate;
        this.numberApplicants = numberApplicants;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getJobDesc() {
        return jobDesc;
    }

    public void setJobDesc(String jobDesc) {
        this.jobDesc = jobDesc;
    }

    public ArrayList<String> getJobRequiredSkills() {
        return jobRequiredSkills;
    }

    public void setJobRequiredSkills(ArrayList<String> jobRequiredSkills) {
        this.jobRequiredSkills = jobRequiredSkills;
    }

    public ArrayList<String> getJobType() {
        return jobType;
    }

    public void setJobType(ArrayList<String> jobType) {
        this.jobType = jobType;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyLogo() {
        return companyLogo;
    }

    public void setCompanyLogo(String companyLogo) {
        this.companyLogo = companyLogo;
    }

    public String getCompanyIndustry() {
        return companyIndustry;
    }

    public void setCompanyIndustry(String companyIndustry) {
        this.companyIndustry = companyIndustry;
    }

    public String getCompanyLocation() {
        return companyLocation;
    }

    public void setCompanyLocation(String companyLocation) {
        this.companyLocation = companyLocation;
    }

    public String getPostedDate() {
        return postedDate;
    }

    public void setPostedDate(String postedDate) {
        this.postedDate = postedDate;
    }

    public String getNumberApplicants() {
        return numberApplicants;
    }

    public void setNumberApplicants(String numberApplicants) {
        this.numberApplicants = numberApplicants;
    }
}
