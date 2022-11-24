package com.example.uia.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import com.example.uia.R
import com.example.uia.databinding.ActivityResultScreenBinding
import com.example.uia.databinding.ActivitySplashScreenBinding

class ResultScreen : AppCompatActivity() {
    lateinit var binding : ActivityResultScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =  ActivityResultScreenBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)
    }

}