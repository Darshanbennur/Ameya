package com.example.uia.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import com.example.uia.R
import com.example.uia.databinding.ActivityCommonQuestBinding
import com.example.uia.databinding.ActivityHomeScreenBinding

class CommonQuest : AppCompatActivity() {

    lateinit var binding : ActivityCommonQuestBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCommonQuestBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)


    }



}