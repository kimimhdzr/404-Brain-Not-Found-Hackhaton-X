package com.example.hackathon_x.DataBinding.AdapterModel;

public class JobItem1Model {

    private String companyName;
    private String companyLogo;
    private String jobTitle;

    public JobItem1Model(String companyName, String companyLogo, String jobTitle) {
        this.companyName = companyName;
        this.companyLogo = companyLogo;
        this.jobTitle = jobTitle;
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
}
