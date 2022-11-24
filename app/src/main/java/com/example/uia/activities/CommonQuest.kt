package com.example.uia.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.ArrayAdapter
import android.widget.Toast
import com.example.uia.R
import com.example.uia.constant.Constants
import com.example.uia.databinding.ActivityCommonQuestBinding
import com.example.uia.models.UserModel
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class CommonQuest : AppCompatActivity() {

    lateinit var binding : ActivityCommonQuestBinding
    private lateinit var currentUser : UserModel
    var dataBaseRef : DatabaseReference = Firebase.database.getReference("Users")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCommonQuestBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)
        currentUser = intent.getSerializableExtra("currentUser") as UserModel;

        val area = Constants.UrbanStatus.values()
        val adapter = ArrayAdapter(this,R.layout.list_item,area)
        binding.dropArea.setAdapter(adapter)

        val gender = Constants.Gender.values()
        val adapGen = ArrayAdapter(this,R.layout.list_item,gender)
        binding.dropGender.setAdapter(adapGen)

        val hand = Constants.Hand.values()
        val adaphan = ArrayAdapter(this,R.layout.list_item,hand)
        binding.dropHand.setAdapter(adaphan)

        val married = Constants.married.values()
        val adapmar = ArrayAdapter(this,R.layout.list_item,married)
        binding.dropMarital.setAdapter(adapmar)

        val major = Constants.Major.values()
        val adapmaj = ArrayAdapter(this,R.layout.list_item,major)
        binding.dropMajor.setAdapter(adapmaj)

        binding.submitResponse.setOnClickListener {
            saveData()
        }
    }

    private fun saveData() {
        currentUser.urban = Constants.UrbanStatus.valueOf(binding.dropArea.text.toString())
        currentUser.gender = Constants.Gender.valueOf(binding.dropGender.text.toString())
        currentUser.hand = Constants.Hand.valueOf(binding.dropHand.text.toString())
        currentUser.married = Constants.married.valueOf(binding.dropMarital.text.toString())
        currentUser.major = Constants.Major.valueOf(binding.dropMajor.text.toString())

        var myMap = mapOf<String, UserModel>(
            currentUser.no to currentUser
        )
        dataBaseRef.updateChildren(myMap).addOnSuccessListener {
            Toast.makeText(applicationContext,"Profile Created", Toast.LENGTH_SHORT).show()
        }.addOnFailureListener {
            Toast.makeText(applicationContext,"Sed !!", Toast.LENGTH_SHORT).show()
        }

        var intent = Intent(this, ResultScreen::class.java)
        intent.putExtra("currentUser", currentUser)
        startActivity(intent)
        finish()
    }


}