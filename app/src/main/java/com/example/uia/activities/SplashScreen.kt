package com.example.uia.activities

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import com.example.uia.R
import com.example.uia.databinding.ActivityCommonQuestBinding
import com.example.uia.databinding.ActivitySplashScreenBinding

class SplashScreen : AppCompatActivity() {

    lateinit var binding : ActivitySplashScreenBinding
    lateinit var handler : Handler
    private lateinit var preferences : SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashScreenBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)

        preferences = getSharedPreferences("userData", MODE_PRIVATE)
        handler = Handler()

        handler.postDelayed({

            if (preferences.getString("number", "no").toString() == "no") {
                val intent = Intent(this, LoginScreen::class.java)
                startActivity(intent)
                finish()
            } else {
                val intent = Intent(this, ResultScreen::class.java)
                startActivity(intent)
                finish()
            }
        },2000)

    }

}