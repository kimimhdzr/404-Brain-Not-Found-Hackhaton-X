package com.example.hackathon_x.DataBinding.Cache.Model;

import com.google.firebase.Timestamp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SignUpModel {
    private Map<String, Object> userInfo;
    private String currentJobPosition;
    private String joinDate;
    private Map<String, Object> userPreferences;

    public SignUpModel() {
        this.userInfo = new HashMap<>();
        this.userPreferences = new HashMap<>();
    }

    public SignUpModel(
            String userName,
            String userEmail,
            String userPhoneNum,
            String userRole,
            String userProfilePic,
            String userAddress,

            String currentJobPosition,
            String joinDate,

            String jobLocation,
            List<String> jobIndustry,
            String jobLevel,
            String jobSalary
    ) {
        this(); // Initialize the maps
        this.userInfo.put("userName", userName);
        this.userInfo.put("userEmail", userEmail);
        this.userInfo.put("userPhoneNum", userPhoneNum);
        this.userInfo.put("userRole", userRole);
        this.userInfo.put("userProfilePic", userProfilePic);
        this.userInfo.put("userAddress", userAddress);

        this.currentJobPosition = currentJobPosition;
        this.joinDate = joinDate;

        this.userPreferences.put("jobLocation", jobLocation);
        this.userPreferences.put("jobIndustry", jobIndustry);
        this.userPreferences.put("jobLevel", jobLevel);
        this.userPreferences.put("jobSalary", jobSalary);
    }


    public Map<String, Object> getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(Map<String, Object> userInfo) {
        this.userInfo = userInfo;
    }


    public void setuserName(String userName) {
        this.userInfo.put("userName", userName);
    }

    public void setuserEmail(String userEmail) {
        this.userInfo.put("userEmail", userEmail);
    }

    public void setuserPhoneNum(String userPhoneNum) {
        this.userInfo.put("userPhoneNum", userPhoneNum);
    }

    public void setuserRole(String userRole) {
        this.userInfo.put("userRole", userRole);
    }

    public void setuserProfilePic(String userProfilePic) {
        this.userInfo.put("userProfilePic", userProfilePic);
    }

    public void setuserAddress(String userAddress) {
        this.userInfo.put("userAddress", userAddress);
    }



    public void setJoinDate(String joinDate) {
        this.joinDate = joinDate;
    }
    public void setCurrentJobPosition(String currentJobPosition) {
        this.currentJobPosition = currentJobPosition;
    }

    public void setJobIndustry(List<String> jobIndustry) {
        this.userPreferences.put("jobIndustry", jobIndustry);
    }

    public void setJobLocation(String jobLocation) {
        this.userPreferences.put("jobLocation", jobLocation);
    }

    public void setJobLevel(String jobLevel) {
        this.userPreferences.put("jobLevel", jobLevel);
    }

    public void setJobSalary(String jobSalary) {
        this.userPreferences.put("jobSalary", jobSalary);
    }

    public String getCurrentJobPosition() {
        return currentJobPosition;
    }


    public String getJoinDate() {
        return joinDate;
    }


    public Map<String, Object> getUserPreferences() {
        return userPreferences;
    }



}
