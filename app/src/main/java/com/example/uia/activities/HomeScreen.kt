package com.example.uia.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import com.example.uia.databinding.ActivityHomeScreenBinding
import com.example.uia.databinding.ActivityMainBinding

class HomeScreen : AppCompatActivity() {
    lateinit var binding : ActivityHomeScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeScreenBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)
    }




}