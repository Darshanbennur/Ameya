package com.example.uia.activities

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Patterns
import android.view.LayoutInflater
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.uia.databinding.ActivityRegisterPageBinding
import com.example.uia.models.UserModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class RegisterPage : AppCompatActivity() {
    private lateinit var binding : ActivityRegisterPageBinding

    private lateinit var firebaseAuth : FirebaseAuth
    private lateinit var sharedPreferences : SharedPreferences
    var dataBaseRef : DatabaseReference = Firebase.database.getReference("Users")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterPageBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)

        sharedPreferences = getSharedPreferences("userData", MODE_PRIVATE)
        firebaseAuth = FirebaseAuth.getInstance()

        binding.loginHereButton.setOnClickListener {
            finish()
        }

        binding.registerButton.setOnClickListener {
            var useremail = binding.userEmail.text.toString()
            var userPhoneNumber = binding.userPhoneNumber.text.toString()
            var userName = binding.userName.text.toString()
            var userPassword = binding.userPassword.text.toString()

            if (userName.isEmpty() || useremail.isEmpty() || userPhoneNumber.isEmpty() || userPassword.isEmpty()){
                Toast.makeText(applicationContext,"Fields Can't be Empty",Toast.LENGTH_SHORT).show()
            }
            else if (!Patterns.EMAIL_ADDRESS.matcher(useremail).matches()){
                Toast.makeText(applicationContext,"Invalid Email Address Format",Toast.LENGTH_SHORT).show()
                binding.userEmail.requestFocus()
            }
            else if (userPhoneNumber.length != 10){
                Toast.makeText(applicationContext,"Phone Number to be 10 letters",Toast.LENGTH_SHORT).show()
                binding.userPhoneNumber.requestFocus()
            }
            else if (userPassword.length < 8){
                Toast.makeText(applicationContext,"Password length minimum 8 characters",Toast.LENGTH_SHORT).show()
                binding.userPassword.requestFocus()
            }
            else{
                dataBaseRef.addListenerForSingleValueEvent(object  : ValueEventListener{
                    override fun onDataChange(snapshot: DataSnapshot) {
                        if (snapshot.hasChild(userPhoneNumber)){
                            Toast.makeText(applicationContext,"Already Registered",Toast.LENGTH_SHORT).show()
                        }else{
                            var userModel : UserModel = UserModel(userName, userPhoneNumber,useremail)

                            var myMap = mapOf<String, UserModel>(
                                userPhoneNumber to userModel
                            )
                            var myMapEmail = mapOf<String, String>(
                                "email" to useremail
                            )

                            dataBaseRef.updateChildren(myMap).addOnSuccessListener {
                                Toast.makeText(applicationContext,"Done RD",Toast.LENGTH_SHORT).show()
                            }.addOnFailureListener {
                                Toast.makeText(applicationContext,"Failed RD",Toast.LENGTH_SHORT).show()
                            }
                            dataBaseRef.child(userPhoneNumber).updateChildren(myMapEmail).addOnSuccessListener {
                                Toast.makeText(applicationContext,"Done RD",Toast.LENGTH_SHORT).show()
                            }.addOnFailureListener {
                                Toast.makeText(applicationContext,"Failed RD",Toast.LENGTH_SHORT).show()
                            }
                            firebaseAuth.createUserWithEmailAndPassword(useremail,userPassword).addOnCompleteListener {
                                if (it.isSuccessful){
                                    Toast.makeText(applicationContext,"Successfully Registered",Toast.LENGTH_SHORT).show()
                                    var userModel = UserModel(userName, userPhoneNumber)
                                    val editor: SharedPreferences.Editor = sharedPreferences.edit()
                                    editor.clear()
                                    editor.apply()
                                    sharedPreferences.edit().putString("name","" + userName).apply()
                                    sharedPreferences.edit().putString("number","" + userPhoneNumber).apply()
                                    sharedPreferences.edit().putString("email","" + useremail).apply()
                                    var intent = Intent(applicationContext, MainActivity::class.java)
                                    intent.putExtra("currentUser", userModel)
                                    startActivity(intent)
                                    finish()
                                }else{
                                    Toast.makeText(applicationContext,"Registration Failed",Toast.LENGTH_SHORT).show()
                                }
                            }
                        }
                    }

                    override fun onCancelled(error: DatabaseError) {

                    }

                })
            }
        }

    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }

}