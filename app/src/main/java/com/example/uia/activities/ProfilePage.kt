package com.example.uia.activities

import android.R.attr.label
import android.content.*
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.uia.R
import com.example.uia.databinding.ActivityProfilePageBinding
import android.content.ClipboardManager
import android.widget.Toast


class ProfilePage : AppCompatActivity() {

    lateinit var binding : ActivityProfilePageBinding
    private lateinit var sharedPreferences : SharedPreferences
    lateinit var uri : Uri

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfilePageBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)

        sharedPreferences = getSharedPreferences("userData", MODE_PRIVATE)

        binding.userName.text = sharedPreferences.getString("name","Oppsies Error").toString()

//        if (!sharedPreferences.getString("profileURI","noImage").equals("noImage")){
//            uri = Uri.parse(sharedPreferences.getString("profileURI","noImage"))
//            binding.imageView.setImageURI(uri)
//        }

        binding.changePhoto.setOnClickListener {
            var intent = Intent(Intent.ACTION_GET_CONTENT)
            intent.type = "image/+"
            startActivityForResult(intent,100)
        }

        binding.removePhoto.setOnClickListener {
            binding.imageView.setImageResource(R.drawable.profile)
        }

        binding.educationAnswer.text = sharedPreferences.getString("userEducation","bacha")
        binding.majorAnswer.text = sharedPreferences.getString("userMajor","nalla hai")
        binding.GenderAnswer.text = sharedPreferences.getString("userGender","ladka hai")
        binding.marriedAnswer.text = sharedPreferences.getString("userMaritial","koi na mil rha ise")
        binding.phoneAnswer.text = sharedPreferences.getString("number","paise nahi")
        binding.emailAnswer.text = sharedPreferences.getString("email","not registered")

        binding.emailAnswer.setOnClickListener {
            val clipboard = getSystemService(CLIPBOARD_SERVICE) as ClipboardManager
            val clip = ClipData.newPlainText(label.toString(), binding.emailAnswer.text)
            clipboard.setPrimaryClip(clip)
            Toast.makeText(applicationContext,"Copied to ClipBoard", Toast.LENGTH_SHORT).show()
        }

        binding.back.setOnClickListener {
            finish()
        }

    }

    fun contactSeller(view: View) {
        val txtview = view as TextView
        val intent_04 = Intent(Intent.ACTION_DIAL)
        intent_04.data = Uri.parse("tel:" + txtview.text.toString())
        startActivity(intent_04)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 100){
            if (data != null) {
                binding.imageView.setImageURI(data.data)
//                uri = data?.data!!
//                sharedPreferences.edit().putString("profileURI","" + uri).apply()
            }
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }
}