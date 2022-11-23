package com.example.uia.models

import com.example.uia.constant.Constants

class UserProfile (
    private val education: Constants.EduStatus,
    val urban:Constants.UrbanStatus,
    val gender : Constants.Gender,
    val engnat : Boolean,
    val hand : Constants.Hand,
    val married : Constants.married){

}