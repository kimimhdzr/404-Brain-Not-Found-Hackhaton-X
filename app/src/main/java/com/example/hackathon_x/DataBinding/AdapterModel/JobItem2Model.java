package com.example.hackathon_x.DataBinding.AdapterModel;

import java.util.ArrayList;

public class JobItem2Model {

    private String companyName;
    private String companyLogo;
    private String jobTitle;
    private String jobDesc;
    private String jobType;
    private String postedDate;
    private String companyLocation;
    private String jobSalary;
    private ArrayList<String>  jobSkillsRequired;
    public JobItem2Model(
            String companyName,
            String companyLogo,
            String jobTitle,
            String jobDesc,
            String jobType,
            String postedDate,
            String companyLocation,
            String jobSalary,
            ArrayList<String> jobSkillsRequired
    ) {
        this.companyName = companyName;
        this.companyLogo = companyLogo;
        this.jobTitle = jobTitle;
        this.jobDesc = jobDesc;
        this.jobType = jobType;
        this.postedDate = postedDate;
        this.companyLocation = companyLocation;
        this.jobSalary = jobSalary;
        this.jobSkillsRequired = jobSkillsRequired;
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

    public String getJobType() {
        return jobType;
    }

    public void setJobType(String jobType) {
        this.jobType = jobType;
    }

    public String getPostedDate() {
        return postedDate;
    }

    public void setPostedDate(String postedDate) {
        this.postedDate = postedDate;
    }

    public String getCompanyLocation() {
        return companyLocation;
    }

    public void setCompanyLocation(String companyLocation) {
        this.companyLocation = companyLocation;
    }

    public String getJobSalary() {
        return jobSalary;
    }

    public void setJobSalary(String jobSalary) {
        this.jobSalary = jobSalary;
    }

    public ArrayList<String> getJobSkillsRequired() {
        return jobSkillsRequired;
    }

    public void setJobSkillsRequired(ArrayList<String> jobSkillsRequired) {
        this.jobSkillsRequired = jobSkillsRequired;
    }


}
