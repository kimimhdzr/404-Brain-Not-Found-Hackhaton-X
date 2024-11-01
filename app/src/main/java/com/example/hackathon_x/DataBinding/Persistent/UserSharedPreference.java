package com.example.hackathon_x.DataBinding.Persistent;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import com.example.hackathon_x.DataBinding.Cache.Model.SignUpModel;

import java.util.Arrays;
import java.util.List;

public class UserSharedPreference {
    private static final String PREF_NAME = "UserProfile";

    // Keys for storing user data in SharedPreferences
    private static final String KEY_NAME = "userName";
    private static final String KEY_EMAIL = "userEmail";
    private static final String KEY_PHONE = "userPhoneNum";
    private static final String KEY_ADDRESS = "userrAddress";
    private static final String KEY_JOIN_DATE = "joinDate";
    private static final String KEY_ROLE = "userRole";
    private static final String KEY_PROFILE_PIC = "userProfilePic";
    private static final String KEY_CURRENT_JOB_POSITION = "currentJobPosition";
    private static final String KEY_JOB_LOCATION = "jobLocation";
    private static final String KEY_JOB_INDUSTRY = "jobIndustry"; // Store as comma-separated
    private static final String KEY_JOB_LEVEL = "jobLevel";
    private static final String KEY_JOB_SALARY = "jobSalary";

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    public UserSharedPreference(Context context) {
        sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    // Save user data
    public void saveUserProfile(String userName, String userEmail, String userPhoneNum, String userAddress,
                                String joinDate, String userRole, String profilePic,
                                String currentJobPosition, String jobLocation,
                                List<String> jobIndustry, String jobLevel, String jobSalary) {
        editor.putString(KEY_NAME, userName);
        editor.putString(KEY_EMAIL, userEmail);
        editor.putString(KEY_PHONE, userPhoneNum);
        editor.putString(KEY_ADDRESS, userAddress);
        editor.putString(KEY_JOIN_DATE, joinDate);
        editor.putString(KEY_ROLE, userRole);
        editor.putString(KEY_PROFILE_PIC, profilePic);
        editor.putString(KEY_CURRENT_JOB_POSITION, currentJobPosition);
        editor.putString(KEY_JOB_LOCATION, jobLocation);
        editor.putString(KEY_JOB_INDUSTRY, TextUtils.join(",", jobIndustry)); // Convert List to String
        editor.putString(KEY_JOB_LEVEL, jobLevel);
        editor.putString(KEY_JOB_SALARY, jobSalary);
        editor.apply(); // Save changes asynchronously
    }

    // Retrieve user data
    public String getUserName() {
        return sharedPreferences.getString(KEY_NAME, "");
    }

    public String getUserEmail() {
        return sharedPreferences.getString(KEY_EMAIL, "");
    }

    public String getUserPhoneNum() {
        return sharedPreferences.getString(KEY_PHONE, "");
    }

    public String getUserAddress() {
        return sharedPreferences.getString(KEY_ADDRESS, "");
    }

    public String getJoinDate() {
        return sharedPreferences.getString(KEY_JOIN_DATE, "");
    }

    public String getUserRole() {
        return sharedPreferences.getString(KEY_ROLE, "");
    }

    public String getProfilePic() {
        return sharedPreferences.getString(KEY_PROFILE_PIC, "");
    }

    public String getCurrentJobPosition() {
        return sharedPreferences.getString(KEY_CURRENT_JOB_POSITION, "");
    }

    public String getJobLocation() {
        return sharedPreferences.getString(KEY_JOB_LOCATION, "");
    }

    public List<String> getJobIndustry() {
        String industries = sharedPreferences.getString(KEY_JOB_INDUSTRY, "");
        return Arrays.asList(industries.split(",")); // Convert String back to List
    }

    public String getJobLevel() {
        return sharedPreferences.getString(KEY_JOB_LEVEL, "");
    }

    public String getJobSalary() {
        return sharedPreferences.getString(KEY_JOB_SALARY, "");
    }

    // Setters
    public void setUserName(String userName) {
        editor.putString(KEY_NAME, userName);
        editor.apply();
    }

    public void setUserEmail(String userEmail) {
        editor.putString(KEY_EMAIL, userEmail);
        editor.apply();
    }

    public void setUserPhoneNum(String userPhoneNum) {
        editor.putString(KEY_PHONE, userPhoneNum);
        editor.apply();
    }

    public void setUserAddress(String userAddress) {
        editor.putString(KEY_ADDRESS, userAddress);
        editor.apply();
    }

    public void setJoinDate(String joinDate) {
        editor.putString(KEY_JOIN_DATE, joinDate);
        editor.apply();
    }

    public void setUserRole(String userRole) {
        editor.putString(KEY_ROLE, userRole);
        editor.apply();
    }

    public void setProfilePic(String profilePic) {
        editor.putString(KEY_PROFILE_PIC, profilePic);
        editor.apply();
    }

    public void setCurrentJobPosition(String currentJobPosition) {
        editor.putString(KEY_CURRENT_JOB_POSITION, currentJobPosition);
        editor.apply();
    }

    public void setJobLocation(String jobLocation) {
        editor.putString(KEY_JOB_LOCATION, jobLocation);
        editor.apply();
    }

    public void setJobIndustry(List<String> jobIndustry) {
        editor.putString(KEY_JOB_INDUSTRY, TextUtils.join(",", jobIndustry)); // Convert List to String
        editor.apply();
    }

    public void setJobLevel(String jobLevel) {
        editor.putString(KEY_JOB_LEVEL, jobLevel);
        editor.apply();
    }

    public void setJobSalary(String jobSalary) {
        editor.putString(KEY_JOB_SALARY, jobSalary);
        editor.apply();
    }

    // Clear user data (e.g., on logout)
    public void clearProfileData() {
        editor.clear();
        editor.apply();
    }
}