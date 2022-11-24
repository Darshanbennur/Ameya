package com.example.uia.activities

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.view.LayoutInflater
import android.widget.Toast
import com.example.uia.databinding.ActivityLoginScreenBinding
import com.example.uia.models.UserModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import java.util.*

class LoginScreen : AppCompatActivity() {
    private lateinit var binding : ActivityLoginScreenBinding
    private lateinit var firebaseAuth : FirebaseAuth
    private lateinit var sharedPreferences : SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginScreenBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)
        sharedPreferences = getSharedPreferences("userData", MODE_PRIVATE)

        firebaseAuth = FirebaseAuth.getInstance()

        binding.registerHereButton.setOnClickListener {
            var intent = Intent(this, RegisterPage::class.java)
            startActivity(intent)
        }

        binding.loginButton.setOnClickListener {
            var userEmail = binding.userEmail.text.toString()
            var password = binding.userPassword.text.toString()
            var phoneNum = binding.userPhoneNumber.text.toString()

            if (!Patterns.EMAIL_ADDRESS.matcher(userEmail).matches()){
                Toast.makeText(applicationContext,"Invalid Email Address Format", Toast.LENGTH_SHORT).show()
                binding.userEmail.requestFocus()
            }
            else if (password.length < 8){
                Toast.makeText(applicationContext,"Password length minimum 8 characters", Toast.LENGTH_SHORT).show()
                binding.userPassword.requestFocus()
            }
            else if (phoneNum.length!=10){
                Toast.makeText(applicationContext,"Phone Number of Incorrect Length", Toast.LENGTH_SHORT).show()
                binding.userPhoneNumber.requestFocus()
            }
            else{
                firebaseAuth.signInWithEmailAndPassword(userEmail,password).addOnCompleteListener {
                    if (it.isSuccessful){
                        Toast.makeText(applicationContext,"Logged in Successfully", Toast.LENGTH_SHORT).show()
                        sharedPreferences.edit().putString("number","" + phoneNum).apply()
                        login(phoneNum)

                    }
                    else{
                        Toast.makeText(applicationContext,"Login Error", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }

    fun login(phoneNumber : String) {
        var name : String
        var dataBaseRef : DatabaseReference = Firebase.database.getReference("Users").child(phoneNumber)
        dataBaseRef.addListenerForSingleValueEvent(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                for(data in snapshot.children){
                    if(Objects.equals(data.key,"name")) {
                        name = data.value.toString()
                        var userModel = UserModel(name, phoneNumber)
                        var intent = Intent(applicationContext, MainActivity::class.java)
                        intent.putExtra("currentUser", userModel)
                        startActivity(intent)
                        finish()
                    }
                }
            }

            override fun onCancelled(error: DatabaseError) {

            }

        })
    }

}