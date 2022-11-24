package com.example.uia.activities

import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.uia.databinding.ActivityResultScreenBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import java.util.*

class ResultScreen : AppCompatActivity() {
    lateinit var binding : ActivityResultScreenBinding
    private lateinit var sharedPreferences : SharedPreferences
    var dataBaseRef : DatabaseReference = Firebase.database.getReference("Users")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =  ActivityResultScreenBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)
        sharedPreferences = getSharedPreferences("userData", MODE_PRIVATE)
        binding.btn2.isClickable = false
        binding.btn3.isClickable = false
        binding.btn4.isClickable = false

        var flag = sharedPreferences.getString("quiz_status","no")
        if (flag.equals("Done")){
            binding.btn2.isClickable = true
            binding.btn3.isClickable = true
            binding.btn4.isClickable = true
        }

        dataBaseRef.child(sharedPreferences.getString("number","user1").toString()).addListenerForSingleValueEvent(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                for (data in snapshot.children){
                    if(Objects.equals(data.key,"name")){
                        binding.userName.text = "Hello, " + data.value.toString() + "!"
                    }
                }
            }

            override fun onCancelled(error: DatabaseError) {

            }

        })

        binding.potential.setOnClickListener {
            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse("https://erratic-touch-34b.notion.site/Unconventional-Career-Stories-5cf355a518784b36b8cb26e904419a4c"))
            startActivity(browserIntent)
        }

    }

    fun reDirect(view : View){
        var char = view.tag
        when (char) {
            "1" -> {
                var intent = Intent(this,HomeScreen::class.java)
                startActivity(intent)
            }
            "2" -> {
                val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.notion.so/Career-Paths-dff18f3e3af24f7fabdcd8cd2c5d627e"))
                startActivity(browserIntent)
            }
            "3" -> {
                val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.notion.so/Mentor-Guidance-Portal-61971aa392e44db897a7d4270abdb587"))
                startActivity(browserIntent)
            }
            "4" -> {
                if (!binding.btn4.isClickable){
                    Toast.makeText(applicationContext,"Connot Proceed, Complete Quiz", Toast.LENGTH_SHORT).show()
                }
            }
            else -> { // Note the block
                Toast.makeText(applicationContext,"Invalid", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }

}