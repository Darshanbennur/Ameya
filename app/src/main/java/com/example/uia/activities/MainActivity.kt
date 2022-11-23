package com.example.uia.activities

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.uia.constant.Constants.EduStatus.*
import com.example.uia.databinding.ActivityMainBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    private lateinit var preferences : SharedPreferences
    var dataBaseRef : DatabaseReference = Firebase.database.getReference("Users")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)
        preferences = getSharedPreferences("userData", MODE_PRIVATE)
    }

    fun getStatus(view : View){
        var name = view.tag
        when (name) {
            "1" -> {
                Toast.makeText(applicationContext, School.name, Toast.LENGTH_SHORT).show()
                updateData(School.name)
            }
            "2" -> {
                Toast.makeText(applicationContext, College.name, Toast.LENGTH_SHORT).show()
                updateData(College.name)
            }
            "3" -> {
                Toast.makeText(applicationContext, Graduate.name, Toast.LENGTH_SHORT).show()
                updateData(Graduate.name)
            }
            "4" -> {
                Toast.makeText(applicationContext, Professional.name, Toast.LENGTH_SHORT).show()
                updateData(Professional.name)
            }
            else -> { // Note the block
                Toast.makeText(applicationContext,"Invalid Response",Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun updateData(num : String){
        val favourUpdate = mapOf<String,String>(
            "EdStatus" to num
        )
        dataBaseRef.child(preferences.getString("number","user1").toString()).updateChildren(favourUpdate).addOnSuccessListener {
            Toast.makeText(applicationContext,"Education Status Updated",Toast.LENGTH_SHORT).show()
        }
        var intent = Intent(this, HomeScreen::class.java)
        startActivity(intent)
        finish()
    }

}