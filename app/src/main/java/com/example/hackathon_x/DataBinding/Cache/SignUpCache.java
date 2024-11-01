package com.example.hackathon_x.DataBinding.Cache;

import com.example.hackathon_x.Auth.Fragments.SignUp;
import com.example.hackathon_x.DataBinding.Cache.Model.SignUpModel;

import java.util.ArrayList;
import java.util.List;

public class SignUpCache {
    private static SignUpCache instance;
    private SignUpModel signUpModel;

    // Private constructor to enforce singleton pattern
    private SignUpCache() {
        signUpModel = new SignUpModel();
    }

    // Method to get the singleton instance
    public static synchronized SignUpCache getInstance() {
        if (instance == null) {
            instance = new SignUpCache();
        }
        return instance;
    }

    // Getter for SignUpModel
    public SignUpModel getSignUpModel() {
        return signUpModel;
    }

    // Setter for SignUpModel to update data if needed
    public void setSignUpModel(SignUpModel signUpModel) {
        this.signUpModel = signUpModel;
    }
}

