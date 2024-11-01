package com.example.hackathon_x.DataBinding.Cache.Model;

import java.util.List;
import java.util.Map;

public class MentorProfileModel {

    private Map<String, Object> userInfos;
    private Map<String, Object> userExperience;
    private Map<String, Object> userEducation;
    private Map<String, Object> userAwards;
    private Map<String, Object> userLanguage;
    private Map<String, Object> userWebinars;
    public MentorProfileModel(
            //userInfos
            String userName,
            String userCurrentPosition,
            int userConnectionCount,
            int userMentorshipCount,
            String userProfilePic,
            String userEmail,
            String userPhoneNum,
            String userStatus,
            String userRole,
            String userAddress,

            //userExperience
            List<String> expImage,
            List<String> expPosition,
            List<String> expAssociated,
            List<String> expType,
            List<String> expFrom,
            List<String> expTo,
            List<String> expRange,

            //userEducation
            List<String> eduImage,
            List<String> eduName,
            List<String> eduLevel,
            List<String> eduType,
            List<String> eduFrom,
            List<String> eduTo,
            List<String> eduRange,

            //userAwards
            List<String> awardsName,
            List<String> awardsBy,
            List<String> awardsDate,

            //userLanguage
            List<String> langName,
            List<String> langCert,
            List<String> langLevel

    ) {
        //userInfos
        this.userInfos.put("userCurrentPosition", userCurrentPosition);
        this.userInfos.put("userAddress", userAddress);
        this.userInfos.put("userConnectionCount", userConnectionCount);
        this.userInfos.put("userMentorshipCount", userMentorshipCount);
        this.userInfos.put("userProfilePic", userProfilePic);
        this.userInfos.put("userEmail", userEmail);
        this.userInfos.put("userPhoneNum", userPhoneNum);
        this.userInfos.put("userStatus", userStatus);
        this.userInfos.put("userRole", userRole);
        this.userInfos.put("userAddress", userAddress);


        //userExperience
        this.userExperience.put("expImage", expImage);
        this.userExperience.put("expPosition", expPosition);
        this.userExperience.put("expAssociated", expAssociated);
        this.userExperience.put("expType", expType);
        this.userExperience.put("expFrom", expFrom);
        this.userExperience.put("expTo", expTo);
        this.userExperience.put("expRange", expRange);


        //userEducation
        this.userEducation.put("eduImage", eduImage);
        this.userEducation.put("eduName", eduName);
        this.userEducation.put("eduLevel", eduLevel);
        this.userEducation.put("eduType", eduType);
        this.userEducation.put("eduFrom", eduFrom);
        this.userEducation.put("eduTo", eduTo);
        this.userEducation.put("eduRange", eduRange);


        //userAwards
        this.userAwards.put("awardsName", awardsName);
        this.userAwards.put("awardsBy", awardsBy);
        this.userAwards.put("awardsDate", awardsDate);


        //userLanguage
        this.userLanguage.put("langName", langName);
        this.userLanguage.put("langCert", langCert);
        this.userLanguage.put("langLevel", langLevel);
    }
    // User Infos
    public String getUserCurrentPosition() {
        return (String)  userInfos.get("userCurrentPosition");
    }

    public void setUserCurrentPosition(String userCurrentPosition) {
        this.userInfos.put("userCurrentPosition", userCurrentPosition);
    }

    public String getUserAddress() {
        return (String) userInfos.get("userAddress");
    }

    public void setUserAddress(String userAddress) {
        this.userInfos.put("userAddress", userAddress);
    }

    public int getUserConnectionCount() {
        return (int) userInfos.get("userConnectionCount");
    }

    public void setUserConnectionCount(int userConnectionCount) {
        this.userInfos.put("userConnectionCount", userConnectionCount);
    }

    public int getUserMentorshipCount() {
        return (int)userInfos.get("userMentorshipCount");
    }

    public void setUserMentorshipCount(int userMentorshipCount) {
        this.userInfos.put("userMentorshipCount", userMentorshipCount);
    }

    public String getUserProfilePic() {
        return (String) userInfos.get("userProfilePic");
    }

    public void setUserProfilePic(String userProfilePic) {
        this.userInfos.put("userProfilePic", userProfilePic);
    }

    public String getUserEmail() {
        return (String) userInfos.get("userEmail");
    }

    public void setUserEmail(String userEmail) {
        this.userInfos.put("userEmail", userEmail);
    }

    public String getUserPhoneNum() {
        return (String) userInfos.get("userPhoneNum");
    }

    public void setUserPhoneNum(String userPhoneNum) {
        this.userInfos.put("userPhoneNum", userPhoneNum);
    }

    public String getUserStatus() {
        return (String) userInfos.get("userStatus");
    }

    public void setUserStatus(String userStatus) {
        this.userInfos.put("userStatus", userStatus);
    }

    public String getUserRole() {
        return (String) userInfos.get("userRole");
    }

    public void setUserRole(String userRole) {
        this.userInfos.put("userRole", userRole);
    }

    // User Experience
    public String getExpImage() {
        return (String) userExperience.get("expImage");
    }

    public void setExpImage(String expImage) {
        this.userExperience.put("expImage", expImage);
    }

    public String getExpPosition() {
        return (String) userExperience.get("expPosition");
    }

    public void setExpPosition(String expPosition) {
        this.userExperience.put("expPosition", expPosition);
    }

    public String getExpAssociated() {
        return (String) userExperience.get("expAssociated");
    }

    public void setExpAssociated(String expAssociated) {
        this.userExperience.put("expAssociated", expAssociated);
    }

    public String getExpType() {
        return (String) userExperience.get("expType");
    }

    public void setExpType(String expType) {
        this.userExperience.put("expType", expType);
    }

    public String getExpFrom() {
        return (String) userExperience.get("expFrom");
    }

    public void setExpFrom(String expFrom) {
        this.userExperience.put("expFrom", expFrom);
    }

    public String getExpTo() {
        return (String) userExperience.get("expTo");
    }

    public void setExpTo(String expTo) {
        this.userExperience.put("expTo", expTo);
    }

    public String getExpRange() {
        return (String) userExperience.get("expRange");
    }

    public void setExpRange(String expRange) {
        this.userExperience.put("expRange", expRange);
    }

    // User Education
    public String getEduImage() {
        return (String) userEducation.get("eduImage");
    }

    public void setEduImage(String eduImage) {
        this.userEducation.put("eduImage", eduImage);
    }

    public String getEduName() {
        return (String) userEducation.get("eduName");
    }

    public void setEduName(String eduName) {
        this.userEducation.put("eduName", eduName);
    }

    public String getEduLevel() {
        return (String) userEducation.get("eduLevel");
    }

    public void setEduLevel(String eduLevel) {
        this.userEducation.put("eduLevel", eduLevel);
    }

    public String getEduType() {
        return (String) userEducation.get("eduType");
    }

    public void setEduType(String eduType) {
        this.userEducation.put("eduType", eduType);
    }

    public String getEduFrom() {
        return (String) userEducation.get("eduFrom");
    }

    public void setEduFrom(String eduFrom) {
        this.userEducation.put("eduFrom", eduFrom);
    }

    public String getEduTo() {
        return (String) userEducation.get("eduTo");
    }

    public void setEduTo(String eduTo) {
        this.userEducation.put("eduTo", eduTo);
    }

    public String getEduRange() {
        return (String) userEducation.get("eduRange");
    }

    public void setEduRange(String eduRange) {
        this.userEducation.put("eduRange", eduRange);
    }

    // User Awards
    public String getAwardsName() {
        return (String) userAwards.get("awardsName");
    }

    public void setAwardsName(String awardsName) {
        this.userAwards.put("awardsName", awardsName);
    }

    public String getAwardsBy() {
        return (String) userAwards.get("awardsBy");
    }

    public void setAwardsBy(String awardsBy) {
        this.userAwards.put("awardsBy", awardsBy);
    }

    public String getAwardsDate() {
        return (String) userAwards.get("awardsDate");
    }

    public void setAwardsDate(String awardsDate) {
        this.userAwards.put("awardsDate", awardsDate);
    }

    // User Language
    public String getLangName() {
        return (String) userLanguage.get("langName");
    }

    public void setLangName(String langName) {
        this.userLanguage.put("langName", langName);
    }

    public String getLangCert() {
        return (String) userLanguage.get("langCert");
    }

    public void setLangCert(String langCert) {
        this.userLanguage.put("langCert", langCert);
    }

    public String getLangLevel() {
        return (String) userLanguage.get("langLevel");
    }

    public void setLangLevel(String langLevel) {
        this.userLanguage.put("langLevel", langLevel);
    }




}
