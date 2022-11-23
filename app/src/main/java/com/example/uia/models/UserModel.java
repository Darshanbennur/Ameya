package com.example.uia.models;

import com.example.uia.constant.Constants;

public class UserModel {
    String name;
    String no;
    Constants.EduStatus education;
    Constants.UrbanStatus urban;
    Constants.Gender gender;
    Boolean engnat;
    Constants.Hand hand;
    Constants.married married;

    public UserModel(String name, String no) {
        this.name = name;
        this.no = no;
    }

    public UserModel(String name, String no, Constants.EduStatus education, Constants.UrbanStatus urban, Constants.Gender gender, Boolean engnat, Constants.Hand hand, Constants.married married) {
        this.name = name;
        this.no = no;
        this.education = education;
        this.urban = urban;
        this.gender = gender;
        this.engnat = engnat;
        this.hand = hand;
        this.married = married;
    }
}