package com.example.uia.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.ArrayAdapter
import com.example.uia.R
import com.example.uia.constant.Constants
import com.example.uia.databinding.ActivityCommonQuestBinding

class CommonQuest : AppCompatActivity() {

    lateinit var binding : ActivityCommonQuestBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCommonQuestBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)

        val area = Constants.UrbanStatus.values()
        val adapter = ArrayAdapter(this,R.layout.list_item,area)
        binding.drop1.setAdapter(adapter)

        val gender = Constants.Gender.values()
        val adapGen = ArrayAdapter(this,R.layout.list_item,gender)
        binding.drop2.setAdapter(adapGen)

        val hand = Constants.Hand.values()
        val adaphan = ArrayAdapter(this,R.layout.list_item,hand)
        binding.drop3.setAdapter(adaphan)

        val married = Constants.married.values()
        val adapmar = ArrayAdapter(this,R.layout.list_item,married)
        binding.drop4.setAdapter(adapmar)

        val major = Constants.Major.values()
        val adapmaj = ArrayAdapter(this,R.layout.list_item,major)
        binding.drop5.setAdapter(adapmaj)

    }

    private fun saveData() {


        var urban = binding.drop1.text.toString()
//        var userProfile = UserProfile(,Constants.UrbanStatus.valueOf(urban),)
//        userProfile.urban = Constants.UrbanStatus.valueOf(urban)

    }


}