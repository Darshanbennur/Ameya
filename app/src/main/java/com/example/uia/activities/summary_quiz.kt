package com.example.uia.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import com.example.uia.R
import com.example.uia.databinding.ActivityResultScreenBinding
import com.example.uia.databinding.ActivitySummaryQuizBinding

class summary_quiz : AppCompatActivity() {

    lateinit var binding : ActivitySummaryQuizBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =  ActivitySummaryQuizBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)

        var word = intent.getStringExtra("pahilaword")
        var link = ""
        Toast.makeText(applicationContext,word, Toast.LENGTH_SHORT).show()
        when (word) {
            "R" -> {
                binding.tv6.text = "Engineering"
                binding.tv3.text = "Computers"
                binding.tv5.text = "Mechanic/Machinist"
                binding.tv2.text = "Health Assistant"
                binding.tv4.text = "Construction"
                binding.tv1.text = "Agriculture"
                binding.tv7.text = "Food and Hospitality"
                link = "https://murindanyi.medium.com/realistic-1c660d90a0f2"
            }
            "I" -> {
                binding.tv2.text = "Engineering"
                binding.tv5.text = "Medicine/Surgery"
                binding.tv7.text = "Psychology"
                binding.tv3.text = "Chemistry"
                binding.tv1.text = "Marine Biology"
                binding.tv6.text = "Consumer Economics"
                binding.tv4.text = "Zoology"
                link = "https://murindanyi.medium.com/investigative-1d41a25c4d27"
            }
            "A" -> {
                binding.tv4.text = "Photography"
                binding.tv3.text = "Fine and Performing Arts"
                binding.tv1.text = "Communications"
                binding.tv5.text = "Radio and TV"
                binding.tv6.text = "Interior Design"
                binding.tv7.text = "Architecture"
                binding.tv2.text = "Cosmetology"
                link = "https://medium.com/@devcollins.test/artistic-c2f7b8954ca6"
            }
            "S" -> {
                binding.tv1.text = "Counseling"
                binding.tv2.text = "Nursing"
                binding.tv3.text = "Physical Therapy"
                binding.tv6.text = "Public Relations"
                binding.tv4.text = "Travel"
                binding.tv5.text = "Advertising"
                binding.tv7.text = "Education"
                link = "https://medium.com/@devcollins.test/social-59cad4c14d51"
            }
            "E" -> {
                binding.tv4.text = "Law"
                binding.tv2.text = "Real Estate"
                binding.tv6.text = "International Trade"
                binding.tv5.text = "Political Science"
                binding.tv3.text = "Marketing/Sales"
                binding.tv7.text = "Banking/Finance"
                binding.tv1.text = "Fashion Merchandising"
                link = "https://murindanyi.medium.com/enterprising-74dc2ae0c344"
            }
            "C" -> {
                binding.tv6.text = "Banking"
                binding.tv5.text = "Medical Records"
                binding.tv4.text = "Administration"
                binding.tv1.text = "Accounting"
                binding.tv7.text = "Data Processing"
                binding.tv3.text = "Insurance"
                binding.tv2.text = "Court Reporting"
                link = "https://medium.com/@devcollins.test/conventional-45779d81c9ca"
            }
            else -> { // Note the block
                Toast.makeText(applicationContext,"Invalid", Toast.LENGTH_SHORT).show()
            }
        }

        binding.explore.setOnClickListener {
            var intent = Intent(this,webview::class.java)
            intent.putExtra("url",link)
            startActivity(intent)
        }

        binding.backtoHome.setOnClickListener {
            var intent = Intent(this,ResultScreen::class.java)
            startActivity(intent)
            finish()
        }

    }

}